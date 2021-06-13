package com.idus.devtask.common.service;

import com.idus.devtask.common.model.json.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * 공통 응답 서비스
 *
 * @author dpwe231@gmail.com
 */
@Service
@Slf4j
public class ResponseService {
  /**
   * 응답 객체 생성
   *
   * @param msg 메시지
   * @return 응답 객체
   */
  public ResponseEntity<ResponseJson> getJsonEntity(String... msg) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return new ResponseEntity<>(
        ResponseJson.builder()
            .status(HttpStatus.OK.value())
            .msg(msg.length != 0 ? msg[0] : null)
            .build(),
        headers,
        HttpStatus.OK);
  }

  /**
   * 응답 객체 생성
   *
   * @param status 응답 코드
   * @param msg 메시지
   * @return 응답 객체
   */
  public ResponseEntity<ResponseJson> getJsonEntity(int status, String msg) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return new ResponseEntity<>(
            ResponseJson.builder()
                    .status(status)
                    .msg(msg)
                    .build(),
            headers,
            HttpStatus.OK);
  }

  /**
   * 응답 객체 생성
   *
   * @param data 데이터
   * @param msg 메시지
   * @param <T> 제네릭 타입
   * @return 응답 객체 생성
   */
  public <T> ResponseEntity<ResponseJson> getJsonEntity(T data, String... msg) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return new ResponseEntity<>(
        ResponseJson.builder()
            .data(data)
            .status(HttpStatus.OK.value())
            .msg(msg.length != 0 ? msg[0] : null)
            .build(),
        headers,
        HttpStatus.OK);
  }
}
