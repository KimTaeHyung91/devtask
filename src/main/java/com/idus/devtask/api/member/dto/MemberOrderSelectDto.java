package com.idus.devtask.api.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Member Order 조회 DTO 파일
 *
 * @author dpwe231@gmail.com
 */
@Getter
@Setter
public class MemberOrderSelectDto {
  /** 주문 idx */
  private Long idx;
  /** 주문번호 */
  private String orderNumber;
  /** 제품명 */
  private String productName;
  /** 결제일시 */
  private LocalDateTime paymentDatetime;
}
