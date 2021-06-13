package com.idus.devtask.member.repository;

import com.idus.devtask.api.member.entity.Member;
import com.idus.devtask.api.member.repository.MemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
  @Autowired MemberRepository memberRepository;

  @After
  public void removeAllData() {
    memberRepository.deleteAll();
  }

  @Test
  public void userTest() {
    // given
    memberRepository.save(
        Member.builder()
            .name("a")
            .nickName("b")
            .password("d")
            .email("e")
            .tel("f")
            .gender("M")
            .build());
    // when
    List<Member> memberList = memberRepository.findAll();
    // then
    Member member = memberList.get(0);
    assertThat(member.getName(), is("a"));
  }
}
