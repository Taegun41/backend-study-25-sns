package com.apptivedev.sns.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

// 로그인 요청 양식 (형식 검사는 회원가입에서 끝났으므로 필수·공백 불가만 검사)
public record LoginRequest(

        @NotBlank(message = "이메일은 필수입니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password
) {
}
