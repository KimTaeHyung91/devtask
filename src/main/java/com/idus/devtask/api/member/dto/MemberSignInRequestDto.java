package com.idus.devtask.api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 로그인 정보 DTO
 *
 * @author dpwe231@gmail.com
 */
@Getter
@Setter
@ApiModel(value = "사용자 로그인 정보", description = "사용자 로그인 정보 DTO")
public class MemberSignInRequestDto {
  /** 사용자 이메일 */
  @ApiModelProperty(value = "이메일", example = "test@test.com")
  String email;
  /** 사용자 비밀번호 */
  @ApiModelProperty(value = "비밀번호", example = "aaaaAbbbc!")
  String password;
}
