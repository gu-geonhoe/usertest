package com.preproject.User.service;

import com.preproject.User.entity.User;
import com.preproject.User.repository.UserRepository;
import com.preproject.exception.BusinessLogicException;
import com.preproject.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        User savedUser = user;
        return userRepository.save(savedUser);
    }


    public User updateUser(User user) {
        User findUser = findVerifiedUser(user.getUserId());

        Optional.ofNullable(user.getUserName())
                .ifPresent(name -> findUser.setUserName(name));
        Optional.ofNullable(user.getEmail())
                .ifPresent(email -> findUser.setEmail(email));
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> findUser.setPassword(password));


        return userRepository.save(findUser);
    }


    public User findUser(long userId) {
        return  findVerifiedUser(userId);
    }

    public Page<User> findUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size,
                Sort.by("userId").descending()));
    }

    public void deleteUser(long userId) {
        User findUser = findVerifiedUser(userId);
        userRepository.delete(findUser);

    }

    public User findVerifiedUser(long userId) {
        Optional<User> optionalMember =
                userRepository.findById(userId);
        User findUser =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findUser;
    }
    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }


}