package com.idus.devtask.common.exception.custom;

/**
 * 회원정보 예외
 *
 * @author dpwe231@gmail.com
 */
public class UserNotFoundException extends Exception {
  /**
   * 생성자
   *
   * @param msg
   * @param exception
   */
  public UserNotFoundException(String msg, Exception exception) {
    super(msg, exception);
  }

  /**
   * 생성자
   *
   * @param msg
   */
  public UserNotFoundException(String msg) {
    super(msg);
  }

  /** 생성자 */
  public UserNotFoundException() {
    super("회원 정보를 찾을 수 없습니다.");
  }
}
