-- dev_task.AUTH_TOKEN definition
-- refresh 토큰
CREATE TABLE `AUTH_TOKEN`
(
    `member_idx`    int(11)      NOT NULL COMMENT '회원 id',
    `refresh_token` varchar(255) NOT NULL COMMENT '리프레시 토큰',
    `create_at`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '토큰 생성 일자',
    PRIMARY KEY (`member_idx`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;