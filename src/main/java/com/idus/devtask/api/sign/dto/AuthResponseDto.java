package com.idus.devtask.api.sign.dto;

import com.idus.devtask.api.sign.entity.AuthToken;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 인증 응답 DTO
 *
 * @author dpwe231@gmail.com
 */
@Getter
public class AuthResponseDto {
  /** 인증 토큰 */
  private String authenticationToken;
  /** refresh 토큰 */
  private String refreshToken;
  /** 만료 일자 */
  private LocalDateTime expireAt;

  /**
   * 생성자 호출
   *
   * @param authToken refresh 토큰정보가 담김 AuthToken 객체
   * @param authenticationToken 인증 토큰
   * @param expireAt 만료 일자
   */
  public AuthResponseDto(AuthToken authToken, String authenticationToken, LocalDateTime expireAt) {
    this.refreshToken = authToken.getRefreshToken();
    this.authenticationToken = authenticationToken;
    this.expireAt = expireAt;
  }
}
