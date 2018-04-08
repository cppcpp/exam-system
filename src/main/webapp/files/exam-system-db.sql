/*
 Navicat Premium Data Transfer

 Source Server         : cpp
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : exam-system-db

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 08/04/2018 18:46:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for active_rule_parameters
-- ----------------------------
DROP TABLE IF EXISTS `active_rule_parameters`;
CREATE TABLE `active_rule_parameters`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active_rule_id` int(11) NOT NULL,
  `rules_parameter_id` int(11) NOT NULL,
  `value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `rules_parameter_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ix_arp_on_active_rule_id`(`active_rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for active_rules
-- ----------------------------
DROP TABLE IF EXISTS `active_rules`;
CREATE TABLE `active_rules`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) NOT NULL,
  `rule_id` int(11) NOT NULL,
  `failure_level` int(11) NOT NULL,
  `inheritance` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_profile_rule_ids`(`profile_id`, `rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1014 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for analysis_properties
-- ----------------------------
DROP TABLE IF EXISTS `analysis_properties`;
CREATE TABLE `analysis_properties`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `snapshot_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kee` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `text_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `clob_value` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `is_empty` tinyint(1) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `ix_snapshot_uuid`(`snapshot_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for backup_db
-- ----------------------------
DROP TABLE IF EXISTS `backup_db`;
CREATE TABLE `backup_db`  (
  `b_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `b_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `b_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`b_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for backup_db_copy1
-- ----------------------------
DROP TABLE IF EXISTS `backup_db_copy1`;
CREATE TABLE `backup_db_copy1`  (
  `b_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `b_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `b_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`b_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ce_activity
-- ----------------------------
DROP TABLE IF EXISTS `ce_activity`;
CREATE TABLE `ce_activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `task_type` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `component_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `is_last` tinyint(1) NOT NULL,
  `is_last_key` varchar(55) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `submitter_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `submitted_at` bigint(20) NOT NULL,
  `started_at` bigint(20) DEFAULT NULL,
  `executed_at` bigint(20) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `execution_time_ms` bigint(20) DEFAULT NULL,
  `analysis_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `error_message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `error_stacktrace` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `worker_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `execution_count` int(11) NOT NULL,
  `error_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ce_activity_uuid`(`uuid`) USING BTREE,
  INDEX `ce_activity_component_uuid`(`component_uuid`) USING BTREE,
  INDEX `ce_activity_islast_status`(`is_last`, `status`) USING BTREE,
  INDEX `ce_activity_islastkey`(`is_last_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ce_queue
-- ----------------------------
DROP TABLE IF EXISTS `ce_queue`;
CREATE TABLE `ce_queue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `task_type` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `component_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `submitter_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `started_at` bigint(20) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `worker_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `execution_count` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ce_queue_uuid`(`uuid`) USING BTREE,
  INDEX `ce_queue_component_uuid`(`component_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ce_scanner_context
-- ----------------------------
DROP TABLE IF EXISTS `ce_scanner_context`;
CREATE TABLE `ce_scanner_context`  (
  `task_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `context_data` longblob NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`task_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ce_task_characteristics
-- ----------------------------
DROP TABLE IF EXISTS `ce_task_characteristics`;
CREATE TABLE `ce_task_characteristics`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `task_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kee` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `text_value` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `ce_characteristics_task_uuid`(`task_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ce_task_input
-- ----------------------------
DROP TABLE IF EXISTS `ce_task_input`;
CREATE TABLE `ce_task_input`  (
  `task_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `input_data` longblob,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`task_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for default_qprofiles
-- ----------------------------
DROP TABLE IF EXISTS `default_qprofiles`;
CREATE TABLE `default_qprofiles`  (
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `qprofile_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`organization_uuid`, `language`) USING BTREE,
  UNIQUE INDEX `uniq_default_qprofiles_uuid`(`qprofile_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for duplications_index
-- ----------------------------
DROP TABLE IF EXISTS `duplications_index`;
CREATE TABLE `duplications_index`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `index_in_file` int(11) NOT NULL,
  `start_line` int(11) NOT NULL,
  `end_line` int(11) NOT NULL,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `analysis_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `duplications_index_hash`(`hash`) USING BTREE,
  INDEX `duplication_analysis_component`(`analysis_uuid`, `component_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for es_queue
-- ----------------------------
DROP TABLE IF EXISTS `es_queue`;
CREATE TABLE `es_queue`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `doc_type` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `doc_id` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `doc_id_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `doc_routing` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `es_queue_created_at`(`created_at`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for events
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `event_data` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `event_date` bigint(20) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `analysis_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `events_uuid`(`uuid`) USING BTREE,
  INDEX `events_analysis`(`analysis_uuid`) USING BTREE,
  INDEX `events_component_uuid`(`component_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `e_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(6) DEFAULT NULL COMMENT '学科id',
  `user_id` bigint(6) DEFAULT NULL COMMENT '创建用户id',
  `e_add_time` datetime(0) DEFAULT NULL COMMENT '试题添加时间',
  `e_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '试题标题',
  `e_difficulty_level` double(6, 6) DEFAULT NULL COMMENT '试卷难易程度',
  `e_address_a` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'A试卷生成word地址',
  `e_address_b` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'B试卷生成word地址',
  PRIMARY KEY (`e_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1523182651984518560 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_questions
-- ----------------------------
DROP TABLE IF EXISTS `exam_questions`;
CREATE TABLE `exam_questions`  (
  `e_q_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id\r\n',
  `exam_id` bigint(20) DEFAULT NULL COMMENT '试卷id',
  `exam_type` tinyint(255) DEFAULT 0 COMMENT '试卷状态：0:A卷、1：B卷',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  PRIMARY KEY (`e_q_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 468 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_questiontype
-- ----------------------------
DROP TABLE IF EXISTS `exam_questiontype`;
CREATE TABLE `exam_questiontype`  (
  `e_t_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `exam_id` bigint(11) DEFAULT NULL COMMENT '试卷id',
  `question_type_id` bigint(11) DEFAULT NULL COMMENT '问题类型id',
  `question_num` smallint(6) DEFAULT NULL COMMENT '试题数量',
  `type_score` smallint(6) DEFAULT NULL COMMENT '题型分数',
  `type_sort` tinyint(4) DEFAULT NULL COMMENT '题目排序',
  PRIMARY KEY (`e_t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file_sources
-- ----------------------------
DROP TABLE IF EXISTS `file_sources`;
CREATE TABLE `file_sources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `file_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `line_hashes` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `data_hash` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `src_hash` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `binary_data` longblob,
  `data_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `revision` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `file_sources_uuid_type`(`file_uuid`, `data_type`) USING BTREE,
  INDEX `file_sources_project_uuid`(`project_uuid`) USING BTREE,
  INDEX `file_sources_updated_at`(`updated_at`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 377 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_roles
-- ----------------------------
DROP TABLE IF EXISTS `group_roles`;
CREATE TABLE `group_roles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `role` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_group_roles`(`organization_uuid`, `group_id`, `resource_id`, `role`) USING BTREE,
  INDEX `group_roles_resource`(`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for groups_users
-- ----------------------------
DROP TABLE IF EXISTS `groups_users`;
CREATE TABLE `groups_users`  (
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  UNIQUE INDEX `groups_users_unique`(`group_id`, `user_id`) USING BTREE,
  INDEX `index_groups_users_on_user_id`(`user_id`) USING BTREE,
  INDEX `index_groups_users_on_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for internal_properties
-- ----------------------------
DROP TABLE IF EXISTS `internal_properties`;
CREATE TABLE `internal_properties`  (
  `kee` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `is_empty` tinyint(1) NOT NULL,
  `text_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `clob_value` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`kee`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for issue_changes
-- ----------------------------
DROP TABLE IF EXISTS `issue_changes`;
CREATE TABLE `issue_changes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `issue_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `change_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `change_data` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `issue_change_creation_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `issue_changes_issue_key`(`issue_key`) USING BTREE,
  INDEX `issue_changes_kee`(`kee`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for issues
-- ----------------------------
DROP TABLE IF EXISTS `issues`;
CREATE TABLE `issues`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rule_id` int(11) DEFAULT NULL,
  `severity` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `manual_severity` tinyint(1) NOT NULL,
  `message` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `line` int(11) DEFAULT NULL,
  `gap` decimal(30, 20) DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `resolution` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `checksum` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `reporter` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `assignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `author_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `action_plan_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `issue_attributes` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `effort` int(11) DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `issue_creation_date` bigint(20) DEFAULT NULL,
  `issue_update_date` bigint(20) DEFAULT NULL,
  `issue_close_date` bigint(20) DEFAULT NULL,
  `tags` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `locations` longblob,
  `issue_type` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `issues_kee`(`kee`) USING BTREE,
  INDEX `issues_assignee`(`assignee`) USING BTREE,
  INDEX `issues_component_uuid`(`component_uuid`) USING BTREE,
  INDEX `issues_creation_date`(`issue_creation_date`) USING BTREE,
  INDEX `issues_project_uuid`(`project_uuid`) USING BTREE,
  INDEX `issues_resolution`(`resolution`) USING BTREE,
  INDEX `issues_rule_id`(`rule_id`) USING BTREE,
  INDEX `issues_updated_at`(`updated_at`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1166 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for knowledge_points
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_points`;
CREATE TABLE `knowledge_points`  (
  `k_id` bigint(6) NOT NULL AUTO_INCREMENT COMMENT '知识点id',
  `subject_id` smallint(6) DEFAULT NULL COMMENT '科目id',
  `subject_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '科目标题',
  `k_num` tinyint(4) DEFAULT NULL COMMENT '知识点序号一级',
  `k_sub_num` tinyint(4) DEFAULT NULL COMMENT '知识点序号二级',
  `k_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '知识点标题',
  `k_addtime` datetime(0) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`k_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for live_measures
-- ----------------------------
DROP TABLE IF EXISTS `live_measures`;
CREATE TABLE `live_measures`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `metric_id` int(11) NOT NULL,
  `value` decimal(38, 20) DEFAULT NULL,
  `text_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `variation` decimal(38, 20) DEFAULT NULL,
  `measure_data` longblob,
  `update_marker` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `live_measures_component`(`component_uuid`, `metric_id`) USING BTREE,
  INDEX `live_measures_project`(`project_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manual_measures
-- ----------------------------
DROP TABLE IF EXISTS `manual_measures`;
CREATE TABLE `manual_measures`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `metric_id` int(11) NOT NULL,
  `value` decimal(38, 20) DEFAULT NULL,
  `text_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `manual_measures_component_uuid`(`component_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for metrics
-- ----------------------------
DROP TABLE IF EXISTS `metrics`;
CREATE TABLE `metrics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `direction` int(11) NOT NULL DEFAULT 0,
  `domain` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `short_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `qualitative` tinyint(1) NOT NULL DEFAULT 0,
  `val_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_managed` tinyint(1) DEFAULT 0,
  `enabled` tinyint(1) DEFAULT 1,
  `worst_value` decimal(38, 20) DEFAULT NULL,
  `best_value` decimal(38, 20) DEFAULT NULL,
  `optimized_best_value` tinyint(1) DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT NULL,
  `delete_historical_data` tinyint(1) DEFAULT NULL,
  `decimal_scale` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `metrics_unique_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 147 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `n_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `n_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_add_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`n_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` longblob,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_qprofiles
-- ----------------------------
DROP TABLE IF EXISTS `org_qprofiles`;
CREATE TABLE `org_qprofiles`  (
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rules_profile_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `parent_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `last_used` bigint(20) DEFAULT NULL,
  `user_updated_at` bigint(20) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `qprofiles_org_uuid`(`organization_uuid`) USING BTREE,
  INDEX `qprofiles_rp_uuid`(`rules_profile_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_quality_gates
-- ----------------------------
DROP TABLE IF EXISTS `org_quality_gates`;
CREATE TABLE `org_quality_gates`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `quality_gate_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `uniq_org_quality_gates`(`organization_uuid`, `quality_gate_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for organization_members
-- ----------------------------
DROP TABLE IF EXISTS `organization_members`;
CREATE TABLE `organization_members`  (
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`organization_uuid`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for organizations
-- ----------------------------
DROP TABLE IF EXISTS `organizations`;
CREATE TABLE `organizations`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kee` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `avatar_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `default_perm_template_project` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `default_perm_template_view` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `guarded` tinyint(1) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `default_group_id` int(11) DEFAULT NULL,
  `new_project_private` tinyint(1) NOT NULL,
  `default_quality_gate_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `organization_key`(`kee`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for perm_templates_groups
-- ----------------------------
DROP TABLE IF EXISTS `perm_templates_groups`;
CREATE TABLE `perm_templates_groups`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `template_id` int(11) NOT NULL,
  `permission_reference` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for perm_templates_users
-- ----------------------------
DROP TABLE IF EXISTS `perm_templates_users`;
CREATE TABLE `perm_templates_users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `template_id` int(11) NOT NULL,
  `permission_reference` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for perm_tpl_characteristics
-- ----------------------------
DROP TABLE IF EXISTS `perm_tpl_characteristics`;
CREATE TABLE `perm_tpl_characteristics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` int(11) NOT NULL,
  `permission_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `with_project_creator` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_perm_tpl_charac`(`template_id`, `permission_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission_templates
-- ----------------------------
DROP TABLE IF EXISTS `permission_templates`;
CREATE TABLE `permission_templates`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kee` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  `key_pattern` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for plugins
-- ----------------------------
DROP TABLE IF EXISTS `plugins`;
CREATE TABLE `plugins`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kee` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `base_plugin_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `file_hash` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `plugins_key`(`kee`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_branches
-- ----------------------------
DROP TABLE IF EXISTS `project_branches`;
CREATE TABLE `project_branches`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `branch_type` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `merge_branch_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `project_branches_kee`(`project_uuid`, `kee`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_links
-- ----------------------------
DROP TABLE IF EXISTS `project_links`;
CREATE TABLE `project_links`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `href` varchar(2048) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `component_uuid` varchar(2048) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_measures
-- ----------------------------
DROP TABLE IF EXISTS `project_measures`;
CREATE TABLE `project_measures`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` decimal(38, 20) DEFAULT NULL,
  `metric_id` int(11) NOT NULL,
  `text_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `alert_status` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `alert_text` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `variation_value_1` decimal(38, 20) DEFAULT NULL,
  `variation_value_2` decimal(38, 20) DEFAULT NULL,
  `variation_value_3` decimal(38, 20) DEFAULT NULL,
  `variation_value_4` decimal(38, 20) DEFAULT NULL,
  `variation_value_5` decimal(38, 20) DEFAULT NULL,
  `measure_data` longblob,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `analysis_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `measures_component_uuid`(`component_uuid`) USING BTREE,
  INDEX `measures_analysis_metric`(`analysis_uuid`, `metric_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 208 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_qprofiles
-- ----------------------------
DROP TABLE IF EXISTS `project_qprofiles`;
CREATE TABLE `project_qprofiles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `profile_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_project_qprofiles`(`project_uuid`, `profile_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  `scope` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `qualifier` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `kee` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `long_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `path` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deprecated_kee` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `module_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `module_uuid_path` varchar(1500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `authorization_updated_at` bigint(20) DEFAULT NULL,
  `root_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `copy_component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `developer_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `uuid_path` varchar(1500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `b_changed` tinyint(1) DEFAULT NULL,
  `b_copy_component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_enabled` tinyint(1) DEFAULT NULL,
  `b_language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_long_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_module_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_module_uuid_path` varchar(1500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_path` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_qualifier` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_uuid_path` varchar(1500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `tags` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `private` tinyint(1) NOT NULL,
  `main_branch_project_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `projects_kee`(`kee`(255)) USING BTREE,
  INDEX `projects_module_uuid`(`module_uuid`) USING BTREE,
  INDEX `projects_qualifier`(`qualifier`) USING BTREE,
  INDEX `projects_root_uuid`(`root_uuid`) USING BTREE,
  INDEX `projects_uuid`(`uuid`) USING BTREE,
  INDEX `projects_organization`(`organization_uuid`) USING BTREE,
  INDEX `projects_project_uuid`(`project_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 454 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prop_key` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `is_empty` tinyint(1) NOT NULL,
  `text_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `clob_value` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `properties_key`(`prop_key`(255)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qprofile_changes
-- ----------------------------
DROP TABLE IF EXISTS `qprofile_changes`;
CREATE TABLE `qprofile_changes`  (
  `kee` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rules_profile_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `change_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `change_data` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`kee`) USING BTREE,
  INDEX `qp_changes_rules_profile_uuid`(`rules_profile_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qprofile_edit_groups
-- ----------------------------
DROP TABLE IF EXISTS `qprofile_edit_groups`;
CREATE TABLE `qprofile_edit_groups`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` int(11) NOT NULL,
  `qprofile_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `qprofile_edit_groups_unique`(`group_id`, `qprofile_uuid`) USING BTREE,
  INDEX `qprofile_edit_groups_qprofile`(`qprofile_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qprofile_edit_users
-- ----------------------------
DROP TABLE IF EXISTS `qprofile_edit_users`;
CREATE TABLE `qprofile_edit_users`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  `qprofile_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  UNIQUE INDEX `qprofile_edit_users_unique`(`user_id`, `qprofile_uuid`) USING BTREE,
  INDEX `qprofile_edit_users_qprofile`(`qprofile_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for quality_gate_conditions
-- ----------------------------
DROP TABLE IF EXISTS `quality_gate_conditions`;
CREATE TABLE `quality_gate_conditions`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qgate_id` int(11) DEFAULT NULL,
  `metric_id` int(11) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `operator` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `value_error` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `value_warning` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for quality_gates
-- ----------------------------
DROP TABLE IF EXISTS `quality_gates`;
CREATE TABLE `quality_gates`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  `is_built_in` tinyint(1) NOT NULL,
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_quality_gates_uuid`(`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_correct_ratio
-- ----------------------------
DROP TABLE IF EXISTS `question_correct_ratio`;
CREATE TABLE `question_correct_ratio`  (
  `q_response_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `question_id` int(6) DEFAULT NULL,
  `q_response_time` datetime(0) DEFAULT NULL,
  `q_did_num` int(11) DEFAULT NULL,
  `q_correct_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`q_response_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_type
-- ----------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type`  (
  `t_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `t_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `t_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `q_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `question_type_id` bigint(6) DEFAULT NULL COMMENT '题型id',
  `konwledge_point_id` bigint(6) DEFAULT NULL COMMENT '知识点id',
  `q_title` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '题目',
  `q_answer` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '答案',
  `q_add_time` datetime(0) DEFAULT NULL COMMENT '试题添加时间',
  `q_user_id` bigint(6) DEFAULT NULL COMMENT '试题添加人员',
  `q_difficulty_level` double(6, 6) DEFAULT NULL COMMENT '试题的难易程度：\r\n1：很容易\r\n2：容易\r\n3：中等\r\n4：困难\r\n5：很困难',
  `s_id` bigint(20) DEFAULT NULL COMMENT '科目id',
  `q_get_times` int(11) DEFAULT 0 COMMENT '抽取次数',
  `q_did_num` int(11) DEFAULT NULL COMMENT '试题已做数量',
  `q_correct_num` int(11) DEFAULT NULL COMMENT '试题正确数量',
  PRIMARY KEY (`q_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rule_repositories
-- ----------------------------
DROP TABLE IF EXISTS `rule_repositories`;
CREATE TABLE `rule_repositories`  (
  `kee` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`kee`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `plugin_rule_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `plugin_config_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `plugin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `priority` int(11) DEFAULT NULL,
  `template_id` int(11) DEFAULT NULL,
  `status` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `def_remediation_function` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `def_remediation_gap_mult` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `def_remediation_base_effort` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gap_description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `system_tags` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `is_template` tinyint(1) NOT NULL DEFAULT 0,
  `description_format` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `rule_type` tinyint(2) DEFAULT NULL,
  `plugin_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rules_repo_key`(`plugin_rule_key`, `plugin_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1527 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rules_metadata
-- ----------------------------
DROP TABLE IF EXISTS `rules_metadata`;
CREATE TABLE `rules_metadata`  (
  `rule_id` int(11) NOT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `note_data` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `note_user_login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `note_created_at` bigint(20) DEFAULT NULL,
  `note_updated_at` bigint(20) DEFAULT NULL,
  `remediation_function` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remediation_gap_mult` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remediation_base_effort` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tags` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`rule_id`, `organization_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rules_parameters
-- ----------------------------
DROP TABLE IF EXISTS `rules_parameters`;
CREATE TABLE `rules_parameters`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_id` int(11) NOT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `param_type` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `default_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rules_parameters_rule_id`(`rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 248 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rules_profiles
-- ----------------------------
DROP TABLE IF EXISTS `rules_profiles`;
CREATE TABLE `rules_profiles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `kee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rules_updated_at` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  `is_built_in` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_qprof_key`(`kee`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for schema_migrations
-- ----------------------------
DROP TABLE IF EXISTS `schema_migrations`;
CREATE TABLE `schema_migrations`  (
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for snapshots
-- ----------------------------
DROP TABLE IF EXISTS `snapshots`;
CREATE TABLE `snapshots`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'U',
  `islast` tinyint(1) NOT NULL DEFAULT 0,
  `version` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `purge_status` int(11) DEFAULT NULL,
  `period1_mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period1_param` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period2_mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period2_param` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period3_mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period3_param` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period4_mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period4_param` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period5_mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `period5_param` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `build_date` bigint(20) DEFAULT NULL,
  `period1_date` bigint(20) DEFAULT NULL,
  `period2_date` bigint(20) DEFAULT NULL,
  `period3_date` bigint(20) DEFAULT NULL,
  `period4_date` bigint(20) DEFAULT NULL,
  `period5_date` bigint(20) DEFAULT NULL,
  `component_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `analyses_uuid`(`uuid`) USING BTREE,
  INDEX `snapshot_component`(`component_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `s_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `s_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_add_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `power` tinyint(4) DEFAULT NULL COMMENT '1：录入人员\r\n2：教师\r\n3：管理员',
  `parent_id` bigint(6) DEFAULT NULL,
  `regist_time` datetime(0) DEFAULT NULL,
  `recent_login_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1522404441160297174 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `role` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `organization_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_roles_resource`(`resource_id`) USING BTREE,
  INDEX `user_roles_user`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_subject
-- ----------------------------
DROP TABLE IF EXISTS `user_subject`;
CREATE TABLE `user_subject`  (
  `u_s_id` bigint(6) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(6) DEFAULT NULL COMMENT '用户id',
  `subject_id` bigint(6) DEFAULT NULL COMMENT '科目',
  PRIMARY KEY (`u_s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_tokens
-- ----------------------------
DROP TABLE IF EXISTS `user_tokens`;
CREATE TABLE `user_tokens`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `token_hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_tokens_login_name`(`login`, `name`) USING BTREE,
  UNIQUE INDEX `user_tokens_token_hash`(`token_hash`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `crypted_password` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `scm_accounts` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `external_identity` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `external_identity_provider` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_local` tinyint(1) DEFAULT NULL,
  `is_root` tinyint(1) NOT NULL,
  `onboarded` tinyint(1) NOT NULL,
  `homepage_type` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `homepage_parameter` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `users_login`(`login`) USING BTREE,
  INDEX `users_updated_at`(`updated_at`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for webhook_deliveries
-- ----------------------------
DROP TABLE IF EXISTS `webhook_deliveries`;
CREATE TABLE `webhook_deliveries`  (
  `uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `component_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ce_task_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `success` tinyint(1) NOT NULL,
  `http_status` int(11) DEFAULT NULL,
  `duration_ms` int(11) DEFAULT NULL,
  `payload` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `error_stacktrace` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `created_at` bigint(20) NOT NULL,
  `analysis_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `component_uuid`(`component_uuid`) USING BTREE,
  INDEX `ce_task_uuid`(`ce_task_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
