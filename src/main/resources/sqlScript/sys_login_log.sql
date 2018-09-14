/*
Navicat MySQL Data Transfer

Source Server         : 本地localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : register

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-14 15:35:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '编号',
  `LOGIN_NAME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录名称',
  `LOGIN_TYPE` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1：微信',
  `LOGIN_PLATFORM` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1：安卓手机,2:苹果手机，3：PC',
  `LOGIN_IP` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录IP',
  `LOGIN_DATE_TIME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录时间',
  `LOGIN_FLAG` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录Flag(1：成功,2:用户错误，3：密码错误，4：账号锁定)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户登录日志';
