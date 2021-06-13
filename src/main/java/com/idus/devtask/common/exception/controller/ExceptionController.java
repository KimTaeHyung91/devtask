package com.idus.devtask.common.exception.controller;

import com.idus.devtask.common.exception.custom.AuthenticationException;
import com.idus.devtask.common.model.json.ResponseJson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom 에외 처리하기 위한 Security Entrypoint 설정
 *
 * @author dpwe231@gmail.com
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {
  /**
   * 인증 예외 처리
   *
   * @return
   * @throws AuthenticationException
   */
  @GetMapping("/entrypoint")
  public ResponseEntity<ResponseJson> entrypointException() throws AuthenticationException {
    throw new AuthenticationException();
  }
}
