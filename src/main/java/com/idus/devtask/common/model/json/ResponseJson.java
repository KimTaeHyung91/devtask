package com.idus.devtask.common.model.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 응답 JSON 객체
 *
 * @param <T> 데이터 타입
 */
@Getter
@Setter
@ApiModel(value = "JSON 응답", description = "JSON 응답")
public class ResponseJson<T> {
  /** 상태값 */
  @ApiModelProperty(value = "상태", example = "200")
  private int status;
  /** 메세지 */
  @ApiModelProperty(value = "메세지", example = "로그아웃 되었습니다.")
  private String msg;
  /** 데이터 */
  @ApiModelProperty(value = "데이터", example = "{idx:1, name: kim}")
  private T data;

  /**
   * 빌더 패턴으로 생성자 호출
   *
   * @param status 상태값
   * @param msg 메세지
   * @param data 데이터
   */
  @Builder
  public ResponseJson(int status, String msg, T data) {
    this.status = status;
    this.msg = msg;
    this.data = data;
  }
}
