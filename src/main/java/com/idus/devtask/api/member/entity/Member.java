package com.idus.devtask.api.member.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Member Entity
 *
 * @author dpwe231@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Member implements UserDetails {
  /** idx */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Idx;

  /** 이름 */
  @Column(length = 20, nullable = false)
  private String name;

  /** 별명 */
  @Column(length = 30, nullable = false)
  private String nickName;

  /** 비밀번호 */
  @Column(nullable = false)
  private String password;

  /** 전화번호 */
  @Column(length = 20, nullable = false)
  private String tel;

  /** 이메일 */
  @Column(length = 100, nullable = false)
  private String email;

  /** 성별 */
  @Column private String gender;

  /** role */
  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles;

  /**
   * 빌더 패턴으로 생성자 호출
   *
   * @param name 이름
   * @param nickName 별명
   * @param password 비밀번호
   * @param tel 전화번호
   * @param email 이메일
   * @param gender 남/녀
   * @param roles 권한 유형
   */
  @Builder
  public Member(
      String name,
      String nickName,
      String password,
      String tel,
      String email,
      String gender,
      List<String> roles) {
    this.name = name;
    this.nickName = nickName;
    this.password = password;
    this.tel = tel;
    this.email = email;
    this.gender = gender;
    this.roles = roles;
  }

  /**
   * 권한 유형 리스트 조회
   *
   * @return 인증 유형 리스트
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  /**
   * 사용자 고유값
   *
   * @return
   */
  @Override
  public String getUsername() {
    return String.valueOf(this.getIdx());
  }

  /**
   * 계정의 만료 여부
   *
   * @return 계정의 만료 여부
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * 계정의 잠김 여부
   *
   * @return 계정의 잠김 여부
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * 비밀번호 만료 여부
   *
   * @return 계정의 잠김 여부
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * 계정의 활성화 여부
   *
   * @return 계정의 잠김 여부
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
