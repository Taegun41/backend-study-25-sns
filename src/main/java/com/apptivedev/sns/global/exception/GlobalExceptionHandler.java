package com.apptivedev.sns.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// 모든 Controller에서 올라온 예외를 한 곳에서 받아 ErrorResponse로 응답한다
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 4-1. 우리가 Service에서 던진 예외 (사용자 없음, 중복 이메일, 이후 로그인 실패·토큰 오류 등 전부)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();                 // 예외가 들고 온 ErrorCode 꺼내기
        return ResponseEntity.status(errorCode.getHttpStatus()) // ErrorCode에 정해 둔 상태 코드
                .body(ErrorResponse.from(errorCode));           // ErrorCode의 코드와 메시지
    }

    // 4-2. @Valid 검증 실패 → 400, DTO에 적어 둔 필드별 메시지 사용
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();                           // 첫 번째로 실패한 필드의 메시지
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.from(errorCode, message));  // 3-2: 코드는 C001, 메시지는 DTO의 것
    }

    // 4-3. PathVariable 타입 변환 실패 (/users/abc) → 400
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.from(errorCode));
    }

    // 4-4. 위에서 처리하지 못한 모든 예외 (최후의 방어선) → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("예상하지 못한 오류", e);                        // 원인은 서버 로그에만 기록
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.from(errorCode));           // 클라이언트에는 일반적인 안내만
    }
}