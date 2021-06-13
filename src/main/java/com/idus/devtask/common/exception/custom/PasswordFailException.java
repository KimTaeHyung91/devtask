package com.idus.devtask.common.exception.custom;

/**
 * 비밀번호 예외
 *
 * @author dpwe231@gmail.com
 */
public class PasswordFailException extends Exception {
  /**
   * 생성자
   *
   * @param msg 메세지
   * @param exception 예외 정보 객체
   */
  public PasswordFailException(String msg, Exception exception) {
    super(msg, exception);
  }

  /**
   * 생성자
   *
   * @param msg
   */
  public PasswordFailException(String msg) {
    super(msg);
  }

  /** 생성자 */
  public PasswordFailException() {
    super("비밀번호가 잘못되었습니다.");
  }
}
