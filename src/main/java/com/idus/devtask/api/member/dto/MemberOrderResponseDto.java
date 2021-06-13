package com.idus.devtask.api.member.dto;

import com.idus.devtask.api.member.entity.MemberOrder;
import com.idus.devtask.common.util.DateUtil;
import lombok.Getter;

/**
 * 주문 응답 DTO
 *
 * @author dpwe231@gmail.com
 */
@Getter
public class MemberOrderResponseDto {
  /** 주문 idx */
  private Long idx;
  /** 주문번호 */
  private String orderNumber;
  /** 제품명 */
  private String productName;
  /** 결제일시 */
  private String paymentDatetime;

  /**
   * 생성자
   *
   * @param memberOrder 주문정보
   */
  public MemberOrderResponseDto(MemberOrder memberOrder) {
    this.idx = memberOrder.getIdx();
    this.orderNumber = memberOrder.getOrderNumber();
    this.productName = memberOrder.getProductName();
    this.paymentDatetime = DateUtil.formatDate("datetimestamp", memberOrder.getPaymentDatetime());
  }
}
