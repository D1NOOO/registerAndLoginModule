/*
Navicat MySQL Data Transfer

Source Server         : 本地localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : register

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-14 15:35:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_login_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_ref`;
CREATE TABLE `sys_login_ref` (
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '编号',
  `LOGIN_ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录ID',
  `USER_ID` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='登录关联表';
