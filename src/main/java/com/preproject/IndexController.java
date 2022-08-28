package com.preproject;

import com.preproject.User.entity.User;
import com.preproject.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Http status 를 반환 할시에는 RestController 를 view 를 반환 하고 싶을 땐 Controller 를 사용한다.

public class IndexController {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;


    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/user/signup")
    public String join() {
        return "joinForm";
    }

    @GetMapping("/")
    public @ResponseBody String index() {
        return "여기는 메인 페이지 입니다.(질문 목록)";
    }

    @PostMapping("/user/signup")
    public String signup(User user){
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user);

        return "redirect:/user/login";
    }

}
