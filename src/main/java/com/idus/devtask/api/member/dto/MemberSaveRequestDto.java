package com.idus.devtask.api.member.dto;

import com.idus.devtask.api.member.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collections;

/**
 * Member 회원가입 요청 DTO
 *
 * @author dpwe231@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "회원가입 요청 정보", description = "회원 가입 요청 모델 DTO")
public class MemberSaveRequestDto {
  /** 이름 */
  @ApiModelProperty(value = "이름", example = "홍길동")
  @NotBlank(message = "이름은 필수 값 입니다.")
  @Length(max = 20, message = "이름은 최대 20자리까지 입력해주세요.")
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]+$", message = "한글, 영문 대소문자만 허용됩니다.")
  private String name;

  /** 별명 */
  @ApiModelProperty(value = "별명", example = "태극기")
  @NotBlank(message = "별명은 필수 값 입니다.")
  @Length(max = 30, message = "별명은 최대 30자리까지 입력해주세요.")
  @Pattern(regexp = "^[a-z]+$", message = "영문 소문자만 허용됩니다.")
  private String nickName;

  /** 비밀번호 */
  @ApiModelProperty(value = "비밀번호", example = "aaaaAbbbc!")
  @NotBlank(message = "패스워드는 필수 값 입니다.")
  @Length(min = 10, message = "패스워드는 최소 10자리이상입니다.")
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W)[a-zA-Z\\d\\W]+",
      message = "영문 대문자, 소문자, 특수문자, 숫자가 최소 a1개이상 포함되야합니다.")
  private String password;

  /** 전화번호 */
  @ApiModelProperty(value = "전화번호", example = "01012345678")
  @NotBlank(message = "패스워드는 필수 값 입니다.")
  @Length(max = 20, message = "전화번호는 최대 20자리까지 입력해주세요.")
  @Pattern(regexp = "^[0-9]+$", message = "숫자만 허용됩니다.")
  private String tel;

  /** 이메일 */
  @ApiModelProperty(value = "이메일", example = "test@test.com")
  @NotBlank(message = "이메일은 필수 값 입니다.")
  @Length(max = 100, message = "이메일은 최대 100자리까지 입력해주세요.")
  @Email(message = "이메일 형식을 확인부탁드립니다.")
  private String email;

  /** 성별 */
  @ApiModelProperty(value = "성별", example = "M or W")
  private String gender;

  /**
   * Convert to Member Entity class
   *
   * @return Member Entity class
   */
  public Member dtoBuilder() {
    return Member.builder()
        .name(name)
        .nickName(nickName)
        .password(password)
        .tel(tel)
        .email(email)
        .gender(gender)
        .roles(Collections.singletonList("ROLE_USER"))
        .build();
  }
}
