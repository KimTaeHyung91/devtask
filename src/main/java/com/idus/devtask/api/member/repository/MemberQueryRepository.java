package com.idus.devtask.api.member.repository;

import com.idus.devtask.api.member.dto.MemberOrderSelectDto;
import com.idus.devtask.api.member.dto.MemberSearchDto;
import com.idus.devtask.api.member.entity.Member;
import com.idus.devtask.api.member.entity.MemberOrder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.idus.devtask.api.member.entity.QMember.member;
import static com.idus.devtask.api.member.entity.QMemberOrder.memberOrder;

/**
 * Member 조회 Repository
 *
 * @author dpwe231@gmail.com
 */
@Repository
@Slf4j
public class MemberQueryRepository extends QuerydslRepositorySupport {
  /** JPAQueryFactory */
  private final JPAQueryFactory jpaQueryFactory;

  /**
   * DI 주입
   *
   * @param jpaQueryFactory JPAQueryFactory
   */
  public MemberQueryRepository(JPAQueryFactory jpaQueryFactory) {
    super(Member.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  /**
   * 각 회원별로 마지막 주문을 포함한 회원 리스트 조회
   *
   * @param memberSearchDto 검색 DTO
   * @return 회원 리스트
   */
  public List<Tuple> findAllMembersByNameOrEmail(MemberSearchDto memberSearchDto) {
    return jpaQueryFactory
        .select(
            member,
            Projections.fields(
                MemberOrderSelectDto.class,
                memberOrder.idx.max().as("idx"),
                memberOrder.orderNumber.max().as("orderNumber"),
                memberOrder.productName.max().as("productName"),
                memberOrder.paymentDatetime.max().as("paymentDatetime")))
        .from(member)
        .leftJoin(memberOrder)
        .on(member.Idx.eq(memberOrder.memberIdx))
        .where(
            this.ltIdx(memberSearchDto.getSearchIdx()),
            this.eqName(memberSearchDto.getSearchName()),
            this.eqEmail(memberSearchDto.getSearchEmail()))
        .groupBy(member.Idx)
        .orderBy(member.Idx.desc())
        .limit(memberSearchDto.getPageSize())
        .fetch();
  }

  /**
   * 단일 회원의 주문 리스트 조회
   *
   * @param idx 회원 idx
   * @return 단일 회원 정보
   */
  public List<MemberOrder> findAllOrderByMemberId(Long idx) {
    return jpaQueryFactory
        .select(memberOrder)
        .from(member)
        .innerJoin(memberOrder)
        .on(member.Idx.eq(memberOrder.memberIdx))
        .where(member.Idx.eq(idx))
        .fetch();
  }

  /**
   * 이름 검색 조건
   *
   * @param searchName 이름
   * @return equal name 조건 표현문
   */
  private BooleanExpression eqName(String searchName) {
    return !StringUtils.hasText(searchName) ? null : member.name.like(searchName + "%");
  }

  /**
   * 이메일 검색 조건
   *
   * @param searchEmail 이메일
   * @return equal email 조건 표현문
   */
  private BooleanExpression eqEmail(String searchEmail) {
    return !StringUtils.hasText(searchEmail) ? null : member.email.like(searchEmail + "%");
  }

  /**
   * 회원 idx 검색 조건
   *
   * @param idx 회원 idx
   * @return less than id 검색 조건 표현문
   */
  private BooleanExpression ltIdx(Long idx) {
    if (idx == null) {
      return null;
    }

    return member.Idx.lt(idx);
  }
}
