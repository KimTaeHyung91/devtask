package com.idus.devtask.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 커스템 예외처리 설정을 위한 EntryPoint
 *
 * @author dpwe231@gmail.com
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  /**
   * 커스텀 예외 처리를 위한 리다이렉트 설정
   *
   * @param request 요청 객체
   * @param response 응답 객체
   * @param authException 인증 예외 객체
   * @throws IOException IO 예외
   * @throws ServletException Servlet 예외
   */
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    response.sendRedirect("/exception/entrypoint");
  }
}
