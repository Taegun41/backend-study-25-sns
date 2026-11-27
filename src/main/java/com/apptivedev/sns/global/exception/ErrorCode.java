package com.apptivedev.sns.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

// 에러 목록: 에러마다 (HTTP 상태 코드, 고유 코드, 안내 메시지)를 한 묶음으로 정의한다
@Getter
@RequiredArgsConstructor
public enum ErrorCode {


    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "U002",  "이미 가입된 이메일 입니다."),

    // Post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "게시글을 찾을 수 없습니다."),
    NO_UPDATE_FIELD(HttpStatus.BAD_REQUEST, "P002", "수정할 필드(title, content)를 하나 이상 보내야 합니다."),
    BLANK_POST_TITLE(HttpStatus.BAD_REQUEST, "P003", "제목은 빈 문자열이나 공백일 수 없습니다."),
    BLANK_POST_CONTENT(HttpStatus.BAD_REQUEST, "P004", "내용은 빈 문자열이나 공백일 수 없습니다."),

    // Common
    INVALID_INPUT(HttpStatus.BAD_REQUEST,"C001", "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"C999", "서버 내부 오류입니다."),

    //Auth(인증)
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED,"A001","이메일 혹은 비밀번호가 올바르지 않습니다.");

    private final HttpStatus httpStatus;   // 응답 상태 코드
    private final String code;             // 프론트엔드가 분기 처리에 쓰는 고유 코드
    private final String message;          // 사용자에게 보여 줄 메시지
}
