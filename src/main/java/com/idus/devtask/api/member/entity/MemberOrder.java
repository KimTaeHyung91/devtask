package com.idus.devtask.api.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Member Order Entity
 *
 * @author dpwe231@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberOrder {
  /** 주문 idx */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  /** 주문 번호 */
  @Column(length = 12, nullable = false)
  private String orderNumber;

  /** 제품명 */
  @Column(length = 100, nullable = false)
  private String productName;

  /** 결제일시 */
  @Column private LocalDateTime paymentDatetime;

  /** 회원의 idx 회원 - 주문 -> 1:N 관계로 설정하여 참조하기위해 설정 */
  @Column private Long memberIdx;
}
