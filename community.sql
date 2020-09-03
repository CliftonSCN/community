/*
 Navicat Premium Data Transfer

 Source Server         : 47.102.217.51
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : 47.102.217.51:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 03/09/2020 09:19:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_start` bigint(20) NULL DEFAULT NULL,
  `gmt_end` bigint(20) NULL DEFAULT NULL,
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `pos` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ad
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commentator` int(11) NOT NULL,
  `like_count` bigint(20) NULL DEFAULT 0,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  `comment_count` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 2, 1, 'qqqq', 45646916, 0, 1580733939608, 1580733939608, 8);
INSERT INTO `comment` VALUES (2, 2, 1, 'wwww', 45646916, 0, 1580733939608, 1580733939608, 6);
INSERT INTO `comment` VALUES (3, 2, 2, 'eeeeee', 45646916, 0, 1580733555555, 1580733555555, 0);
INSERT INTO `comment` VALUES (4, 2, 1, '吾问无为谓', 45646916, 0, 1581411231588, 1581411231588, 3);
INSERT INTO `comment` VALUES (5, 2, 2, '我我我我我我', 45646916, 0, 1581411285350, 1581411285350, 0);
INSERT INTO `comment` VALUES (8, 2, 1, 'qweee', 45646916, 0, 1581680122845, 1581680122845, 1);
INSERT INTO `comment` VALUES (10, 2, 2, 'qqqqq', 45646916, 0, 1581680406574, 1581680406574, 0);
INSERT INTO `comment` VALUES (11, 4, 2, 'qqqq', 45646916, 0, 1581681388018, 1581681388018, 0);
INSERT INTO `comment` VALUES (12, 4, 2, 'wwwwww', 45646916, 0, 1581681392359, 1581681392359, 0);
INSERT INTO `comment` VALUES (13, 4, 1, 'qweqweqwe', 45646916, 0, 1581683429421, 1581683429421, 1);
INSERT INTO `comment` VALUES (14, 13, 2, 'qqqqqqqq', 45646916, 0, 1581683436798, 1581683436798, 0);
INSERT INTO `comment` VALUES (15, 4, 1, 'qqqq', 45646916, 0, 1581734378726, 1581734378726, 0);
INSERT INTO `comment` VALUES (17, 1, 2, '服务器能收到吗？', 45646916, 0, 1582187001509, 1582187001509, 0);
INSERT INTO `comment` VALUES (18, 1, 2, '服务器能收到吗？', 45646916, 0, 1582187083542, 1582187083542, 0);
INSERT INTO `comment` VALUES (19, 1, 2, '服务器能收到吗？', 45646916, 0, 1582187115273, 1582187115273, 0);
INSERT INTO `comment` VALUES (20, 1, 2, '服务器能收到吗？', 45646916, 0, 1582187325021, 1582187325021, 0);
INSERT INTO `comment` VALUES (21, 1, 2, '服务器能收到吗？', 45646916, 0, 1582189197450, 1582189197450, 0);
INSERT INTO `comment` VALUES (22, 2, 1, 'qqqqqqqqqqqqqqqqqqqqqqqqqqqqq', 45646916, 0, 1582201202674, 1582201202674, 0);
INSERT INTO `comment` VALUES (23, 2, 1, 'wwwwwwwwwwwwww', 45646916, 0, 1582202837892, 1582202837892, 0);
INSERT INTO `comment` VALUES (24, 2, 1, 'qqqqqqqqqqqqqq', 45646916, 0, 1582203030485, 1582203030485, 2);
INSERT INTO `comment` VALUES (25, 2, 1, 'eeeeeeeeeeeeeeee', 45646916, 0, 1582203228647, 1582203228647, 0);
INSERT INTO `comment` VALUES (26, 2, 1, 'eeeee', 45646916, 0, 1582203488092, 1582203488092, 0);
INSERT INTO `comment` VALUES (27, 2, 1, 'xxxxxx', 45646916, 0, 1582203571563, 1582203571563, 0);
INSERT INTO `comment` VALUES (28, 2, 1, 'rrrrrrrrrrrrrrrr', 45646916, 0, 1582203663945, 1582203663945, 0);
INSERT INTO `comment` VALUES (29, 2, 1, 'ooooooooooooooooooooooooooooooo', 45646916, 0, 1582203860173, 1582203860173, 0);
INSERT INTO `comment` VALUES (30, 2, 1, 'tttttttttttttttt', 45646916, 0, 1582204004572, 1582204004572, 0);
INSERT INTO `comment` VALUES (31, 2, 1, 'aaaaaaaaaaaaaaa', 45646916, 0, 1582204035895, 1582204035895, 0);
INSERT INTO `comment` VALUES (32, 2, 1, 'tttttttttttttt', 45646916, 0, 1582252826623, 1582252826623, 0);
INSERT INTO `comment` VALUES (33, 1, 2, '我能收到', 45646916, 0, 1582256972346, 1582256972346, 0);
INSERT INTO `comment` VALUES (34, 1, 2, 'hello', 45646916, 0, 1582257116231, 1582257116231, 0);
INSERT INTO `comment` VALUES (35, 1, 2, '这回没问题了吧', 45646916, 0, 1582258026437, 1582258026437, 0);
INSERT INTO `comment` VALUES (36, 2, 2, '这次总好了吧', 45646916, 0, 1582258085409, 1582258085409, 0);
INSERT INTO `comment` VALUES (37, 2, 1, '这样呢', 45646916, 0, 1582258242822, 1582258242822, 0);
INSERT INTO `comment` VALUES (38, 2, 2, '这样呢', 45646916, 0, 1582258365145, 1582258365145, 0);
INSERT INTO `comment` VALUES (39, 2, 2, '嗯哼', 45646916, 0, 1582258465563, 1582258465563, 0);
INSERT INTO `comment` VALUES (40, 24, 2, '吐了', 45646916, 0, 1582258535567, 1582258535567, 0);
INSERT INTO `comment` VALUES (41, 24, 2, '嗯哼', 45646916, 0, 1582258683761, 1582258683761, 0);
INSERT INTO `comment` VALUES (42, 8, 2, '哈哈哈哈哈', 45646916, 0, 1582258758641, 1582258758641, 0);
INSERT INTO `comment` VALUES (43, 4, 2, '欧侯', 45646916, 0, 1582259216008, 1582259216008, 0);
INSERT INTO `comment` VALUES (44, 2, 1, '欧侯', 45646916, 0, 1582259242392, 1582259242392, 0);

-- ----------------------------
-- Table structure for nav
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT 0,
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of nav
-- ----------------------------

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) NOT NULL COMMENT '通知的人',
  `receiver` int(11) NOT NULL COMMENT '接收的人',
  `outerid` int(11) NOT NULL COMMENT '问题或评论的id',
  `type` int(11) NOT NULL COMMENT '1：问题\n2：评论',
  `gmt_create` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `notifier_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outer_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES (5, 45646916, 45646916, 4, 1, 1581683429491, 1, 'Clifton', '3');
INSERT INTO `notification` VALUES (6, 45646916, 45646916, 13, 2, 1581683436886, 1, 'Clifton', 'qweqw...');
INSERT INTO `notification` VALUES (7, 45646916, 45646916, 4, 1, 1581734378780, 0, 'Clifton', '3');
INSERT INTO `notification` VALUES (8, 45646916, 45646916, 1, 2, 1582187001594, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (9, 45646916, 45646916, 1, 2, 1582187083617, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (10, 45646916, 45646916, 1, 2, 1582187140294, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (11, 45646916, 45646916, 1, 2, 1582187376202, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (12, 45646916, 45646916, 1, 2, 1582189197542, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (13, 45646916, 45646916, 2, 1, 1582201202728, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (14, 45646916, 45646916, 2, 1, 1582202837948, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (15, 45646916, 45646916, 2, 1, 1582203030536, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (16, 45646916, 45646916, 2, 1, 1582203228702, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (17, 45646916, 45646916, 2, 1, 1582203488147, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (18, 45646916, 45646916, 2, 1, 1582203571617, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (19, 45646916, 45646916, 2, 1, 1582203664004, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (20, 45646916, 45646916, 2, 1, 1582203860228, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (21, 45646916, 45646916, 2, 1, 1582204004628, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (22, 45646916, 45646916, 2, 1, 1582204035948, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (23, 45646916, 45646916, 2, 1, 1582252826682, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (24, 45646916, 45646916, 1, 2, 1582256972434, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (25, 45646916, 45646916, 1, 2, 1582257116317, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (26, 45646916, 45646916, 1, 2, 1582258026528, 0, 'Clifton', 'qqqq');
INSERT INTO `notification` VALUES (27, 45646916, 45646916, 2, 2, 1582258085499, 0, 'Clifton', 'wwww');
INSERT INTO `notification` VALUES (28, 45646916, 45646916, 2, 1, 1582258242873, 0, 'Clifton', '测试修改');
INSERT INTO `notification` VALUES (29, 45646916, 45646916, 2, 2, 1582258365234, 0, 'Clifton', 'wwww');
INSERT INTO `notification` VALUES (30, 45646916, 45646916, 2, 2, 1582258465645, 0, 'Clifton', 'wwww');
INSERT INTO `notification` VALUES (31, 45646916, 45646916, 24, 2, 1582258535650, 0, 'Clifton', 'qqqqq...');
INSERT INTO `notification` VALUES (32, 45646916, 45646916, 24, 2, 1582258683847, 0, 'Clifton', 'qqqqq...');
INSERT INTO `notification` VALUES (33, 45646916, 45646916, 8, 2, 1582258758726, 0, 'Clifton', 'qweee');
INSERT INTO `notification` VALUES (34, 45646916, 45646916, 4, 2, 1582259216092, 0, 'Clifton', '吾问无为谓');
INSERT INTO `notification` VALUES (35, 45646916, 45646916, 2, 1, 1582259242442, 0, 'Clifton', '测试修改');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `tag` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` int(11) NULL DEFAULT NULL,
  `like_count` int(11) NULL DEFAULT 0,
  `comment_count` int(11) NULL DEFAULT 0,
  `view_count` int(11) NULL DEFAULT 0,
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modify` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (2, '测试修改', 'asdasdasd', 'qwewqe,java', 45646916, 0, 37, 157, 1580733939608, 1581071628780);
INSERT INTO `question` VALUES (3, '2', 'ewqweqwe', 'qwewqe,java', 45646916, 0, 0, 12, 1580886455000, 1580886455000);
INSERT INTO `question` VALUES (4, '3', 'ewqweqwe', 'qwewqe,java', 45646916, 0, 3, 12, 1580886455000, 1580886455000);
INSERT INTO `question` VALUES (5, '4', 'ewqweqwe', 'qwewqe,java', 45646916, 0, 0, 0, 1580886455000, 1580886455000);
INSERT INTO `question` VALUES (6, '5', 'ewqweqwe', 'qwewqe', 45646916, 0, 0, 1, 1580886455000, 1580886455000);
INSERT INTO `question` VALUES (7, '6', 'ewqweqwe', 'qwewqe', 45646916, 0, 0, 1, 1580886455000, 1580886455000);
INSERT INTO `question` VALUES (8, '7', 'ewqweqwe', 'qwewqe', 45646916, 0, 0, 1, 1580886455000, 1580886455000);
INSERT INTO `question` VALUES (9, 'CliftonS的博客', 'qweqwe', 'weqwe', 45646916, 0, 0, 0, 1581058592526, 1581058592526);
INSERT INTO `question` VALUES (10, 'ceshi', 'qweqweqwewqeq', 'test', 45646916, 0, 0, 0, 1581071648559, 1581071648559);
INSERT INTO `question` VALUES (11, 'MarkDown测试', '[TOC]\r\n\r\n#### Disabled options\r\n\r\n- TeX (Based on KaTeX);\r\n- Emoji;\r\n- Task lists;\r\n- HTML tags decode;\r\n- Flowchart and Sequence Diagram;\r\n\r\n#### Editor.md directory\r\n\r\n    editor.md/\r\n            lib/\r\n            css/\r\n            scss/\r\n            tests/\r\n            fonts/\r\n            images/\r\n            plugins/\r\n            examples/\r\n            languages/     \r\n            editormd.js\r\n            ...\r\n\r\n```html\r\n<!-- English -->\r\n<script src=\"../dist/js/languages/en.js\"></script>\r\n\r\n<!-- 繁體中文 -->\r\n<script src=\"../dist/js/languages/zh-tw.js\"></script>\r\n```', 'markDown', 45646916, 0, 0, 2, 1581736038801, 1581736038801);
INSERT INTO `question` VALUES (12, 'java', 'jvav', 'java', 45646916, 0, 0, 0, 1581753639922, 1581753639922);
INSERT INTO `question` VALUES (13, 'java springboot', 'java', 'javascript', 45646916, 0, 0, 3, 1581753656614, 1581753656614);
INSERT INTO `question` VALUES (14, 'testtag', 'qweqwe', 'javascript,test,', 45646916, 0, 0, 3, 1581753815328, 1581753815328);
INSERT INTO `question` VALUES (15, 'java qwe', 'qweqwe', 'weqwe', 45646916, 0, 0, 0, 1581754833316, 1581754833316);
INSERT INTO `question` VALUES (16, 'qwe java', 'qweqwe', 'test', 45646916, 0, 0, 0, 1581754863545, 1581754863545);
INSERT INTO `question` VALUES (17, 'javascript,php,css', 'wqeqweqwe', 'javascript,php,css', 45646916, 0, 0, 2, 1581754938348, 1581754938348);
INSERT INTO `question` VALUES (18, 'adasdad', 'qweqwewqe', 'javascript,php', 45646916, 0, 0, 2, 1581754949679, 1581754949679);
INSERT INTO `question` VALUES (19, 'php,javascript', 'qweqweqew', 'php,javascript', 45646916, 0, 0, 5, 1581754960173, 1581754960173);
INSERT INTO `question` VALUES (20, 'php', 'qweqwe', 'php', 45646916, 0, 0, 3, 1581754971907, 1581754971907);

-- ----------------------------
-- Table structure for test1
-- ----------------------------
DROP TABLE IF EXISTS `test1`;
CREATE TABLE `test1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test1
-- ----------------------------
INSERT INTO `test1` VALUES (1, 'qweq');
INSERT INTO `test1` VALUES (2, 'dsdfs');
INSERT INTO `test1` VALUES (4, '宋金程');
INSERT INTO `test1` VALUES (6, '宋金程');
INSERT INTO `test1` VALUES (7, '宋金程');
INSERT INTO `test1` VALUES (8, '宋金程');
INSERT INTO `test1` VALUES (9, '宋金程');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modify` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (11, 45646916, 'Clifton', 'a59ddc26-9058-41ca-8f6f-72a93f3a05ad', 'https://avatars3.githubusercontent.com/u/45646916?v=4', 1580803662282, 1580803662282);
INSERT INTO `user` VALUES (12, 123, '123', '123', '123', 123, 123);
INSERT INTO `user` VALUES (13, 222, '222', '22', '22', 22, 22);

SET FOREIGN_KEY_CHECKS = 1;
