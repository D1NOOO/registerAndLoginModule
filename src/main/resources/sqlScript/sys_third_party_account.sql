/*
Navicat MySQL Data Transfer

Source Server         : 本地localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : register

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-14 15:35:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_third_party_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_party_account`;
CREATE TABLE `sys_third_party_account` (
  `LOGIN_ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录ID',
  `OPEN_ID` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户ID',
  `Third_party_account_type` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '第三方类型(1:微信，2：QQ 3：微博)',
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='登录关联表';
