package com.apptivedev.sns.domain.user.controller;

import com.apptivedev.sns.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor //생성자 주입
@RestController//요청을 받고 반환값을 Json으로 응답
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}") //Get -> read요청임을 알 수 있음
    public UserResponse userRead(@PathVariable("userId") Long userId){ //pathVariable : url경로값을 변수에 넣기
        return userService.userRead(userId);
    }
}
