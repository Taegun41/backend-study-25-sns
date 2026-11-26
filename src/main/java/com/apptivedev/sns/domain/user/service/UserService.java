package com.apptivedev.sns.domain.user.service;

import com.apptivedev.sns.domain.user.dto.UserResponse;
import com.apptivedev.sns.domain.user.dto.UserUpdateRequest;
import com.apptivedev.sns.domain.user.entity.User;
import com.apptivedev.sns.domain.user.repository.UserRepository;
import com.apptivedev.sns.global.exception.CustomException;
import com.apptivedev.sns.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private User getUserOrThrow(Long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
    public UserResponse userRead(Long userId) {
        User foundUser = getUserOrThrow(userId);
        return UserResponse.from(foundUser);                             // 3. DTO로 바꿔 반환
    }

    @Transactional //수정도 하기 때문에 readOnly false임
    public UserResponse userUpdate(Long userId,UserUpdateRequest request){
        User foundUser = getUserOrThrow(userId);
        foundUser.changeName(request.name()); // request는 DTO, 이 DTO 에서 name()으로 String을 꺼내서 전달
        return UserResponse.from(foundUser);
    }

    @Transactional
    public void userDelete(Long userId){
        User foundUser = getUserOrThrow(userId);
        userRepository.delete(foundUser);
    }

}
