package com.apptivedev.sns.global.exception;

// 에러 응답 DTO: 모든 에러를 {"code": "...", "message": "..."} 형태로 통일한다
public record ErrorResponse(
        String code,
        String message
) {

    //보편적인 error 상황 통제
    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());

    }
    //다양한 error에 대해 더 자세히 안내
    public static ErrorResponse from(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getCode(), message);
    }
}
