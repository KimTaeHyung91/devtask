package com.idus.devtask.common.advice;

import com.idus.devtask.common.exception.custom.*;
import com.idus.devtask.common.model.json.ResponseJson;
import com.idus.devtask.common.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 예외 처리 Advice
 *
 * @author dpwe231@gmail.com
 */
@RestControllerAdvice
public class ExceptionAdvice {

  /** 응답 객체 서비스 */
  private final ResponseService responseService;

  /**
   * DI 주입
   *
   * @param responseService 응답 객체
   */
  public ExceptionAdvice(ResponseService responseService) {
    this.responseService = responseService;
  }

  /**
   * UserNotFoundException 예외
   *
   * @param request 요청 객체
   * @param e 예외 객체
   * @return 예외 내용
   */
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ResponseJson> userNotException(
      HttpServletRequest request, UserNotFoundException e) {
    return responseService.getJsonEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }

  /**
   * SignupException 예외
   *
   * @param request 요청 객체
   * @param e 예외 객체
   * @return 예외 내용
   */
  @ExceptionHandler(SignupException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ResponseJson> signupException(
      HttpServletRequest request, SignupException e) {
    return responseService.getJsonEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }

  /**
   * UserExistException 예외
   *
   * @param request 요청 객체
   * @param e 예외 객체
   * @return 예외 내용
   */
  @ExceptionHandler(UserExistException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ResponseJson> userExistException(
      HttpServletRequest request, UserExistException e) {
    return responseService.getJsonEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }

  /**
   * PasswordFailException 예외
   *
   * @param request 요청 객체
   * @param e 예외 객체
   * @return 예외 내용
   */
  @ExceptionHandler(PasswordFailException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ResponseJson> passwordFailException(
      HttpServletRequest request, PasswordFailException e) {
    return responseService.getJsonEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }

  /**
   * AuthenticationException 예외
   *
   * @param request 요청 객체
   * @param e 예외 객체
   * @return 예외 내용
   */
  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ResponseJson> authenticationException(
      HttpServletRequest request, AuthenticationException e) {
    return responseService.getJsonEntity(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
  }
}
