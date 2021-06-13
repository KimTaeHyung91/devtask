package com.idus.devtask.common.util;

import java.util.Arrays;

/**
 * 전화번호 자리수 유틸
 *
 * @author dpwe231@gmail.com
 */
public enum TelUtil {
  /** 8자리 -> 123-12345 */
  TEL_EIGHT(
      8,
      tel -> {
        return tel.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
      }),
  /** 10자리 -> 123-123-1234 */
  TEL_TEN(
      10,
      tel -> {
        return tel.replaceFirst("(^02|[0-9]{3})([0-9]{3})([0-9]{4})$", "$1-$2-$3");
      }),
  /** 11자리 -> 123-1234-1234 */
  TEL_ELEVEN(
      11,
      tel -> {
        return tel.replaceFirst("(^02|[0-9]{3})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
      }),
  /** 12자리 -> 1234-1234-1234 */
  TEL_TWELVE(
      12,
      tel -> {
        return tel.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
      });

  /** 자릿수 */
  private int digit;
  /** Formatter 인터페이스 */
  private Formatter formatter;

  /**
   * private 생성자 호출
   *
   * @param digit 자릿수
   * @param formatter Formatter 인터페이스
   */
  TelUtil(int digit, Formatter formatter) {
    this.digit = digit;
    this.formatter = formatter;
  }

  /** 내부 Formatter 인터페이스 */
  private interface Formatter {
    /**
     * 전화번호 포맷팅 선언
     *
     * @param tel 전화번호
     * @return 포맷팅된 전화번호
     */
    String formatTel(String tel);
  }

  /**
   * 전화번호 포맷팅 구현체
   *
   * @param digit 자릿수
   * @param tel 전화번호
   * @return 포매팅된 전화번호
   */
  public static String formatTel(int digit, String tel) {
    TelUtil factory =
        Arrays.stream(TelUtil.values())
            .filter(telFormat -> telFormat.digit == digit)
            .findFirst()
            .get();
    return factory.formatter.formatTel(tel);
  }
}
