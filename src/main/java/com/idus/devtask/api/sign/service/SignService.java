package com.idus.devtask.api.sign.service;

import com.idus.devtask.api.member.dto.MemberResponseDto;
import com.idus.devtask.api.member.dto.MemberSaveRequestDto;
import com.idus.devtask.api.member.entity.Member;
import com.idus.devtask.api.member.repository.MemberRepository;
import com.idus.devtask.common.exception.custom.SignupException;
import com.idus.devtask.common.exception.custom.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 로그인 서비스
 *
 * @author dpwe231@gmail.com
 */
@Service
@Slf4j
public class SignService {
  /** Member Repository */
  private final MemberRepository memberRepository;
  /**
   * DI 주입
   *
   * @param memberRepository Member Repository
   */
  public SignService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원 정보 조회
   *
   * @param email 이메일
   * @return 회원 정보
   * @throws UserNotFoundException UserNotFoundException 예외
   */
  @Transactional(readOnly = true)
  public Member findMemberByEmail(String email) throws UserNotFoundException {
    return memberRepository.findMemberByEmail(email).orElseThrow(UserNotFoundException::new);
  }

  /**
   * 회원 정보 저장
   *
   * @param memberSaveRequestDto 회원 정보 요청 객체
   * @return 회원 idx
   * @throws SignupException SignupException 예외
   */
  @Transactional
  public Long saveMember(MemberSaveRequestDto memberSaveRequestDto) throws SignupException {
    return memberRepository.saveAndFlush(memberSaveRequestDto.dtoBuilder()).getIdx();
  }

  /**
   * 회원 존재 유무 조회
   *
   * @param email 이메일
   * @return 회원 정보
   */
  @Transactional(readOnly = true)
  public MemberResponseDto hasMember(String email) {
    return memberRepository
        .findMemberByEmail(email)
        .map(MemberResponseDto::new)
        .orElse(new MemberResponseDto());
  }
}
