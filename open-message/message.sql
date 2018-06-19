/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : open-message

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-06-19 16:50:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `message_id` varchar(50) NOT NULL DEFAULT '' COMMENT '消息ID',
  `message_body` longtext NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(100) NOT NULL DEFAULT '' COMMENT '消费队列',
  `message_send_times` smallint(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `already_dead` char(1) NOT NULL DEFAULT '' COMMENT '是否死亡 Y：已死亡 N：未死亡 ',
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '状态  WAIT_VERIFY：待确认  SENDING：发送中',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `biz_id` bigint(20) DEFAULT NULL COMMENT '业务系统唯一id',
  `filed1` varchar(200) DEFAULT NULL COMMENT '预留字段1',
  `filed2` varchar(200) DEFAULT NULL COMMENT '预留字段2',
  `filed3` varchar(200) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `AK_Key_2` (`message_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='可靠消息表';

-- ----------------------------
-- Records of message
-- ----------------------------
