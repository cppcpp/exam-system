/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : exam_system

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2017-07-03 19:02:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `backup_db`
-- ----------------------------
DROP TABLE IF EXISTS `backup_db`;
CREATE TABLE `backup_db` (
  `b_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `b_file_name` varchar(255) DEFAULT NULL,
  `b_time` datetime DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of backup_db
-- ----------------------------

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `e_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `subject_id` smallint(6) DEFAULT NULL,
  `user_id` smallint(6) DEFAULT NULL,
  `e_add_time` datetime DEFAULT NULL,
  `e_title` varchar(255) DEFAULT NULL,
  `e_status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_questions`
-- ----------------------------
DROP TABLE IF EXISTS `exam_questions`;
CREATE TABLE `exam_questions` (
  `e_q_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `exam_id` smallint(6) DEFAULT NULL,
  `question_id` smallint(6) DEFAULT NULL,
  `exqm_type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`e_q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_questions
-- ----------------------------

-- ----------------------------
-- Table structure for `exam_questiontype`
-- ----------------------------
DROP TABLE IF EXISTS `exam_questiontype`;
CREATE TABLE `exam_questiontype` (
  `e_t_id` smallint(11) NOT NULL AUTO_INCREMENT,
  `exam_id` smallint(11) DEFAULT NULL,
  `question_type_id` smallint(11) DEFAULT NULL,
  `question_num` smallint(6) DEFAULT NULL,
  `type_score` smallint(6) DEFAULT NULL,
  `type_sort` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`e_t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_questiontype
-- ----------------------------

-- ----------------------------
-- Table structure for `konwledge_points`
-- ----------------------------
DROP TABLE IF EXISTS `konwledge_points`;
CREATE TABLE `konwledge_points` (
  `k_id` int(6) NOT NULL AUTO_INCREMENT,
  `subject_id` smallint(6) DEFAULT NULL,
  `k_num` tinyint(4) DEFAULT NULL,
  `k_sub_num` tinyint(4) DEFAULT NULL,
  `k_title` varchar(255) DEFAULT NULL,
  `k_addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`k_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of konwledge_points
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `n_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `n_content` varchar(255) DEFAULT NULL,
  `n_add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `questions`
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `q_id` int(6) NOT NULL AUTO_INCREMENT,
  `question_type_id` smallint(6) DEFAULT NULL,
  `konwledge_point_id` int(6) DEFAULT NULL,
  `q_title` text,
  `q_answer` text,
  `q_add_time` datetime DEFAULT NULL,
  `q_user_id` smallint(6) DEFAULT NULL,
  `q_did_num` int(11) DEFAULT NULL,
  `q_correct_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of questions
-- ----------------------------

-- ----------------------------
-- Table structure for `question_correct_ratio`
-- ----------------------------
DROP TABLE IF EXISTS `question_correct_ratio`;
CREATE TABLE `question_correct_ratio` (
  `q_response_id` int(6) NOT NULL AUTO_INCREMENT,
  `question_id` int(6) DEFAULT NULL,
  `q_response_time` datetime DEFAULT NULL,
  `q_did_num` int(11) DEFAULT NULL,
  `q_correct_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`q_response_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_correct_ratio
-- ----------------------------

-- ----------------------------
-- Table structure for `question_type`
-- ----------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type` (
  `t_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `t_title` varchar(255) DEFAULT NULL,
  `t_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_type
-- ----------------------------

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `s_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `s_title` varchar(255) DEFAULT NULL,
  `s_desc` text,
  `s_add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_config
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `power` tinyint(4) DEFAULT NULL,
  `parent_id` smallint(6) DEFAULT NULL,
  `regist_time` datetime DEFAULT NULL,
  `recent_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_subject`
-- ----------------------------
DROP TABLE IF EXISTS `user_subject`;
CREATE TABLE `user_subject` (
  `u_s_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `subject_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`u_s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_subject
-- ----------------------------
