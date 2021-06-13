package com.idus.devtask.api.member.dto;

import com.idus.devtask.common.model.dto.CommonSearchDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 검색 DTO 파일
 *
 * @author dpwe231@gmail.com
 */
@Getter
@Setter
@ToString
@ApiModel(value = "검색 요청 정보", description = "검색 요청 정보 DTO")
public class MemberSearchDto extends CommonSearchDto implements Serializable {
  /** 검색 후 마지막 Idx */
  @ApiModelProperty(value = "검색 후 마지막 Idx", notes = "검색 후 마지막 Idx", example = "20")
  private Long searchIdx;
  /** 검색 할 이름 */
  @ApiModelProperty(value = "검색 할 이름", notes = "검색 할 이름", example = "홍길동")
  private String searchName;
  /** 검색 할 이메일 */
  @ApiModelProperty(value = "검색 할 이메일", notes = "검색 할 이메일", example = "test@test.com")
  private String searchEmail;
}
