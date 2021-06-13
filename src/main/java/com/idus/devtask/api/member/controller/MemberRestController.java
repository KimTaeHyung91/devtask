package com.idus.devtask.api.member.controller;

import com.idus.devtask.api.member.dto.MemberSearchDto;
import com.idus.devtask.api.member.service.MemberService;
import com.idus.devtask.common.exception.custom.UserNotFoundException;
import com.idus.devtask.common.model.json.ResponseJson;
import com.idus.devtask.common.service.ResponseService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Member 컨트롤러
 *
 * @author dpwe231@gmail.com
 */
@Api(tags = {"1. Member"})
@RestController
@RequestMapping("/api")
@Slf4j
public class MemberRestController {

  /** MemberService */
  private final MemberService memberService;
  /** ResponseService */
  private final ResponseService responseService;

  /**
   * DI 주입
   *
   * @param memberService MemberService
   * @param responseService ResponseService
   */
  public MemberRestController(MemberService memberService, ResponseService responseService) {
    this.memberService = memberService;
    this.responseService = responseService;
  }

  /**
   * 전체 회원 조회
   *
   * @param memberSearchDto 검색 DTO
   * @return 전체 회원 리스트
   */
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-AUTH-TOKEN",
          value = "로그인 이후 accessToken",
          required = true,
          dataType = "String",
          paramType = "header"))
  @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
  @GetMapping("/members")
  public ResponseEntity<ResponseJson> findAllMembers(MemberSearchDto memberSearchDto) {
    return responseService.getJsonEntity(memberService.findAllMembers(memberSearchDto));
  }

  /**
   * 단일 회원의 상세정보를 조회
   *
   * @param idx 회원 idx
   * @return 단일 회원의 상세정보
   * @throws UserNotFoundException UserNotFoundException 예외
   */
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-AUTH-TOKEN",
          value = "로그인 이후 accessToken",
          required = true,
          dataType = "String",
          paramType = "header"))
  @ApiOperation(value = "회원 상세 정보 조회", notes = "단일 회원의 상세정보를 조회한다.")
  @GetMapping("/member/{idx}")
  public ResponseEntity<ResponseJson> findMemberById(
      @ApiParam(value = "회원 idx", required = true) @PathVariable("idx") Long idx)
      throws UserNotFoundException {
    return responseService.getJsonEntity(memberService.findMemberById(idx));
  }

  /**
   * 단일 회원의 모든 주문 목록을 조회
   *
   * @param idx 회원 idx
   * @return 단일 회원의 모든 주문 목록
   */
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-AUTH-TOKEN",
          value = "로그인 이후 accessToken",
          required = true,
          dataType = "String",
          paramType = "header"))
  @ApiOperation(value = "회원의 주문 조회", notes = "단일 회원의 모든 주문 목록을 조회한다.")
  @GetMapping("/member/{idx}/orders")
  public ResponseEntity<ResponseJson> findAllOrderByMemberId(
      @ApiParam(value = "회원 idx", required = true) @PathVariable("idx") Long idx) {
    return responseService.getJsonEntity(memberService.findAllOrderByMemberId(idx));
  }
}
