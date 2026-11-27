package com.apptivedev.sns.domain.auth.service;

import com.apptivedev.sns.domain.auth.dto.LoginRequest;
import com.apptivedev.sns.domain.auth.dto.SignupRequest;
import com.apptivedev.sns.domain.user.dto.UserResponse;
import com.apptivedev.sns.domain.user.entity.User;
import com.apptivedev.sns.domain.user.repository.UserRepository;
import com.apptivedev.sns.global.exception.CustomException;
import com.apptivedev.sns.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //이메일 적합성 검증 로직
    private void validDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    @Transactional
    public UserResponse signup(SignupRequest request) {
        validDuplicateEmail(request.email());
        // 1. 요청 DTO → 엔티티
        User user = new User(request.email(), request.name(), passwordEncoder.encode(request.password()));
        // 2. DB에 저장 → id가 채워진 User가 돌아옴
        User savedUser = userRepository.save(user);

        // 3. 엔티티 → 응답 DTO (password가 여기서 빠짐)
        return UserResponse.from(savedUser);
    }

    public UserResponse login(LoginRequest request){
        // 1. 이메일로 사용자 찾기 (없으면 LOGIN_FAILED)
        User user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new CustomException(ErrorCode.LOGIN_FAILED)
        );
        // 2. 비밀번호 대조 (틀리면 LOGIN_FAILED)
        // 순서 중요! -> 입력한 원본 + 저장된 HASH
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }
        // 3. 통과 시 UserResponse 반환(이건 추후에 토큰으로 교체)
        return UserResponse.from(user);
    }

}
