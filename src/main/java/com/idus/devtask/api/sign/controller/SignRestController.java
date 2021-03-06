package com.idus.devtask.api.sign.controller;

import com.idus.devtask.api.member.dto.MemberResponseDto;
import com.idus.devtask.api.member.dto.MemberSaveRequestDto;
import com.idus.devtask.api.member.dto.MemberSignInRequestDto;
import com.idus.devtask.api.member.entity.Member;
import com.idus.devtask.api.sign.dto.AuthRequestDto;
import com.idus.devtask.api.sign.dto.AuthResponseDto;
import com.idus.devtask.api.sign.service.AuthTokenService;
import com.idus.devtask.api.sign.service.SignService;
import com.idus.devtask.auth.JwtTokenProvider;
import com.idus.devtask.common.exception.custom.PasswordFailException;
import com.idus.devtask.common.exception.custom.SignupException;
import com.idus.devtask.common.exception.custom.UserExistException;
import com.idus.devtask.common.exception.custom.UserNotFoundException;
import com.idus.devtask.common.model.json.ResponseJson;
import com.idus.devtask.common.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * Sign-in/up Controller
 *
 * @author dpwe231@gmail.com
 */
@Api(tags = {"2. Sign"})
@RestController
@RequestMapping("/api")
@Slf4j
public class SignRestController {
  /** SignService */
  private final SignService signService;
  /** JwtTokenProvider */
  private final JwtTokenProvider jwtTokenProvider;
  /** PasswordEncoder */
  private final PasswordEncoder encoder;
  /** ResponseService */
  private final ResponseService responseService;
  /** AuthTokenService */
  private final AuthTokenService authTokenService;

  /**
   * DI ??????
   *
   * @param signService Sign-in/up Service
   * @param jwtTokenProvider JWT ?????? ?????????
   * @param encoder ???????????? ?????????
   * @param responseService Response ?????????
   * @param authTokenService refresh token ?????????
   */
  public SignRestController(
      SignService signService,
      JwtTokenProvider jwtTokenProvider,
      PasswordEncoder encoder,
      ResponseService responseService,
      AuthTokenService authTokenService) {
    this.signService = signService;
    this.jwtTokenProvider = jwtTokenProvider;
    this.encoder = encoder;
    this.responseService = responseService;
    this.authTokenService = authTokenService;
  }

  /**
   * ????????? ??????
   *
   * @param memberSignInRequestDto ????????? ?????? ??????
   * @return ?????? ??????
   * @throws UserNotFoundException UserNotFoundException ??????
   * @throws PasswordFailException PasswordFailException ??????
   */
  @ApiOperation(value = "?????????", notes = "????????? ???????????? ?????? ????????? ?????? ????????????.")
  @PostMapping("/sign-in")
  public ResponseEntity<ResponseJson> signIn(
      @ApiParam(value = "?????????, ???????????? ??????", required = true) @RequestBody
          MemberSignInRequestDto memberSignInRequestDto)
      throws UserNotFoundException, PasswordFailException {
    Member savedMember = signService.findMemberByEmail(memberSignInRequestDto.getEmail());
    if (!memberSignInRequestDto.getEmail().equals(savedMember.getEmail())) {
      throw new UserNotFoundException();
    }

    if (!encoder.matches(memberSignInRequestDto.getPassword(), savedMember.getPassword())) {
      throw new PasswordFailException();
    }
    return responseService.getJsonEntity(
        new AuthResponseDto(
            authTokenService.generateRefreshToken(savedMember.getIdx()),
            jwtTokenProvider.createToken(savedMember.getIdx(), savedMember.getRoles()),
            LocalDateTime.now()));
  }

  /**
   * ???????????? ??????
   *
   * @param authRequestDto ?????? ?????? ??????
   * @return ???????????? ??????
   */
  @ApiOperation(value = "????????????", notes = "??????????????? ??????.")
  @PostMapping("/sign-out")
  public ResponseEntity<ResponseJson> signOut(@RequestBody AuthRequestDto authRequestDto) {
    authTokenService.deleteAuthByMemberIdx(authRequestDto.getId());
    return responseService.getJsonEntity("???????????????????????????.");
  }

  /**
   * ???????????? ??????
   *
   * @param memberSaveRequestDto ?????? ?????? ??????
   * @return ???????????? ??????
   * @throws UserExistException UserExistException ??????
   */
  @ApiOperation(value = "????????????", notes = "??????????????? ??????.")
  @PostMapping("/sign-up")
  public ResponseEntity<ResponseJson> signup(
      @ApiParam(value = "??????????????? ????????? ??????(??????, ????????????, ????????????, ??????,  ?????????)", required = true)
          @Valid
          @RequestBody
          MemberSaveRequestDto memberSaveRequestDto)
      throws UserExistException {
    MemberResponseDto savedMember = signService.hasMember(memberSaveRequestDto.getEmail());
    if (memberSaveRequestDto.getEmail().equals(savedMember.getEmail())) {
      throw new UserExistException();
    }

    try {
      memberSaveRequestDto.setPassword(encoder.encode(memberSaveRequestDto.getPassword()));
      signService.saveMember(memberSaveRequestDto);
    } catch (SignupException se) {
      log.error("[SignupException] ::::: {}", se.getMessage());
    }

    return responseService.getJsonEntity(savedMember.getIdx(), "??????????????? ??????????????????.");
  }
}
