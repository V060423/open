/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : open-log

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-06-19 14:58:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for request_data_log
-- ----------------------------
DROP TABLE IF EXISTS `request_data_log`;
CREATE TABLE `request_data_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOG_ID` char(32) DEFAULT NULL COMMENT '日志id',
  `APP_ID` char(32) DEFAULT NULL COMMENT '应用id',
  `CLASS_NAME` char(255) DEFAULT NULL COMMENT '类名',
  `IP` char(32) DEFAULT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL COMMENT '账号id',
  `LOG_NUM` char(32) DEFAULT NULL COMMENT '日志号',
  `URL` varchar(100) DEFAULT NULL COMMENT '请求地址 ',
  `REQUEST_DATA` text,
  `LOG_CATEGORY` char(32) DEFAULT NULL COMMENT '日志类别',
  `LOG_CONTENT` varchar(20000) DEFAULT NULL COMMENT '日志内容简要',
  `LOG_DETAILS` text COMMENT '日志详情',
  `CREATE_TIME` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `DEL_FLAG` char(1) DEFAULT 'N' COMMENT 'Y:已删除   N:未删除',
  `APPLICATION_NAME` varchar(255) DEFAULT NULL COMMENT 'spring.application.name的名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51878 DEFAULT CHARSET=utf8 COMMENT='请求数据';

-- ----------------------------
-- Records of request_data_log
-- ----------------------------

-- ----------------------------
-- Table structure for trace_log
-- ----------------------------
DROP TABLE IF EXISTS `trace_log`;
CREATE TABLE `trace_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APPLICATION_NAME` varchar(100) DEFAULT NULL COMMENT '应用名称',
  `METHOD_NAME` varchar(200) DEFAULT NULL COMMENT '方法名称',
  `SERVLET_PATH` varchar(200) DEFAULT NULL COMMENT '请求路径',
  `RPC_PHASE` varchar(10) DEFAULT NULL COMMENT 'rpc调用类型，\n    G1,     //网关发送请求\n\n    G2,     //接收网关请求（切controller）\n\n    P1,     //调用端发送请求（切consumer）\n\n    P2,     //被调用端接收到请求（切provider）\n\n    P3,     //被调用端发送响应成功\n\n    P4,     //调用端接收到响应成功\n\n    EP3,    //被调用端发送响应失败\n\n    EP4,    //调用端接收到响应失败\n\n    G3,     //控制器响应网关成功\n\n    G4,     //网关接收到成功请求\n\n    EG3,    //控制器接收到错误响应\n\n    EG4,    //网关接收到错误响应',
  `TRACE_ID` varchar(100) DEFAULT NULL COMMENT '唯一请求号',
  `SPAN_ID` varchar(100) DEFAULT NULL COMMENT '节点id',
  `PARENT_SPAN_ID` varchar(100) DEFAULT NULL COMMENT '节点父id',
  `CREATE_TIME` bigint(20) DEFAULT NULL COMMENT '生成时间戳',
  `CONTENT` varchar(20000) DEFAULT NULL COMMENT '日志内容',
  `IP` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  PRIMARY KEY (`ID`),
  KEY `TRACE_ID` (`TRACE_ID`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=1044120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trace_log
-- ----------------------------
