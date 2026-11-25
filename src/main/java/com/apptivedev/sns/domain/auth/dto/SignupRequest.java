package com.apptivedev.sns.domain.auth.dto;

// 회원가입 요청 양식 (id는 서버가 만들기 때문에 받지 않는다)
public record SignupRequest(
        String email,
        String password,
        String name
) {
}
