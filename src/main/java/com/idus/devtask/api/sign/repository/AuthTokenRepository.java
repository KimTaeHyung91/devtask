package com.idus.devtask.api.sign.repository;

import com.idus.devtask.api.sign.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** AuthToken Repository */
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
  /**
   * 회원 별 refresh 토큰 조회
   *
   * @param memberIdx 회원 idx
   * @return refresh 토큰 정보
   */
  Optional<AuthToken> findAuthTokenByMemberIdx(Long memberIdx);

  /**
   * 회원의 refresh 토큰 삭제
   *
   * @param memberIdx 회원 idx
   */
  void deleteAuthByMemberIdx(Long memberIdx);
}
