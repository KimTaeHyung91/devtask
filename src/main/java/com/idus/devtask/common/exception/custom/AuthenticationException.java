package com.idus.devtask.common.exception.custom;

/**
 * 리소스 접근 권한 예외
 *
 * @author dpwe231@gmail.com
 */
public class AuthenticationException extends Exception {
  /**
   * 생성자
   *
   * @param msg 메시지
   * @param exception 예외 정보 객체
   */
  public AuthenticationException(String msg, Exception exception) {
    super(msg, exception);
  }

  /**
   * 생성자
   *
   * @param msg 메시지
   */
  public AuthenticationException(String msg) {
    super(msg);
  }

  /** 생성자 */
  public AuthenticationException() {
    super("해당 리소스에 접근 권한이 없습니다.");
  }
}
