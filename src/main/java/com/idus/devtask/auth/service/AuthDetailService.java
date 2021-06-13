package com.idus.devtask.auth.service;

import com.idus.devtask.api.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security 의 사용자 조회 서비스
 *
 * @author dpwe231@gmail.com
 */
@Service
public class AuthDetailService implements UserDetailsService {
  /** MemberRepository */
  private final MemberRepository memberRepository;

  /**
   * DI 주입
   *
   * @param memberRepository MemberRepository
   */
  public AuthDetailService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 사용자 존재인지 판단 수행
   *
   * @param memberId 회원 idx
   * @return 회원정보
   * @throws UsernameNotFoundException UsernameNotFoundException 예외
   */
  @Override
  public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
    return memberRepository
        .findById(Long.valueOf(memberId))
        .orElseThrow(() -> new UsernameNotFoundException(memberId));
  }
}
