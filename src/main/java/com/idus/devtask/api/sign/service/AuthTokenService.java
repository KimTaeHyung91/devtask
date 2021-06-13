package com.idus.devtask.api.sign.service;

import com.idus.devtask.api.sign.entity.AuthToken;
import com.idus.devtask.api.sign.repository.AuthTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Refresh Token 서비스
 *
 * @author dpwe231@gmail.com
 */
@Service
@Slf4j
public class AuthTokenService {
  /** AuthTokenRepository */
  private final AuthTokenRepository authTokenRepository;

  /**
   * DI 주입
   *
   * @param authTokenRepository AuthTokenRepository
   */
  public AuthTokenService(AuthTokenRepository authTokenRepository) {
    this.authTokenRepository = authTokenRepository;
  }

  /**
   * refresh token 생성
   *
   * @param memberIdx 회원 idx
   * @return refresh token
   */
  @Transactional
  public AuthToken generateRefreshToken(Long memberIdx) {
    return authTokenRepository.save(
        AuthToken.builder()
            .memberIdx(memberIdx)
            .refreshToken(UUID.randomUUID().toString())
            .createAt(LocalDateTime.now())
            .build());
  }

  /**
   * refresh 토큰 유효성 체크
   *
   * @param memberIdx 회원 idx
   * @return refresh 토큰 정보
   */
  @Transactional(readOnly = true)
  public AuthToken validateRefreshToken(Long memberIdx) {
    return authTokenRepository.findAuthTokenByMemberIdx(memberIdx).orElse(null);
  }

  /**
   * refresh 토큰 삭제
   *
   * @param memberIdx 회원 idx
   */
  @Transactional
  public void deleteAuthByMemberIdx(Long memberIdx) {
    authTokenRepository.deleteAuthByMemberIdx(memberIdx);
  }
}
