package com.apptivedev.sns.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //class안의 @Bean 매서드들이 돌려주는 객체를 빈으로 정의
public class PasswordConfig {

    @Bean //이 매서드가 돌려주는 객체를 bean으로 등록
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
