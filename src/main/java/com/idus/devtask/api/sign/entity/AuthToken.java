package com.idus.devtask.api.sign.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/** Refresh 토큰 Entity */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthToken {

  /** 회원 idx */
  @Id private Long memberIdx;

  /** refresh 토큰 */
  private String refreshToken;

  /** 생성 일자 */
  @Column(nullable = false)
  private LocalDateTime createAt;

  /**
   * 빌더 패턴으로 생성자 호출
   *
   * @param memberIdx 회원 idx
   * @param refreshToken refresh 토큰
   * @param createAt 생성일자
   */
  @Builder
  public AuthToken(Long memberIdx, String refreshToken, LocalDateTime createAt) {
    this.memberIdx = memberIdx;
    this.refreshToken = refreshToken;
    this.createAt = createAt;
  }
}
