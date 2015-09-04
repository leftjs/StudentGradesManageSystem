/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : localhost
 Source Database       : sms

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : utf-8

 Date: 09/04/2015 06:23:11 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `admin`
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('1', 'admin', '123');
COMMIT;

-- ----------------------------
--  Table structure for `chooseCourse`
-- ----------------------------
DROP TABLE IF EXISTS `chooseCourse`;
CREATE TABLE `chooseCourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `chooseCourse`
-- ----------------------------
BEGIN;
INSERT INTO `chooseCourse` VALUES ('1', '1', '3'), ('2', '1', '1'), ('3', '1', '2'), ('4', '2', '2'), ('5', '2', '3'), ('6', '3', '1'), ('7', '3', '2');
COMMIT;

-- ----------------------------
--  Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(40) NOT NULL,
  `academicYear` varchar(40) NOT NULL,
  `term` varchar(40) NOT NULL,
  `teacherId` int(11) NOT NULL,
  `commitStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `course`
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES ('1', '高数', '14-15', '上', '1', '已暂存'), ('2', '线代', '14-15', '上', '1', '已提交'), ('3', 'lol', '14-15', '下', '3', null);
COMMIT;

-- ----------------------------
--  Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `score` int(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `grade`
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES ('1', '2', '1', '100'), ('2', '2', '2', '59'), ('3', '2', '3', '78');
COMMIT;

-- ----------------------------
--  Table structure for `gradeDraft`
-- ----------------------------
DROP TABLE IF EXISTS `gradeDraft`;
CREATE TABLE `gradeDraft` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `score` int(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `gradeDraft`
-- ----------------------------
BEGIN;
INSERT INTO `gradeDraft` VALUES ('1', '1', '1', '99');
COMMIT;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `studentCode` varchar(64) NOT NULL,
  `name` varchar(20) NOT NULL,
  `studentClass` int(11) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `birthday` date NOT NULL,
  `address` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `studentCode` (`studentCode`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `student`
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('1', 'student', '123', '12003225', '瑞文', '32', '22', '男', '2015-09-04', '', '', ''), ('2', 'student1', '123', '12003333', '孔明', '33', '21', '女', '2014-09-09', '', '', ''), ('3', 'student2', '123', '12003122', '王云鹏', '31', '10', '未知', '1999-11-11', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `teacher`
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES ('1', 'teacher', '123', '宋朋朋', '110', '112@qq.com'), ('2', 'teacher1', '123', '苗立志', '111', '111@qq.com'), ('3', 'teacher2', '123', '卡特琳娜', '120', '888@qq.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
