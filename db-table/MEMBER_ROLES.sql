-- dev_task.MEMBER_ROLES definition
-- 인증 권한
CREATE TABLE `MEMBER_ROLES`
(
    `member_idx` int(11)      NOT NULL COMMENT '회원 idx',
    `roles`      varchar(100) NOT NULL COMMENT '권한명'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;