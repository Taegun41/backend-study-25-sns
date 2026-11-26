package com.apptivedev.sns.domain.auth.service;

import com.apptivedev.sns.domain.auth.dto.SignupRequest;
import com.apptivedev.sns.domain.user.dto.UserResponse;
import com.apptivedev.sns.domain.user.entity.User;
import com.apptivedev.sns.domain.user.repository.UserRepository;
import com.apptivedev.sns.global.exception.CustomException;
import com.apptivedev.sns.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse signup(SignupRequest request) {
        validDuplicateEmail(request.email());
        // 1. 요청 DTO → 엔티티 (비밀번호는 5단계에서 Hash로 바꿀 예정, 지금은 평문)
        User user = new User(request.email(), request.name(), request.password());

        // 2. DB에 저장 → id가 채워진 User가 돌아옴
        User savedUser = userRepository.save(user);

        // 3. 엔티티 → 응답 DTO (password가 여기서 빠짐)
        return UserResponse.from(savedUser);
    }
    //이메일 적합성 검증 로직
    private void validDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
