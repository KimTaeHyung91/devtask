package com.idus.devtask.api.member.repository;

import com.idus.devtask.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Member JPA */
public interface MemberRepository extends JpaRepository<Member, Long> {
  /**
   * 단일 회원 조회
   *
   * @param email 이메일
   * @return 회원 정보
   */
  Optional<Member> findMemberByEmail(String email);

  /**
   * 단일 회원 조회
   *
   * @param id 회원 idx
   * @return 회원 정보
   */
  @Override
  Optional<Member> findById(Long id);
}
