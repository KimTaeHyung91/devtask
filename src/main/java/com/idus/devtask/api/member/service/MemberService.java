package com.idus.devtask.api.member.service;

import com.idus.devtask.api.member.dto.MemberOrderResponseDto;
import com.idus.devtask.api.member.dto.MemberOrderSelectDto;
import com.idus.devtask.api.member.dto.MemberResponseDto;
import com.idus.devtask.api.member.dto.MemberSearchDto;
import com.idus.devtask.api.member.entity.Member;
import com.idus.devtask.api.member.repository.MemberQueryRepository;
import com.idus.devtask.api.member.repository.MemberRepository;
import com.idus.devtask.common.exception.custom.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Member 서비스
 *
 * @author dpwe231@gmail.com
 */
@Service
@Slf4j
public class MemberService {

  /** MemberRepository */
  private final MemberRepository memberRepository;
  /** MemberQueryRepository */
  private final MemberQueryRepository memberQueryRepository;

  /**
   * DI 주입
   *
   * @param memberRepository MemberRepository
   * @param memberQueryRepository MemberQueryRepository
   */
  public MemberService(
      MemberRepository memberRepository, MemberQueryRepository memberQueryRepository) {
    this.memberRepository = memberRepository;
    this.memberQueryRepository = memberQueryRepository;
  }

  /**
   * 전체 회원 조회
   *
   * @param memberSearchDto 검색 DTO
   * @return 전체 회원 리스트
   */
  @Transactional(readOnly = true)
  public List<MemberResponseDto> findAllMembers(MemberSearchDto memberSearchDto) {
    return memberQueryRepository.findAllMembersByNameOrEmail(memberSearchDto).stream()
        .map(
            tuple ->
                new MemberResponseDto(
                    Objects.requireNonNull(tuple.get(0, Member.class)),
                    tuple.get(1, MemberOrderSelectDto.class)))
        .collect(Collectors.toList());
  }

  /**
   * 단일 회원 조회
   *
   * @param idx 회원 idx
   * @return 단일 회원 상세 정보
   */
  @Transactional(readOnly = true)
  public MemberResponseDto findMemberById(Long idx) throws UserNotFoundException {
    return memberRepository
        .findById(idx)
        .map(MemberResponseDto::new)
        .orElseThrow(UserNotFoundException::new);
  }

  /**
   * 회원별 주문 정보 리스트
   *
   * @param idx 회원 idx
   * @return 회원별 주문 리스트
   */
  public List<MemberOrderResponseDto> findAllOrderByMemberId(Long idx) {
    return memberQueryRepository.findAllOrderByMemberId(idx).stream()
        .map(MemberOrderResponseDto::new)
        .collect(Collectors.toList());
  }
}
