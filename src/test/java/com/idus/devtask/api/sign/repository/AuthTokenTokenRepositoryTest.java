package com.idus.devtask.api.sign.repository;

import com.idus.devtask.api.sign.entity.AuthToken;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class AuthTokenTokenRepositoryTest {
  @Autowired private AuthTokenRepository authTokenRepository;

  @After
  public void cleanUp() {
    authTokenRepository.deleteAll();
  }

  @Test
  public void 인증_정보_저장_테스트() {

    // given
    authTokenRepository.save(
        AuthToken.builder()
            .memberIdx(1L)
            .refreshToken("test")
            .createAt(LocalDateTime.now())
            .build());

    // when
    List<AuthToken> authTokenList = authTokenRepository.findAll();

    // test
    AuthToken authToken = authTokenList.get(0);
    assertEquals(authToken.getRefreshToken(), "test");
  }
}
