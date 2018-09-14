/*
Navicat MySQL Data Transfer

Source Server         : 本地localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : register

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-14 15:35:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_login_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_user`;
CREATE TABLE `sys_login_user` (
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录ID',
  `LOGIN_NAME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录名称',
  `LOGIN_MOBILE` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录手机号码',
  `LOGIN_MAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录邮箱',
  `PASSWORD` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码（MD5）',
  `LOGIN_STATUS` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录状态',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `LAST_LOGIN_IP` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最后一次登录IP',
  `LAST_LOGIN_DEVICE` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最后一次登录设备(PC,IOS...)',
  `REGISTER_TIME` datetime DEFAULT NULL COMMENT '注册时间',
  `LOGIN_USER_TYPE` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录用户类型(1:平台操作人员,2:客户,3:经纪人,4:系统用户)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='登录用户表';
