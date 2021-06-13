package com.idus.devtask.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 날짜 유틸
 *
 * @author dpwe231@gmail.com
 */
public enum DateUtil {
  /** 날짜 -> 2021-01-01 */
  FORMAT_DATE(
      "date",
      localDateTime -> {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      }),
  /** 날짜 -> 2021-01-01 00:00 */
  FORMAT_DATETIME(
      "datetime",
      localDateTime -> {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
      }),
  /** 날짜 -> 2021-01-01 00:00:00 */
  FORMAT_DATETIMESTAMP(
      "datetimestamp",
      localDateTime -> {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      });

  /** 날짜 타입 */
  private String type;
  /** Formatter 인터페이스 */
  private Formatter formatter;

  /**
   * private 생성자 호출
   *
   * @param type 날짜 타입
   * @param formatter Formatter 인터페이스
   */
  DateUtil(String type, Formatter formatter) {
    this.type = type;
    this.formatter = formatter;
  }

  /** 내부 Formatter 인터페이스 */
  private interface Formatter {
    /**
     * 날짜 포맷팅 선언
     *
     * @param localDateTime 날짜
     * @return 포맷팅된 날짜
     */
    String formatDate(LocalDateTime localDateTime);
  }

  /**
   * 날짜 포맷팅 구현
   *
   * @param type 날짜 타입
   * @param localDateTime 날짜
   * @return 포맷팅 된 날짜
   */
  public static String formatDate(String type, LocalDateTime localDateTime) {
    DateUtil factory =
        Arrays.stream(DateUtil.values())
            .filter(dateFormat -> dateFormat.type.equals(type))
            .findFirst()
            .get();
    return factory.formatter.formatDate(localDateTime);
  }
}
