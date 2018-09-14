/*
Navicat MySQL Data Transfer

Source Server         : 本地localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : register

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-14 15:35:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_login_short_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_short_message`;
CREATE TABLE `sys_login_short_message` (
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '编号',
  `LOGIN_ID` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录ID',
  `LOGIN_MOBILE` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `Verification_Code` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '验证码',
  `STATUS` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态',
  `SEND_DATE_TIME` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `END_DATE_TIME` bigint(20) DEFAULT NULL COMMENT '有效时间',
  `SHORT_MESSAGE_TYPE` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='登录短信';
