-- dev_task.`MEMBER` definition
-- 회원
CREATE TABLE `MEMBER`
(
    `idx`         int(11)      NOT NULL AUTO_INCREMENT COMMENT 'member idx',
    `name`        varchar(20)  NOT NULL COMMENT '이름',
    `nick_name`   varchar(30)  NOT NULL COMMENT '닉네임',
    `password`    varchar(255) NOT NULL COMMENT '비밀번호',
    `tel`         varchar(20)  NOT NULL COMMENT '전화번호',
    `email`       varchar(100) NOT NULL COMMENT '이메일',
    `gender`      varchar(255)          DEFAULT NULL COMMENT '성별',
    `create_date` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_dt`   datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`idx`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;