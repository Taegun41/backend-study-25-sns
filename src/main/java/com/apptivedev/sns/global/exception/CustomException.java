package com.apptivedev.sns.global.exception;

import lombok.Getter;

// 우리가 직접 던지는 예외의 부모: ErrorCode를 담아서 GlobalExceptionHandler까지 실어 나른다
@Getter
public class CustomException extends RuntimeException {

    //ErrorCode를 담을 필드 선언 (private final)
    private final ErrorCode errorCode;
    //  첫 줄: super(...)로 부모(RuntimeException)에게 메시지를 넘긴다 → 넘길 값은 errorCode의 메시지
    //  둘째 줄: 받은 errorCode를 필드에 저장한다
    public CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
