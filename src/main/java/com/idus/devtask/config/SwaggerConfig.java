package com.idus.devtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 설정 파일
 *
 * @author dpwe231@gmail.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  /**
   * Swagger 상세 설정관련 빈 주입
   *
   * @return Docket
   */
  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(
            new ApiInfoBuilder()
                .title("회원, 주문 API Documentation")
                .description("클라이언트(IOS, Android, Web App)에 사용되는 API 문서입니다.")
                .build())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.idus.devtask.api"))
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/");
  }
}
