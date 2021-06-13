package com.idus.devtask.common.exception.custom;

/**
 * 회원가입 예외
 *
 * @author dpwe231@gmail.com
 */
public class SignupException extends Exception {
  /**
   * 생성자
   *
   * @param msg 메세지
   * @param exception 예외 정보 객체
   */
  public SignupException(String msg, Exception exception) {
    super(msg, exception);
  }

  /**
   * 생성자
   *
   * @param msg 메세지
   */
  public SignupException(String msg) {
    super(msg);
  }

  /** 생성자 */
  public SignupException() {
    super("회원가입을 하는 도중 오류가 발생했습니다.");
  }
}
