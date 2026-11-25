package com.apptivedev.sns.domain.user.dto;

import com.apptivedev.sns.domain.user.entity.User;

// 사용자 응답 양식 (password는 절대 포함하지 않는다)
public record UserResponse(
        Long id,
        String email,
        String name
) {
    // 엔티티 → 응답 DTO 변환
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }
}
