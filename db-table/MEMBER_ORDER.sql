-- dev_task.MEMBER_ORDER definition
-- 주문
CREATE TABLE `MEMBER_ORDER`
(
    `idx`              int(11)                                 NOT NULL AUTO_INCREMENT COMMENT 'order idx',
    `order_number`     varchar(12) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '제품번호',
    `product_name`     varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '제품명',
    `payment_datetime` datetime                                NOT NULL COMMENT '결제 일시',
    `member_idx`       int(11)                                 NOT NULL COMMENT 'member idx',
    PRIMARY KEY (`idx`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;