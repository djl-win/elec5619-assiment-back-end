/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : db_museum

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 13/10/2022 21:52:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员',
  `admin_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `admin_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `admin_peopleId` int(11) NULL DEFAULT NULL COMMENT '外键连接people表',
  PRIMARY KEY (`admin_id`) USING BTREE,
  INDEX `FK_admin_peopleid`(`admin_peopleId`) USING BTREE,
  CONSTRAINT `FK_admin_peopleid` FOREIGN KEY (`admin_peopleId`) REFERENCES `tb_people` (`people_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论表',
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `comment_rank` int(11) NULL DEFAULT NULL COMMENT '评分',
  `comment_peopleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `FK_comment_visitorId`(`comment_peopleId`) USING BTREE,
  CONSTRAINT `FK_comment_peopleId` FOREIGN KEY (`comment_peopleId`) REFERENCES `tb_people` (`people_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_museumflow
-- ----------------------------
DROP TABLE IF EXISTS `tb_museumflow`;
CREATE TABLE `tb_museumflow`  (
  `museumflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `museumflow_out` int(11) NULL DEFAULT NULL,
  `museumflow_int` int(11) NULL DEFAULT NULL,
  `museum_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`museumflow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_parkinglot
-- ----------------------------
DROP TABLE IF EXISTS `tb_parkinglot`;
CREATE TABLE `tb_parkinglot`  (
  `parkinglot_id` int(11) NOT NULL AUTO_INCREMENT,
  `parkinglot_location` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parkinglot_capacity` int(11) NULL DEFAULT NULL,
  `parkinglot_currentFlow` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`parkinglot_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_parkinglot
-- ----------------------------
BEGIN;
INSERT INTO `tb_parkinglot` VALUES (1, 'Mar Street', 250, 211);
COMMIT;

-- ----------------------------
-- Table structure for tb_people
-- ----------------------------
DROP TABLE IF EXISTS `tb_people`;
CREATE TABLE `tb_people`  (
  `people_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '人的主键',
  `people_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人名',
  `people_gender` int(11) NULL DEFAULT NULL COMMENT '性别 1 为男 2 为女',
  `people_age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `people_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `people_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`people_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7931 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_venue
-- ----------------------------
DROP TABLE IF EXISTS `tb_venue`;
CREATE TABLE `tb_venue`  (
  `venue_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场馆表',
  `venue_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场馆名',
  `venue_location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场馆位置',
  `venue_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场馆描述',
  `venue_capacity` int(11) NULL DEFAULT NULL COMMENT '场馆容量',
  PRIMARY KEY (`venue_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_venue
-- ----------------------------
BEGIN;
INSERT INTO `tb_venue` (`venue_id`, `venue_name`, `venue_location`, `venue_description`, `venue_capacity`) VALUES (1, 'venue 1', 'level 1', 'First venue', 300);
INSERT INTO `tb_venue` (`venue_id`, `venue_name`, `venue_location`, `venue_description`, `venue_capacity`) VALUES (2, 'venue 2', 'level 2', 'Second venue', 300);
INSERT INTO `tb_venue` (`venue_id`, `venue_name`, `venue_location`, `venue_description`, `venue_capacity`) VALUES (3, 'venue 2', 'level 3', 'Third venue', 300);
COMMIT;

-- ----------------------------
-- Table structure for tb_visit
-- ----------------------------
DROP TABLE IF EXISTS `tb_visit`;
CREATE TABLE `tb_visit`  (
  `visit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '防止重复',
  `visit_visitorId` int(11) NOT NULL COMMENT '游客id',
  `visit_venueId` int(11) NOT NULL COMMENT '场馆id',
  `visit_date` datetime NULL DEFAULT NULL COMMENT '游客访问某个场馆的时间 年月日、时分秒',
  `visit_duration` int(11) NULL DEFAULT NULL COMMENT '游客停留在某个场馆的时间，类型用的int',
  `visit_status` int(11) NULL DEFAULT NULL COMMENT '记录游客是否离开博物馆，1为进入，0为离开',
  PRIMARY KEY (`visit_id`) USING BTREE,
  INDEX `FK2_visit_venueId`(`visit_venueId`) USING BTREE,
  INDEX `FK1`(`visit_visitorId`) USING BTREE,
  CONSTRAINT `FK1` FOREIGN KEY (`visit_visitorId`) REFERENCES `tb_visitor` (`visitor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2` FOREIGN KEY (`visit_venueId`) REFERENCES `tb_venue` (`venue_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15605 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_visitor
-- ----------------------------
DROP TABLE IF EXISTS `tb_visitor`;
CREATE TABLE `tb_visitor`  (
  `visitor_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '游客',
  `visitor_visitTimes` int(11) NULL DEFAULT NULL COMMENT '游客访问博物馆的次数，数据可通过查询visit表获取。触发器也可',
  `visitor_peopleId` int(11) NULL DEFAULT NULL COMMENT '外键连接people表',
  PRIMARY KEY (`visitor_id`) USING BTREE,
  INDEX `FK_visor_peopleid`(`visitor_peopleId`) USING BTREE,
  CONSTRAINT `FK_visitor_peopleid` FOREIGN KEY (`visitor_peopleId`) REFERENCES `tb_people` (`people_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7907 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;