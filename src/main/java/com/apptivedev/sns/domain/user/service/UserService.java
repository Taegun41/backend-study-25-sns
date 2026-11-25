package com.apptivedev.sns.domain.user.service;

import com.apptivedev.sns.domain.user.dto.UserResponse;
import com.apptivedev.sns.domain.user.entity.User;
import com.apptivedev.sns.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse userRead(Long userId) {
        Optional<User> user = userRepository.findById(userId);          // findById : Optional<user> 형태를 반환
        User foundUser = user.orElseThrow(                               //Optional<User> 안의 User 값을 가져오기
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        return UserResponse.from(foundUser);                             // 3. DTO로 바꿔 반환
    }


    }

}
