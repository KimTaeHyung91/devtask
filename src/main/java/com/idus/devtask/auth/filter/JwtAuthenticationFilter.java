package com.idus.devtask.auth.filter;

import com.idus.devtask.auth.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * JWT Filter
 *
 * @author dpwe231@gmail.com
 */
@Slf4j
public class JwtAuthenticationFilter extends GenericFilter {
  /** JwtTokenProvider */
  private final JwtTokenProvider jwtTokenProvider;

  /**
   * DI 주입
   *
   * @param jwtTokenProvider JwtTokenProvider
   */
  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  /**
   * 토큰이 유효한지 판단 수행
   *
   * @param request 요청 객체
   * @param response 응답 객체
   * @param chain 필터 체인 객체
   * @throws IOException IO 예외
   * @throws ServletException Servlet 예외
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
    if (token != null
        && jwtTokenProvider.validateToken(token)
        && jwtTokenProvider.validateRefreshToken(token)) {
      Authentication authentication = jwtTokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }
}
