package com.apptivedev.sns.domain.auth.controller;

import com.apptivedev.sns.domain.auth.dto.LoginRequest;
import com.apptivedev.sns.domain.auth.dto.SignupRequest;
import com.apptivedev.sns.domain.auth.service.AuthService;
import com.apptivedev.sns.domain.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // POST /auth/signup → 201 Created
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED) //매서드가 성공적으로 끝나면 응답하는 어노테이션
    public UserResponse signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }
}
