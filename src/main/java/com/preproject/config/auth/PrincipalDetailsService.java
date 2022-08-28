package com.preproject.config.auth;

import com.preproject.User.entity.User;
import com.preproject.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userEntity = userRepository.findByuserName(userName);
        System.out.println("userName : " + userName);
        if(userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}