package com.idus.devtask.common.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 공통 검색 클래스
 *
 * @author dpwe231@gmail.com
 */
@Getter
@Setter
@ApiModel(value = "공통 검색 클래스", description = "공통 검색 클래스")
public class CommonSearchDto {
  /** 페이지 사이즈, 디폴트 10 */
  @ApiModelProperty(value = "페이지 사이즈", example = "10")
  private int pageSize = 10;
}
