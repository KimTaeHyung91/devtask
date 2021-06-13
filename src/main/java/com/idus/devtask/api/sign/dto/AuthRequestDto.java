package com.idus.devtask.api.sign.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 인증 요청 DTO
 *
 * @author dpwe231@gmail.com
 */
@Getter
@Setter
@ApiModel(value = "인증 요청 정보", description = "인증 요청 정보")
public class AuthRequestDto {
  /** 회원 idx */
  @ApiModelProperty(value = "회원 idx", example = "1")
  private Long id;
}
