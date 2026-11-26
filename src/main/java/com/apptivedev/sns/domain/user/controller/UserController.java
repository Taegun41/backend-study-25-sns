package com.apptivedev.sns.domain.user.controller;

import com.apptivedev.sns.domain.user.dto.UserResponse;
import com.apptivedev.sns.domain.user.dto.UserUpdateRequest;
import com.apptivedev.sns.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //생성자 주입
@RestController//요청을 받고 반환값을 Json으로 응답
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}") //Get -> read요청임을 알 수 있음
    public UserResponse userRead(@PathVariable("userId") Long userId){ //pathVariable : url경로값을 변수에 넣기
        return userService.userRead(userId);
    }

    @PatchMapping("/{userId}")
    public UserResponse userUpdate(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest request){
        return userService.userUpdate(userId, request);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable("userId") Long userId){
        userService.userDelete(userId);
    }
}
