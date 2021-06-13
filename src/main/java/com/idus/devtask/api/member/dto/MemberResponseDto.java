package com.idus.devtask.api.member.dto;

import com.idus.devtask.api.member.entity.Member;
import com.idus.devtask.common.util.TelUtil;
import lombok.Getter;

/**
 * Member 응답 DTO
 *
 * @author dpwe231@gmail.com
 */
@Getter
public class MemberResponseDto {
  /** idx */
  private Long idx;

  /** 이름 */
  private String name;

  /** 닉네임 */
  private String nickName;

  /** 전화번호 */
  private String tel;

  /** 이메일 */
  private String email;

  /** 성별 */
  private String gender;

  /** 주문 응답 DTO */
  private MemberOrderSelectDto lastOrder;

  /** 생성자 */
  public MemberResponseDto() {}

  /**
   * 생성자
   *
   * @param member 회원 정보
   */
  public MemberResponseDto(Member member) {
    this.idx = member.getIdx();
    this.name = member.getName();
    this.nickName = member.getNickName();
    this.tel = TelUtil.formatTel(member.getTel().length(), member.getTel());
    this.email = member.getEmail();
    this.gender = member.getGender();
  }

  /**
   * 생성자
   *
   * @param member 회원 정보
   * @param memberOrderSelectDto 주문 정보
   */
  public MemberResponseDto(Member member, MemberOrderSelectDto memberOrderSelectDto) {
    this.idx = member.getIdx();
    this.name = member.getName();
    this.nickName = member.getNickName();
    this.tel = TelUtil.formatTel(member.getTel().length(), member.getTel());
    this.email = member.getEmail();
    this.gender = member.getGender();
    this.lastOrder = memberOrderSelectDto;
  }
}
