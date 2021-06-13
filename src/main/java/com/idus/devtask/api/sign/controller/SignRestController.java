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
   * DI 주입
   *
   * @param signService Sign-in/up Service
   * @param jwtTokenProvider JWT 토큰 핸들링
   * @param encoder 패스워드 인코드
   * @param responseService Response 서비스
   * @param authTokenService refresh token 서비스
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
   * 로그인 처리
   *
   * @param memberSignInRequestDto 로그인 요청 객체
   * @return 인증 정보
   * @throws UserNotFoundException UserNotFoundException 예외
   * @throws PasswordFailException PasswordFailException 예외
   */
  @ApiOperation(value = "로그인", notes = "이메일 기반으로 회원 로그인 인증 처리한다.")
  @PostMapping("/sign-in")
  public ResponseEntity<ResponseJson> signIn(
      @ApiParam(value = "이메일, 비밀번호 정보", required = true) @RequestBody
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
   * 로그아웃 처리
   *
   * @param authRequestDto 인증 요청 객체
   * @return 로그아웃 상태
   */
  @ApiOperation(value = "로그아웃", notes = "로그아웃을 한다.")
  @PostMapping("/sign-out")
  public ResponseEntity<ResponseJson> signOut(@RequestBody AuthRequestDto authRequestDto) {
    authTokenService.deleteAuthByMemberIdx(authRequestDto.getId());
    return responseService.getJsonEntity("로그아웃되었습니다.");
  }

  /**
   * 회원가입 처리
   *
   * @param memberSaveRequestDto 회원 요청 객체
   * @return 회원가입 상태
   * @throws UserExistException UserExistException 예외
   */
  @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
  @PostMapping("/sign-up")
  public ResponseEntity<ResponseJson> signup(
      @ApiParam(value = "회원가입에 필요한 정보(이름, 비밀번호, 전화번호, 별명,  이메일)", required = true)
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

    return responseService.getJsonEntity(savedMember.getIdx(), "회원가입을 완료했습니다.");
  }
}
