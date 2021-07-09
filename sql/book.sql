/*
 Navicat Premium Data Transfer

 Source Server         : whh
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 28/06/2021 21:21:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `sales` int(0) NOT NULL,
  `stock` int(0) NOT NULL,
  `img_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (2, '数据结构与算法', '严敏君', 78.50, 7, 12, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (3, '怎样拐跑别人的媳妇', '龙伍', 68.00, 99999, 52, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (4, '木虚肉盖饭', '小胖', 16.00, 1000, 50, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (5, 'C++编程思想', '刚哥', 45.50, 15, 94, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (6, '蛋炒饭', '周星星', 9.90, 13, 52, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (7, '赌神', '龙伍', 66.50, 126, 534, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (8, 'Java编程思想', '阳哥', 99.50, 47, 36, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (9, 'JavaScript从入门到精通', '婷姐', 9.90, 85, 95, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (10, 'cocos2d-x游戏编程入门', '国哥', 49.00, 55, 59, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (11, 'C语言程序设计', '谭浩强', 28.00, 52, 74, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (12, 'Lua语言程序设计', '雷丰阳', 51.50, 48, 82, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (13, '西游记', '罗贯中', 12.00, 20, 9998, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (14, '水浒传', '华仔', 33.05, 22, 88, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (15, '操作系统原理', '刘优', 133.05, 122, 188, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (16, '数据结构 java版', '封大神', 173.15, 21, 81, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (17, 'UNIX高级环境编程', '乐天', 99.15, 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (18, 'javaScript高级编程', '国哥', 69.15, 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (19, '大话设计模式', '国哥', 89.15, 20, 10, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (43, '时间简史', '八喜', 80.00, 9999, 101, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (44, '时间简史', '国哥啊', 80.00, 200, 758, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (45, '时间简史', '八喜', 80.00, 200, 758, 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('16247186734351', '2021-06-26 22:44:33', 1200.00, 1, 1);
INSERT INTO `t_order` VALUES ('16247202262141', '2021-06-26 23:10:26', 84.00, 2, 1);
INSERT INTO `t_order` VALUES ('16247203198701', '2021-06-26 23:12:00', 16.00, 1, 1);
INSERT INTO `t_order` VALUES ('16247732616032', '2021-06-27 13:54:22', 100.00, 0, 2);
INSERT INTO `t_order` VALUES ('16247771383853', '2021-06-27 14:58:58', 84.00, 0, 3);
INSERT INTO `t_order` VALUES ('16247984630722', '2021-06-27 20:54:23', 78.50, 2, 2);
INSERT INTO `t_order` VALUES ('16247990289101', '2021-06-27 21:03:49', 45.50, 1, 1);
INSERT INTO `t_order` VALUES ('16248489181311', '2021-06-28 10:55:18', 76.40, 1, 1);
INSERT INTO `t_order` VALUES ('16248492629782', '2021-06-28 11:01:03', 159.00, 2, 2);

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `count` int(0) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `total_price` decimal(11, 2) NULL DEFAULT NULL,
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES (3, '时间简史', 2, 100.00, 200.00, '16247186734351');
INSERT INTO `t_order_item` VALUES (4, '时间不简史', 1, 1000.00, 1000.00, '16247186734351');
INSERT INTO `t_order_item` VALUES (5, '怎样拐跑别人的媳妇', 1, 68.00, 68.00, '16247202262141');
INSERT INTO `t_order_item` VALUES (6, '木虚肉盖饭', 1, 16.00, 16.00, '16247202262141');
INSERT INTO `t_order_item` VALUES (7, '木虚肉盖饭', 1, 16.00, 16.00, '16247203198701');
INSERT INTO `t_order_item` VALUES (8, '木虚肉盖饭', 2, 16.00, 32.00, '16247732616032');
INSERT INTO `t_order_item` VALUES (9, '怎样拐跑别人的媳妇', 1, 68.00, 68.00, '16247732616032');
INSERT INTO `t_order_item` VALUES (10, '怎样拐跑别人的媳妇', 1, 68.00, 68.00, '16247771383853');
INSERT INTO `t_order_item` VALUES (11, '木虚肉盖饭', 1, 16.00, 16.00, '16247771383853');
INSERT INTO `t_order_item` VALUES (12, '数据结构与算法', 1, 78.50, 78.50, '16247984630722');
INSERT INTO `t_order_item` VALUES (13, 'C++编程思想', 1, 45.50, 45.50, '16247990289101');
INSERT INTO `t_order_item` VALUES (14, '赌神', 1, 66.50, 66.50, '16248489181311');
INSERT INTO `t_order_item` VALUES (15, '蛋炒饭', 1, 9.90, 9.90, '16248489181311');
INSERT INTO `t_order_item` VALUES (16, 'cocos2d-x游戏编程入门', 3, 49.00, 147.00, '16248492629782');
INSERT INTO `t_order_item` VALUES (17, '西游记', 1, 12.00, 12.00, '16248492629782');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'hah', '123456', 'shgds@163.com');
INSERT INTO `t_user` VALUES (2, 'xix', '123456789', '16356@qq.com');
INSERT INTO `t_user` VALUES (3, 'wzg168', '123456', 'wzg168@qq.com');
INSERT INTO `t_user` VALUES (4, 'wzg168123', '123456', 'wzg168@qq.com');
INSERT INTO `t_user` VALUES (5, 'wzg1688', '123456', 'sjakdhaj@qq.com');
INSERT INTO `t_user` VALUES (6, 'baxiba', '1234567', 'ahbda@qq.com');
INSERT INTO `t_user` VALUES (7, 'kklkl', '123456', '123456@163.com');
INSERT INTO `t_user` VALUES (8, 'wzg188', '13456', 'ahbda@qq.com');
INSERT INTO `t_user` VALUES (9, 'lklkl', '123456', 'hdhsak@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
