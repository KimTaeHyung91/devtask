package com.idus.devtask.common.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * API Request Advice 설정
 *
 * @author dpwe231@gmail.com
 */
@RestControllerAdvice
public class ApiAdvice {
  /**
   * API 요청 시 데이터 유효성 조건 예외 처리
   *
   * @param methodArgumentNotValidException
   * @return 유효성 검증안된 데이터 객체
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidException(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    Map<String, String> errorMap = new HashMap<>();
    methodArgumentNotValidException
        .getBindingResult()
        .getAllErrors()
        .forEach(e -> errorMap.put(((FieldError) e).getField(), e.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errorMap);
  }
}
