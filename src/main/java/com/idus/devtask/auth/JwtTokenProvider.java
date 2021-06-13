package com.idus.devtask.auth;

import com.idus.devtask.api.sign.service.AuthTokenService;
import com.idus.devtask.auth.service.AuthDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * JWT Token 핸들링
 *
 * @author dpwe231@gmail.com
 */
@Component
@Slf4j
public class JwtTokenProvider {

  /** 비밀키 */
  @Value("${spring.jwt.secret}")
  private String secretKey;

  /** 유효시간 (10분) */
  private final long tokenValidSecond = 1000 * 60 * 10;

  /** AuthDetailService */
  private final AuthDetailService authDetailService;
  /** AuthTokenService */
  private final AuthTokenService authTokenService;

  /**
   * DI 주입
   *
   * @param authDetailService AuthDetailService
   * @param authTokenService AuthTokenService
   */
  public JwtTokenProvider(AuthDetailService authDetailService, AuthTokenService authTokenService) {
    this.authDetailService = authDetailService;
    this.authTokenService = authTokenService;
  }

  /** 비밀키 초기화 */
  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  /**
   * 인증 토큰 생성
   *
   * @param idx 회원 idx
   * @param roles 인증 유형
   * @return 토큰
   */
  public String createToken(Long idx, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(String.valueOf(idx));
    claims.put("roles", roles);
    Date now = new Date();
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + tokenValidSecond))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  /**
   * 인증 조회
   *
   * @param token 인증 토큰
   * @return 인증 정보
   */
  public Authentication getAuthentication(String token) {
    UserDetails userDetails =
        authDetailService.loadUserByUsername(this.getMemberIdFromToken(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  /**
   * 인증 토큰 기반으로 회원 정보 조회
   *
   * @param token 인증
   * @return 회원 idx
   */
  public String getMemberIdFromToken(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * 헤더에 포함된 토큰값 조회
   *
   * @param request 요청 객체
   * @return 토큰값
   */
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader("X-AUTH-TOKEN");
  }

  /**
   * 인증 토큰 유효성 체크
   *
   * @param token 인증 토큰
   * @return 인증 토큰 유효 여부
   */
  public Boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      log.error("token expired :: {}", e.getMessage());
      return false;
    }
  }

  /**
   * refresh 토큰 유효성 체크
   *
   * @param token 인증 토큰
   * @return refresh 토큰 유효 여부
   */
  public Boolean validateRefreshToken(String token) {
    try {
      Long id = Long.valueOf(this.getMemberIdFromToken(token));
      String refreshToken = authTokenService.validateRefreshToken(id).getRefreshToken();
      return !"".equals(refreshToken) && refreshToken != null;
    } catch (Exception e) {
      log.error("invalid refresh token");
      return false;
    }
  }
}
