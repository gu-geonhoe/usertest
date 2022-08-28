package com.preproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // 추가
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();  // csrf 관련 부분
        http.headers().frameOptions().disable();  // h2 와 연결

        http.authorizeRequests()
                .antMatchers("/write/**").authenticated()  //write 도메인에 권한 부여
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .usernameParameter("userName")
                .loginPage("/user/login");
        return http.build();
    }

}