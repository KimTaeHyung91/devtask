package com.idus.devtask.common.exception.custom;

/**
 * 이메일 예외
 *
 * @author dpwe231@gmail.com
 */
public class UserExistException extends Exception {
  /**
   * 생성자
   *
   * @param msg 메세지
   * @param exception 예외 정보 객체
   */
  public UserExistException(String msg, Exception exception) {
    super(msg, exception);
  }

  /**
   * 생성자
   *
   * @param msg 메세지
   */
  public UserExistException(String msg) {
    super(msg);
  }

  /** 생성자 */
  public UserExistException() {
    super("존재하는 이메일이 있습니다.");
  }
}
