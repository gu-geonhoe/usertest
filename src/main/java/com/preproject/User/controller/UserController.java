package com.preproject.User.controller;



import com.preproject.User.dto.UserPatchDto;
import com.preproject.User.dto.UserPostDto;
import com.preproject.User.entity.User;
import com.preproject.User.mapper.UserMapper;
import com.preproject.User.repository.UserRepository;
import com.preproject.User.service.UserService;
import com.preproject.response.MultiResponseDto;
import com.preproject.response.SingleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Controller
@RestController
@RequestMapping("/user")  //유저 관련 모든 페이지
@Validated
@Slf4j
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
private final UserService userService;
private final UserMapper mapper;
private final UserRepository userRepository;

    public UserController(UserService userService, UserMapper mapper, UserRepository userRepository) {
        this.userService = userService;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @PostMapping("/logout")  //로그아웃
    public ResponseEntity logOut(){

        return null;
    }
    @PostMapping("/login")  //로그인
    public ResponseEntity login(){
        return null;
    }
//    @GetMapping("/login")
//    public String tologin() {
//        return "loginForm";
//    }
//    @GetMapping("/signup")
//public String ssignup(){
//        return "joinForm";
//    }

//    @PostMapping("/signup")  //회원 가입 , PostUser
//    public ResponseEntity signup(@Valid @RequestBody UserPostDto userDto){
//        String rawPassword = userDto.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        userDto.setPassword(encPassword);
//        User user =
//                userService.createUser(mapper.userPostDtoToUser(userDto));
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(mapper.userToUserResponseDto(user)),
//                HttpStatus.CREATED);
////        return "redirect:/user/login";
//    }

//@PostMapping("/signup")
//public String signup(User user){
//    String rawPassword = user.getPassword();
//    String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//    user.setPassword(encPassword);
//
//    userRepository.save(user);
//
//    return "redirect:/user/login";
//}

    @PostConstruct
    public void init(){
      userRepository.save(new User(10L,"김코딩","kcd@asd.com","1234"));
        userRepository.save(new User(11L,"박코딩","kcd2@asd.com","1234"));
        userRepository.save(new User(12L,"이코딩","kcd3@asd.com","1234"));
        userRepository.save(new User(13L,"구코딩","kcd4@asd.com","1234"));
    }


    @GetMapping("/mypage/{user-id}")//회원 정보 조회  , 다른 회원 조회 기능은 추가 예정??
    public ResponseEntity getUser(
            @PathVariable("user-id") @Positive long userId){

        User user = userService.findUser(userId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.userToUserResponseDto(user))
                , HttpStatus.OK);
    }

    @GetMapping("/{user-id}")//다른 회원 정보 조회
    public ResponseEntity getOtherUser(
            @PathVariable("user-id") @Positive long userId){

        User user = userService.findUser(userId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.userToUserResponseDto(user))
                , HttpStatus.OK);
    }

    @PatchMapping("/mypage/edit/{user-id}")  //회원 정보 수정
    public ResponseEntity patchUser(
            @PathVariable("user-id") @Positive long userId,
            @Valid @RequestBody UserPatchDto userPatchDto) {
        userPatchDto.setUserId(userId);

       User user =
                userService.updateUser(mapper.userPatchDtoToUser(userPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.userToUserResponseDto(user)),
                HttpStatus.OK);
    }

    @GetMapping //전체 회원 조회
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<User> pageUsers = userService.findUsers(page - 1, size);
        List<User> users = pageUsers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.usersToUserResponseDtos(users),
                        pageUsers),
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{user-id}")  //회원 삭제
    public ResponseEntity userDelete(
            @PathVariable("user-id") long userId){
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
