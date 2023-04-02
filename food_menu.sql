/*
 Navicat Premium Data Transfer

 Source Server         : TDSQL-C
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : sh-cynosdbmysql-grp-pl8px3xs.sql.tencentcdb.com:23079
 Source Schema         : food_menu

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 01/04/2023 15:15:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员用户密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员用户手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员用户邮箱',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'admin', '111111', '18438057886', '1817937322@qq.com', 0, '2023-03-22 11:32:30', '2023-03-22 11:32:30');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '轮播图url',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 'https://menu-api.oss-cn-beijing.aliyuncs.com/banner/banner1.png', 0, '2023-03-11 10:41:37', '2023-03-11 10:43:05');
INSERT INTO `banner` VALUES (2, 'https://menu-api.oss-cn-beijing.aliyuncs.com/banner/banner2.png', 0, '2023-03-11 10:43:47', '2023-03-11 10:44:07');
INSERT INTO `banner` VALUES (3, 'https://menu-api.oss-cn-beijing.aliyuncs.com/banner/banner3.png', 0, '2023-03-11 10:45:21', '2023-03-11 10:49:46');
INSERT INTO `banner` VALUES (4, 'https://menu-api.oss-cn-beijing.aliyuncs.com/banner/banner4.png', 0, '2023-03-11 10:50:08', '2023-03-11 10:50:08');
INSERT INTO `banner` VALUES (5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/banner/banner5.png', 0, '2023-03-11 10:50:38', '2023-03-11 10:50:38');

-- ----------------------------
-- Table structure for click_log
-- ----------------------------
DROP TABLE IF EXISTS `click_log`;
CREATE TABLE `click_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `up_num` int(11) NULL DEFAULT NULL COMMENT '点赞总量',
  `collect_num` int(11) NULL DEFAULT NULL COMMENT '收藏总量',
  `click_num` int(11) NULL DEFAULT NULL COMMENT '点击总量',
  `dish_id` bigint(20) NULL DEFAULT NULL COMMENT '菜谱id',
  `is_deleted` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of click_log
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `dish_id` bigint(20) NOT NULL COMMENT '菜品id',
  `member_id` bigint(20) NOT NULL COMMENT '评论用户id',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `status` int(10) NULL DEFAULT NULL COMMENT '评论状态',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1641816591244996610 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '我是个评论内容', 5, 1633099796988416001, '2023-03-29 22:36:20', NULL, 0, '2023-03-30 22:36:25', '2023-03-30 22:36:25');
INSERT INTO `comment` VALUES (2, '我也是个评论内容', 5, 1633099796988416001, '2023-03-29 22:36:50', NULL, 0, '2023-03-30 22:36:47', '2023-03-30 22:36:56');
INSERT INTO `comment` VALUES (3, '这个评论真不错', 5, 1633099796988416001, '2023-03-14 22:37:22', NULL, 0, '2023-03-30 22:37:20', '2023-03-30 22:37:24');
INSERT INTO `comment` VALUES (1641813830004649986, '评论一下', 6, 1633099796988416001, '2023-03-31 14:44:55', 1, 0, '2023-03-31 22:44:55', '2023-03-31 22:44:55');
INSERT INTO `comment` VALUES (1641813862128824322, '评论一下', 6, 1633099796988416001, '2023-03-31 14:45:03', 1, 0, '2023-03-31 22:45:03', '2023-03-31 22:45:03');
INSERT INTO `comment` VALUES (1641816591244996609, '评论', 6, 1633099796988416001, '2023-03-31 14:55:53', 1, 0, '2023-03-31 22:55:53', '2023-03-31 22:55:53');

-- ----------------------------
-- Table structure for cook_technology
-- ----------------------------
DROP TABLE IF EXISTS `cook_technology`;
CREATE TABLE `cook_technology`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工艺名',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cook_technology
-- ----------------------------
INSERT INTO `cook_technology` VALUES (1, '炒', 0, '2023-03-17 11:22:59', '2023-03-17 11:22:59');
INSERT INTO `cook_technology` VALUES (2, '煮', 0, '2023-03-17 11:23:00', '2023-03-17 11:23:00');
INSERT INTO `cook_technology` VALUES (3, '蒸', 0, '2023-03-17 11:23:00', '2023-03-17 11:23:00');

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜品图片url',
  `material_id` bigint(20) NULL DEFAULT NULL COMMENT '食材id',
  `production_level_id` bigint(20) NULL DEFAULT NULL COMMENT '制作难度等级id',
  `production_time_id` bigint(20) NULL DEFAULT NULL COMMENT '制作时间id',
  `is_original` bit(1) NULL DEFAULT NULL COMMENT '是否原创',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜品描述',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '类型id',
  `cook_technology_id` bigint(20) NULL DEFAULT NULL COMMENT '工艺id',
  `flavor_id` bigint(20) NULL DEFAULT NULL COMMENT '品味id',
  `skill` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小妙招',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜品名',
  `publisher_id` bigint(20) NULL DEFAULT NULL COMMENT '发布者id',
  `is_sole` int(1) NULL DEFAULT NULL COMMENT '是否是独家发布',
  `status` int(1) NULL DEFAULT NULL COMMENT '(0-草稿 1-已发布 2-已审核)',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28f8fcc9b9ac5e43d48b8f4a04708fd2cacanvas.png', 7, 2, 2, NULL, '有幸在亲戚家吃过一回!被惊艳到了!! 今天给大家分享一道麻辣过瘾巨好吃的辣子鸡丁!! 够香够辣够入味，泰下饭啦，吃了三碗米饭 都不够，每次上桌就被秒光啦~喜欢吃辣的家人们一定要试试!! 食材:鸡胸肉、葱姜蒜、花椒、干辣椒、白芝麻 调味料:1勺料酒+1勺生抽+1勺老抽+1勺蚝油+1勺淀粉+半勺白胡椒粉+少许盐抓匀腌制20分钟 做法:如图所示~', 1042, 3, 4, '有幸在亲戚家吃过一回!被惊艳到了!! 今天给大家分享一道麻辣过瘾巨好吃的辣子鸡丁!! 够香够辣够入味，泰下饭啦，吃了三碗米饭 都不够，每次上桌就被秒光啦~喜欢吃辣的家人们一定要试试!! 食材:鸡胸肉、葱姜蒜、花椒、干辣椒、白芝麻 调味料:1勺料酒+1勺生抽+1勺老抽+1勺蚝油+1勺淀粉+半勺白胡椒粉+少许盐抓匀腌制20分钟 做法:如图所示~', '辣子鸡丁', 1633099796988416002, 0, 0, 0, '2023-03-28 17:40:44', '2023-03-30 22:47:17');
INSERT INTO `dish` VALUES (6, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28e29fb96ea2ca468e9ca14a8bde53fb29xiahua.jpg', 8, 1, 2, NULL, '巨鲜美的美贝虾滑，让你欲罢不能！！', 1042, 2, 3, '煮美贝时，只要煮开口2指左右，不要煮太久，开太大了就不好看了。\n做菜好吃都有技巧，我的每道菜都有小妙招，大家搜索“豆果”可以直接查看我的菜谱！', '巨好吃的美贝虾滑，绝对会鲜掉下', 1633099796988416001, 1, 0, 0, '2023-03-28 20:50:18', '2023-03-31 10:58:39');
INSERT INTO `dish` VALUES (7, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28c30c6ef00bae4f4ab14b45a067c8e2efyanshui.jpg', 9, 2, 2, NULL, '做法简单，好吃不腥', 1140, 3, 4, '晾凉再切片\n做菜好吃都有技巧，我的每道菜都有小妙招，大家搜索“豆果”可以直接查看我的菜谱！', '祖传的盐水猪肝', 1633099796988416001, 1, 0, 0, '2023-03-28 21:04:05', '2023-03-31 10:58:42');
INSERT INTO `dish` VALUES (8, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2843d82a6385454c9ba770871b41d8e40cqingjing.jpg', 10, 2, 4, NULL, '菠菜为藜科菠菜属植物，归肝、胃、大肠、小肠经。具有解热毒，通血脉，利肠胃之功效，常用于头痛，目眩，目赤，夜盲症，消渴，便秘，痔疮。此外，菠菜还富含类胡萝卜素、维生素C、维生素K等多种营养素。 春季干燥，容易引起不适症状，吃一些菠菜及绿色蔬菜能够补充体内所需要的营养，对身体健康有一定的好处。', 815, 2, 4, '做菜好吃都有技巧，我的每道菜都有小妙招，大家搜索“豆果”可以直接查看我的菜谱！', '青酱意面', 1633099796988416003, 0, 0, 0, '2023-03-28 21:10:35', '2023-03-31 10:58:48');
INSERT INTO `dish` VALUES (9, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2815097d7aea5e4b03b79ba08f9c543fe1dangao.jpeg', 11, 3, 3, NULL, '还没填该选项~', 970, 3, 6, '做菜好吃都有技巧，我的每道菜都有小妙招', '原味卷蛋糕', 1633099796988416001, 0, 0, 0, '2023-03-28 21:16:42', '2023-03-31 10:58:51');
INSERT INTO `dish` VALUES (10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2839cc00b2b9b04274a9e347ec2532e08bganbiandoujiao.jpeg', 12, 2, 3, NULL, '炎炎夏日 那来一盘素菜吧 好吃又下饭', 1055, 3, 6, '做菜好吃都有技巧，我的每道菜都有小妙招，大家搜索“豆果”可以直接查看我的菜谱！', '川菜 素 干煸豆角 快手家常菜', 1633099796988416001, 1, 0, 0, '2023-03-28 21:31:21', '2023-03-31 10:58:53');
INSERT INTO `dish` VALUES (11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28168573e3eeb24e0499de4881909c5d2eshuizhuroupian.jpeg', 13, 1, 4, NULL, '我之前做过水煮牛肉，跟这个方法一样，只是没拍过程图，今天全部一一奉上，喜欢川菜的可以尝试哦，哈哈', 907, 3, 1, '做菜好吃都有技巧，我的每道菜都有小妙招', '川菜之水煮肉片', 1633099796988416001, 1, 0, 0, '2023-03-28 21:42:26', '2023-03-31 10:59:03');
INSERT INTO `dish` VALUES (12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2841b2c21c203546afb50e58867fc96164lazhijiding.jpeg', 5, 2, 4, NULL, '说实话，作为一个地道的四川人，小时候从来没听说过宫爆鸡丁，倒是经常吃辣子鸡丁。来了北京才知道大家都以为宫爆鸡丁是川菜中的明星，其实我心中的川菜明星是辣子鸡丁！', 1025, 3, 2, '炸鸡丁的时候要勤翻动，防止粘锅。冰糖一定别忘了加一粒。别看辣椒多，其实真的不辣，超级香！试一试吧！', '川菜明星辣子鸡丁', 1633099796988416002, 0, 0, 0, '2023-03-28 21:48:58', '2023-03-31 10:59:18');

-- ----------------------------
-- Table structure for dish_kitchenware
-- ----------------------------
DROP TABLE IF EXISTS `dish_kitchenware`;
CREATE TABLE `dish_kitchenware`  (
  `id` bigint(20) NOT NULL,
  `dish_id` bigint(20) NOT NULL COMMENT '菜品id',
  `kitchenware_id` bigint(20) NOT NULL COMMENT '厨具id',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish_kitchenware
-- ----------------------------

-- ----------------------------
-- Table structure for flavor
-- ----------------------------
DROP TABLE IF EXISTS `flavor`;
CREATE TABLE `flavor`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '口味名',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flavor
-- ----------------------------
INSERT INTO `flavor` VALUES (1, '微辣', 0, '2023-03-17 11:16:22', '2023-03-17 11:16:22');
INSERT INTO `flavor` VALUES (2, '中辣', 0, '2023-03-17 11:16:37', '2023-03-17 11:16:37');
INSERT INTO `flavor` VALUES (3, '超辣', 0, '2023-03-17 11:20:08', '2023-03-17 11:20:08');
INSERT INTO `flavor` VALUES (4, '麻辣', 0, '2023-03-17 11:20:08', '2023-03-17 11:20:08');
INSERT INTO `flavor` VALUES (5, '酸辣', 0, '2023-03-17 11:20:08', '2023-03-17 11:20:08');
INSERT INTO `flavor` VALUES (6, '酸甜', 0, '2023-03-17 11:20:09', '2023-03-17 11:20:09');
INSERT INTO `flavor` VALUES (7, '酸咸', 0, '2023-03-17 11:20:09', '2023-03-17 11:20:09');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `id` bigint(20) NOT NULL,
  `fans_id` bigint(20) NOT NULL COMMENT '粉丝id',
  `member_id` bigint(20) NOT NULL COMMENT '被关注者用户id',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES (1641786391882924033, 1633099796988416001, 1633099796988416001, 0, '2023-03-31 20:55:53', '2023-03-31 20:55:53');

-- ----------------------------
-- Table structure for kitchenware
-- ----------------------------
DROP TABLE IF EXISTS `kitchenware`;
CREATE TABLE `kitchenware`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厨具名',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kitchenware
-- ----------------------------
INSERT INTO `kitchenware` VALUES (1, '炒锅', 0, '2023-03-17 11:21:36', '2023-03-17 11:21:36');
INSERT INTO `kitchenware` VALUES (2, '煮锅', 0, '2023-03-17 11:21:37', '2023-03-17 11:21:37');
INSERT INTO `kitchenware` VALUES (3, '平底锅', 0, '2023-03-17 11:21:37', '2023-03-17 11:21:37');
INSERT INTO `kitchenware` VALUES (4, '瓦锅', 0, '2023-03-17 11:21:38', '2023-03-17 11:21:38');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dosage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `minor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `minor_dosage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `codiment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `codiment_dosage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES (4, 'sdf', '20g', NULL, NULL, NULL, NULL, 0, '2023-03-17 20:21:31', '2023-03-17 20:21:31');
INSERT INTO `material` VALUES (5, '米,土豆', '适量,一个	', NULL, NULL, NULL, NULL, 0, '2023-03-28 17:30:37', '2023-03-28 17:30:37');
INSERT INTO `material` VALUES (6, '111', '1111', NULL, NULL, NULL, NULL, 0, '2023-03-28 17:40:44', '2023-03-28 17:40:44');
INSERT INTO `material` VALUES (7, '虾滑,蛋清,料酒,盐,淀粉,胡椒粉,葱,胡萝卜,鲜香菇,姜', '两袋,1-2个,少许,少许勺,少许,少许,适量,半根,2-5朵,3-4片', NULL, NULL, NULL, NULL, 0, '2023-03-28 20:50:17', '2023-03-28 20:50:17');
INSERT INTO `material` VALUES (8, '猪肝,盐,白酒,葱,干辣椒', '1块,适量,2勺,1个,2个', NULL, NULL, NULL, NULL, 0, '2023-03-28 21:04:04', '2023-03-28 21:04:04');
INSERT INTO `material` VALUES (9, '罗勒,菠菜,橄榄油,松子,黑胡椒碎,帕玛森奶酪,盐', '50克	,150克,2勺,30克,适量,适量	,适量', NULL, NULL, NULL, NULL, 0, '2023-03-28 21:10:35', '2023-03-28 21:10:35');
INSERT INTO `material` VALUES (10, ' 鸡蛋,纯牛奶,低筋面粉,白糖,植物油,白醋', '5个,60毫升,60克,50克,40克,几滴', NULL, NULL, NULL, NULL, 0, '2023-03-28 21:16:42', '2023-03-28 21:16:42');
INSERT INTO `material` VALUES (11, '豇豆,花椒粒,姜,干红辣椒,蒜,酱油,油,盐 鸡精', '一斤,适量,几片,几颗,两头,2勺,适量,适量', NULL, NULL, NULL, NULL, 0, '2023-03-28 21:31:21', '2023-03-28 21:31:21');
INSERT INTO `material` VALUES (12, '瘦肉,豆芽,豆皮,郫县豆瓣酱,姜,蒜,盐,蚝油,胡椒粉,淀粉,生抽,蛋清,芝麻油,香葱,食用油,干辣椒,花椒粉,辣椒粉', '100克,200克,1张,两勺,5片,适量,适量,3勺,适量,适量,适量,1个,适量,2根,适量,若干,适量,适量', NULL, NULL, NULL, NULL, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `material` VALUES (13, '大鸡腿,大葱,花生豆,干红辣椒,花椒,生抽,冰糖,姜蒜末,料酒,盐', '4个,半根,约150克,约30个,一把,2勺,3勺,适量,3勺,适量', NULL, NULL, NULL, NULL, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自我介绍',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别(0男1女)',
  `exp` int(255) NULL DEFAULT NULL COMMENT '经验',
  `vists` bigint(255) NULL DEFAULT NULL COMMENT '访问量',
  `status` int(1) NULL DEFAULT 1,
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限集合',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1633099796988416004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1633099796988416001, NULL, 'hello', '123456', '\r\nhttps://menu-api.oss-cn-beijing.aliyuncs.com/icon/logo.png', NULL, '18438057886', NULL, NULL, NULL, NULL, NULL, 1, 0, '2023-03-07 21:38:27', '2023-03-30 22:45:41', '');
INSERT INTO `member` VALUES (1633099796988416002, 'ronin', 'admin', '123456', '\r\nhttps://menu-api.oss-cn-beijing.aliyuncs.com/dish/home_cooking/white_chicken.jpg', NULL, '15520367895', NULL, NULL, NULL, NULL, NULL, 1, 0, '2023-03-30 22:43:00', '2023-03-30 22:45:37', '');
INSERT INTO `member` VALUES (1633099796988416003, NULL, 'root', '123456', 'https://menu-api.oss-cn-beijing.aliyuncs.com/dish/home_cooking/braised_chicken_potatoes.jpg', NULL, '15486887963', NULL, NULL, NULL, NULL, NULL, 1, 0, '2023-03-30 22:46:14', '2023-03-30 22:46:21', '');

-- ----------------------------
-- Table structure for production_level
-- ----------------------------
DROP TABLE IF EXISTS `production_level`;
CREATE TABLE `production_level`  (
  `id` bigint(20) NOT NULL,
  `level_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '难度名',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of production_level
-- ----------------------------
INSERT INTO `production_level` VALUES (1, '切墩（初级）', 0, '2023-03-16 18:37:30', '2023-03-16 18:37:30');
INSERT INTO `production_level` VALUES (2, '配菜（中级）', 0, '2023-03-16 18:37:49', '2023-03-16 18:38:06');
INSERT INTO `production_level` VALUES (3, '掌勺（高级）', 0, '2023-03-16 18:37:58', '2023-03-16 18:38:23');

-- ----------------------------
-- Table structure for production_time
-- ----------------------------
DROP TABLE IF EXISTS `production_time`;
CREATE TABLE `production_time`  (
  `id` bigint(20) NOT NULL,
  `time_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '制作难度等级名',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of production_time
-- ----------------------------
INSERT INTO `production_time` VALUES (1, '10分钟左右', 0, '2023-03-16 18:39:09', '2023-03-16 18:39:09');
INSERT INTO `production_time` VALUES (2, '10-30分钟', 0, '2023-03-16 18:58:22', '2023-03-16 18:59:12');
INSERT INTO `production_time` VALUES (3, '30-60分钟', 0, '2023-03-16 18:59:37', '2023-03-16 18:59:37');
INSERT INTO `production_time` VALUES (4, '1小时以上', 0, '2023-03-16 18:59:51', '2023-03-16 18:59:51');

-- ----------------------------
-- Table structure for rec_log
-- ----------------------------
DROP TABLE IF EXISTS `rec_log`;
CREATE TABLE `rec_log`  (
  `id` bigint(20) NOT NULL,
  `dish_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  `click_num` int(11) NULL DEFAULT 0,
  `is_collect` tinyint(1) NULL DEFAULT 0,
  `is_up` int(1) NULL DEFAULT 0,
  `comment_num` int(1) NULL DEFAULT 0,
  `is_deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rec_log
-- ----------------------------
INSERT INTO `rec_log` VALUES (1, 5, 1633099796988416001, 2, 0, 0, 3, 0, '2023-03-13 18:25:19', '2023-03-31 15:58:28');
INSERT INTO `rec_log` VALUES (2, 6, 1633099796988416001, 0, 1, 1, 0, 0, '2023-03-13 18:25:45', '2023-03-31 21:19:08');
INSERT INTO `rec_log` VALUES (3, 7, 1633099796988416001, 5, 1, 0, 0, 0, '2023-03-13 18:26:25', '2023-03-29 09:39:57');
INSERT INTO `rec_log` VALUES (4, 8, 1633099796988416001, 100, 0, 1, 0, 0, '2023-03-13 18:26:45', '2023-03-29 09:39:57');
INSERT INTO `rec_log` VALUES (5, 9, 1633099796988416001, 0, 0, 0, 0, 0, '2023-03-13 18:27:05', '2023-03-29 09:39:57');
INSERT INTO `rec_log` VALUES (6, 10, 1633099796988416001, 10, 1, 1, 10, 0, '2023-03-13 18:27:34', '2023-03-29 09:39:57');
INSERT INTO `rec_log` VALUES (7, 11, 1633099796988416001, 1, 1, 1, 5, 0, '2023-03-13 18:28:26', '2023-03-29 09:39:57');
INSERT INTO `rec_log` VALUES (8, 12, 1633099796988416001, 1, 1, 1, 1, 0, '2023-03-29 09:38:46', '2023-03-29 09:39:57');
INSERT INTO `rec_log` VALUES (1641726426749108226, 6, 1633099796988416001, 0, 1, 1, 0, 0, '2023-03-31 16:57:36', '2023-03-31 21:19:08');

-- ----------------------------
-- Table structure for step
-- ----------------------------
DROP TABLE IF EXISTS `step`;
CREATE TABLE `step`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dish_id` bigint(20) NOT NULL COMMENT '菜品id',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步骤图片url',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步骤内容',
  `rank` int(11) NOT NULL COMMENT '所属第几步',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of step
-- ----------------------------
INSERT INTO `step` VALUES (1, 2, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/1793018b5b4b1c4b5d9c4a38afa4d97cfda.jpg', '还不错', 1, 0, '2023-03-17 18:21:13', '2023-03-17 18:21:13');
INSERT INTO `step` VALUES (2, 2, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/17b5923794e5c948cdab4f25df7822266da.jpg', '还不错2', 2, 0, '2023-03-17 18:21:13', '2023-03-17 18:21:13');
INSERT INTO `step` VALUES (3, 3, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/174dfe485fa5984da7887cb5a9d3b762e5a.jpg', 'sd', 1, 0, '2023-03-17 20:21:31', '2023-03-17 20:21:31');
INSERT INTO `step` VALUES (4, 4, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2829695a30abb44e1ab02fd8393009256fa.jpg', '土豆切丁，胡萝卜切丁，香菇小块，玉米粒少许，香肠切片', 1, 0, '2023-03-28 17:30:38', '2023-03-28 17:30:38');
INSERT INTO `step` VALUES (5, 9, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/288bccad8a2dc04118bf0fe6fb4397ef4cb.jpg', '有幸在亲戚家吃过一回!被惊艳到了!! 今天给大家分享一道麻辣过瘾巨好吃的辣子鸡丁!! 够香够辣够入味，泰下饭啦，吃了三碗米饭 都不够，每次上桌就被秒光啦~喜欢吃辣的家人们一定要试试!! 食材:鸡胸肉、葱姜蒜、花椒、干辣椒、白芝麻 调味料:1勺料酒+1勺生抽+1勺老抽+1勺蚝油+1勺淀粉+半勺白胡椒粉+少许盐抓匀腌制20分钟 做法:如图所示~', 1, 0, '2023-03-28 17:40:44', '2023-03-30 22:52:05');
INSERT INTO `step` VALUES (6, 6, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28beba23bf68324f77a6a521a526fb0d9bxiahua_step1.jpg', '胡萝卜、鲜香菇、泡发的木耳用辅食机打碎放到虾滑里，加少许盐、胡椒粉、蛋清、少量生抽等拌匀。', 1, 0, '2023-03-28 20:50:18', '2023-03-28 20:50:18');
INSERT INTO `step` VALUES (7, 6, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/280c1bbf0ac6db4a42bfcffbbc83ae60b0xiahua_step2.jpg', '把清洗干净的美贝加上葱姜、料酒煮3分钟，至开口两指的样子关火取出。', 2, 0, '2023-03-28 20:50:18', '2023-03-28 20:50:18');
INSERT INTO `step` VALUES (8, 6, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28c36722f1cc8c444599e3a9de318312b7xiahua_step3.jpg', '美贝肉不用取出，直接把虾滑塞进美贝中摆盘。', 3, 0, '2023-03-28 20:50:18', '2023-03-28 20:50:18');
INSERT INTO `step` VALUES (9, 6, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2896fb479b806e4575aeebc996e3cbc186xiahua_step4.jpg', '蒸10分钟出锅。', 4, 0, '2023-03-28 20:50:18', '2023-03-28 20:50:18');
INSERT INTO `step` VALUES (10, 6, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2829ec4753e7594611aaf4c57168c7bb71xiahua_step5.jpg', '两勺生抽、一勺蚝油、一勺淀粉、少许糖，小半碗水用小火加热搅拌至透明黏稠状后淋入蒸好的美贝上，撒上葱花即可。', 5, 0, '2023-03-28 20:50:18', '2023-03-28 20:50:18');
INSERT INTO `step` VALUES (11, 7, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28736a48642b294d1aac64e32ac1f918b9yanshui_step1.jpg', '猪肝洗净剔除筋膜，两面撒盐腌制一晚。', 1, 0, '2023-03-28 21:04:05', '2023-03-28 21:04:05');
INSERT INTO `step` VALUES (12, 7, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28b6ce7d35dee14e15abad808ccfd65662yanshui_step2.jpg', '洗去盐分加入清水，大料，姜，葱，干辣椒，白酒，煮开撇去浮沫，转小火煮20分钟捞出，晾凉切片。', 2, 0, '2023-03-28 21:04:05', '2023-03-28 21:04:05');
INSERT INTO `step` VALUES (13, 7, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28097b1b620137468a9c757bd1147011dbyanshui_step3.jpg', '直接吃或者粘料汁吃都很不错。', 3, 0, '2023-03-28 21:04:05', '2023-03-28 21:04:05');
INSERT INTO `step` VALUES (14, 7, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/280850f2f0eaa44bd88db14afd3773b474yanshui_step4.jpg', '好吃不腥，不柴不老', 4, 0, '2023-03-28 21:04:05', '2023-03-28 21:04:05');
INSERT INTO `step` VALUES (15, 8, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28191ffab01239484a9e2ee1f5a868ed27qingjing_step1.jpg', '菠菜、罗勒洗净，去杆留叶。', 1, 0, '2023-03-28 21:10:35', '2023-03-28 21:10:35');
INSERT INTO `step` VALUES (16, 8, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/286c1d107329f84a07826c1a9e6953d786qingjing_step2.jpg', '水开后，放入，再次冒泡，捞出沥下水份。', 2, 0, '2023-03-28 21:10:35', '2023-03-28 21:10:35');
INSERT INTO `step` VALUES (17, 8, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28b75b920df3e3475ebed0f0849f25b226qingjing_step3.jpg', '菠菜、罗勒、松子、黑胡椒碎、盐、橄榄油，放入料理机，打成糊。', 3, 0, '2023-03-28 21:10:35', '2023-03-28 21:10:35');
INSERT INTO `step` VALUES (18, 8, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/283a341e3ab2884f60af8f38e60fd9d089qingjing_step4.jpg', '煮好的意大利面，放入打好的青酱，搅拌均匀。', 4, 0, '2023-03-28 21:10:35', '2023-03-28 21:10:35');
INSERT INTO `step` VALUES (19, 8, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/280d0c8b3e97074d61829397cdb0410090qingjing_step4.jpg', '刨上帕玛森奶酪丝。', 5, 0, '2023-03-28 21:10:35', '2023-03-28 21:10:35');
INSERT INTO `step` VALUES (20, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28a82056b0c45f4b939d65d95cb3517937dangao_step1.jpeg', '先将蛋清和蛋黄分离，蛋黄备用，蛋清放冰箱冷冻。 再将60g牛奶和40g油搅拌乳化如图所示。', 1, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:01');
INSERT INTO `step` VALUES (21, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2874ae2aba4cf1426fad67a02d19af31d9dangao_step2.jpeg', '过筛60g低筋面粉划Z字拌匀', 2, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:01');
INSERT INTO `step` VALUES (22, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28cf2d277b24c642daabfe1a83b3c4d0fedangao_step3.jpeg', '加入5个蛋黄继续划Z字拌匀', 3, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:01');
INSERT INTO `step` VALUES (23, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28bb6aace357f54f5192ee0b2a911c487ddangao_step4.jpeg', '蛋清冷冻至周围起霜，加几滴白醋，分三次加入白糖中高速打发蛋清。打发蛋白的同时烤箱150度预热10分钟。', 4, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:02');
INSERT INTO `step` VALUES (24, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28e3c65b48f57045639b6c98b7a397b8c9dangao_step5.jpeg', '蛋白打发至大弯钩即可，不要小弯钩，容易后期蛋糕开裂。', 5, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:02');
INSERT INTO `step` VALUES (25, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28ed6ff74cb3564251a273cc9350d0f088dangao_step6.jpeg', '取三分之一的蛋白霜加入蛋黄糊中翻拌均匀。', 6, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:02');
INSERT INTO `step` VALUES (26, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/282fd283ccd677465499e14298245bb605dangao_step7.jpeg', '将翻拌好的蛋黄糊倒入剩余的蛋白霜中翻拌均匀。', 7, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:02');
INSERT INTO `step` VALUES (27, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/286b003e9c2b4b4d03be0fb27b74e3ca47dangao_step8.jpeg', '烤盘28*28尺寸，铺上油布，距离烤盘30公分高出缓慢倒入面糊，轻振排除气泡。', 8, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:02');
INSERT INTO `step` VALUES (28, 5, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28a9dcf9e4ae214dce95f5615354214c35dangao_step9.jpeg', '150度中层上下火烤30分钟，取出晾凉。', 9, 0, '2023-03-28 21:16:42', '2023-03-30 22:52:02');
INSERT INTO `step` VALUES (29, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28042ecbd1239745b381fe6db7c4ae1e70ganbiandoujiao_step1.jpeg', '蒜要多多的 我用了两头 用刀把蒜按一下或者拍一下 这样好去皮 底端去蒂', 1, 0, '2023-03-28 21:31:21', '2023-03-28 21:31:21');
INSERT INTO `step` VALUES (30, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28a25efb8dc28c494993b4bbb0df16e3a8ganbiandoujiao_step2.jpeg', '切成蒜末', 2, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (31, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28a78e7a62a6b441be83bdf1bc8d36b1e7ganbiandoujiao_step3.jpeg', '豆角切长断', 3, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (32, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28351c048c40c546df9d30b0da44dad288ganbiandoujiao_step4.jpeg', '把豆角用油小火煸到起皱褶 这个过程需要耐心一些 煸好后 盛出备用', 4, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (33, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28c6c2d4de615444129377c93164d10514ganbiandoujiao_step5.jpeg', '锅中放油 放入姜片 蒜末 花椒粒 干辣椒', 5, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (34, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/287fb114de5a27431292655fe1f8b4f985ganbiandoujiao_step6.jpeg', '别炒糊了', 6, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (35, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/286bd1f657ba4d4bbea59274c65fc0fdacganbiandoujiao_step7.jpeg', '煸金黄色的时候即可', 7, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (36, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28cb1b237ebe22476d88c1c3d018e59c69ganbiandoujiao_step8.jpeg', '放入 煸好的豆角', 8, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (37, 10, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28901a1866076640379d48574dc9cf15ddganbiandoujiao_step9.jpeg', '快速翻炒 加入盐 鸡精 酱油', 9, 0, '2023-03-28 21:31:22', '2023-03-28 21:31:22');
INSERT INTO `step` VALUES (38, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/287301c776cacb45c0a63e773ffeb6e867shuizhuroupian_step1.jpeg', '豆芽热水下锅，断生，时间不宜太长，下锅30秒基本就可以，煮越久，豆芽就不脆了', 1, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `step` VALUES (39, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28faf2193b86944db4b59dff8d4b367567shuizhuroupian_step2.jpeg', '豆芽捞出，盛盘备用', 2, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `step` VALUES (40, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28bb9786ed38b9456cad1e748d7308ed81shuizhuroupian_step3.jpeg', '备点豆皮，我喜欢吃，其实也可以不放', 3, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `step` VALUES (41, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28aca041a491fe4f45a169ab1e4bc0a2b8shuizhuroupian_step4.jpeg', '瘦肉，切片，越薄越好，放入，生粉，蛋清，香油，食盐，生抽，白胡椒粉，蚝油，香油适量，拌匀，腌制', 4, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `step` VALUES (42, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28d0b3da3678d44e1d8dd6b31c6fb4518fshuizhuroupian_step5.jpeg', '腌制20分钟', 5, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `step` VALUES (43, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/288dd3b87de1864ac999d305a3b69c4a06ganbiandoujiao_step6.jpeg', '备好，姜蒜', 6, 0, '2023-03-28 21:42:26', '2023-03-28 21:42:26');
INSERT INTO `step` VALUES (44, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28a74b1c03fcd540928e51127b307bed5bshuizhuroupian_step7.jpeg', '节状干辣椒，四川青藤椒', 7, 0, '2023-03-28 21:42:27', '2023-03-28 21:42:27');
INSERT INTO `step` VALUES (45, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28d0fd5451f5a0472780345ff0536ec825shuizhuroupian_step8.jpeg', '锅里放油，油温3层热的时候放入辣椒，胡椒炒香，为啥温度不宜过高，因为过高了辣椒会糊不好看，而花椒也需要慢慢炒香，注意小火炒', 8, 0, '2023-03-28 21:42:27', '2023-03-28 21:42:27');
INSERT INTO `step` VALUES (46, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28ac65ec7fb2d949a0a7cd6dbd9223714dshuizhuroupian_step9.jpeg', '接着放姜蒜，火不要太大', 9, 0, '2023-03-28 21:42:27', '2023-03-28 21:42:27');
INSERT INTO `step` VALUES (47, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28cfb47e2bb8f4412c8ee96456ab022a55shuizhuroupian_step10.jpeg', '放入红油豆瓣，炒香', 10, 0, '2023-03-28 21:42:27', '2023-03-28 21:42:27');
INSERT INTO `step` VALUES (48, 11, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/2870af461a773941738a32ec7069db76b2shuizhuroupian_step11.jpeg', '然后锅里水，等水开了熬香，然后加入豆皮，煮一下更入味', 11, 0, '2023-03-28 21:42:27', '2023-03-28 21:42:27');
INSERT INTO `step` VALUES (49, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28873d249d0963432baaeb063eb2db4992lazhijiding_step1.jpeg', '鸡腿切丁，大概两厘米大小', 1, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');
INSERT INTO `step` VALUES (50, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28017cd62ce1d74ba2a9ae1b87f865206alazhijiding_step2.jpeg', '加入盐和料酒腌制15分钟。', 2, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');
INSERT INTO `step` VALUES (51, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/288498d926f1a24b7fa2ca4a90e569c76clazhijiding_step3.jpeg', '干辣椒剪成小段，怕辣的用二荆条辣椒，和灯笼辣椒，喜辣的用朝天椒。我用了三种辣椒可以有不同的香味。', 3, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');
INSERT INTO `step` VALUES (52, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/287d646e0db26b40379cfa56959ae63428lazhijiding_step4.jpeg', '锅里油放宽些，七成热下鸡丁。', 4, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');
INSERT INTO `step` VALUES (53, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/289fc5702bea914b59bfa181d7e91d6c8dlazhijiding_step5.jpeg', '中火炸，注意翻动防止粘锅', 5, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');
INSERT INTO `step` VALUES (54, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/288d35a9536a794d9595681ac52291c6d3lazhijiding_step6.jpeg', '另起锅，加底油烧热，下入葱姜蒜末，下干辣椒和花椒煸出香味。', 6, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');
INSERT INTO `step` VALUES (55, 12, 'https://menu-api.oss-cn-beijing.aliyuncs.com/2023/03/28db079daa69f64cf9b85442be41e05612lazhijiding_step7.jpeg', '下入鸡丁，一起煸炒，加辣椒面，少许盐和生抽一起翻炒，别忘了放一粒冰糖，这是精髓。', 7, 0, '2023-03-28 21:48:58', '2023-03-28 21:48:58');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名',
  `parent_id` bigint(20) NOT NULL COMMENT '父分类id',
  `type_level` int(1) NULL DEFAULT NULL COMMENT '分类等级',
  `is_deleted` int(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1177 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (801, '蔬菜', 0, 1, 0, '2023-03-27 13:06:45', '2023-03-27 13:06:45');
INSERT INTO `type` VALUES (802, '时令蔬菜', 801, 2, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (803, '茄子', 802, 3, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (804, '土豆', 802, 3, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (805, '菠菜', 802, 3, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (806, '韭菜', 802, 3, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (807, '西红柿', 802, 3, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (808, '油菜', 802, 3, 0, '2023-03-27 13:06:46', '2023-03-27 13:06:46');
INSERT INTO `type` VALUES (809, '紫菜', 802, 3, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (810, '春笋', 802, 3, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (811, '香椿', 802, 3, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (812, '绿叶蔬菜', 801, 2, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (813, '西兰花', 812, 3, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (814, '芦笋', 812, 3, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (815, '生菜', 812, 3, 0, '2023-03-27 13:06:47', '2023-03-27 13:06:47');
INSERT INTO `type` VALUES (816, '娃娃菜', 812, 3, 0, '2023-03-27 13:06:48', '2023-03-27 13:06:48');
INSERT INTO `type` VALUES (817, '芹菜', 812, 3, 0, '2023-03-27 13:06:48', '2023-03-27 13:06:48');
INSERT INTO `type` VALUES (818, '莴笋', 812, 3, 0, '2023-03-27 13:06:48', '2023-03-27 13:06:48');
INSERT INTO `type` VALUES (819, '油麦菜', 812, 3, 0, '2023-03-27 13:06:48', '2023-03-27 13:06:48');
INSERT INTO `type` VALUES (820, '小白菜', 812, 3, 0, '2023-03-27 13:06:48', '2023-03-27 13:06:48');
INSERT INTO `type` VALUES (821, '空心菜', 812, 3, 0, '2023-03-27 13:06:48', '2023-03-27 13:06:48');
INSERT INTO `type` VALUES (822, '菜花', 812, 3, 0, '2023-03-27 13:06:49', '2023-03-27 13:06:49');
INSERT INTO `type` VALUES (823, '卷心菜', 812, 3, 0, '2023-03-27 13:06:49', '2023-03-27 13:06:49');
INSERT INTO `type` VALUES (824, '茼蒿', 812, 3, 0, '2023-03-27 13:06:49', '2023-03-27 13:06:49');
INSERT INTO `type` VALUES (825, '苦菊', 812, 3, 0, '2023-03-27 13:06:49', '2023-03-27 13:06:49');
INSERT INTO `type` VALUES (826, '大白菜', 812, 3, 0, '2023-03-27 13:06:49', '2023-03-27 13:06:49');
INSERT INTO `type` VALUES (827, '茴香', 812, 3, 0, '2023-03-27 13:06:49', '2023-03-27 13:06:49');
INSERT INTO `type` VALUES (828, '蒜苗', 812, 3, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (829, '冰草', 812, 3, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (830, '鸡毛菜', 812, 3, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (831, '韭黄', 812, 3, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (832, '芥菜', 812, 3, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (833, '菜苔', 812, 3, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (834, '瓜类', 801, 2, 0, '2023-03-27 13:06:50', '2023-03-27 13:06:50');
INSERT INTO `type` VALUES (835, '黄瓜', 834, 3, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (836, '南瓜', 834, 3, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (837, '冬瓜', 834, 3, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (838, '丝瓜', 834, 3, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (839, '西葫芦', 834, 3, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (840, '苦瓜', 834, 3, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (841, '菌类', 801, 2, 0, '2023-03-27 13:06:51', '2023-03-27 13:06:51');
INSERT INTO `type` VALUES (842, '金针菇', 841, 3, 0, '2023-03-27 13:06:52', '2023-03-27 13:06:52');
INSERT INTO `type` VALUES (843, '杏鲍菇', 841, 3, 0, '2023-03-27 13:06:52', '2023-03-27 13:06:52');
INSERT INTO `type` VALUES (844, '香菇', 841, 3, 0, '2023-03-27 13:06:52', '2023-03-27 13:06:52');
INSERT INTO `type` VALUES (845, '木耳', 841, 3, 0, '2023-03-27 13:06:52', '2023-03-27 13:06:52');
INSERT INTO `type` VALUES (846, '白玉菇', 841, 3, 0, '2023-03-27 13:06:52', '2023-03-27 13:06:52');
INSERT INTO `type` VALUES (847, '茶树菇', 841, 3, 0, '2023-03-27 13:06:52', '2023-03-27 13:06:52');
INSERT INTO `type` VALUES (848, '冲草花', 841, 3, 0, '2023-03-27 13:06:53', '2023-03-27 13:06:53');
INSERT INTO `type` VALUES (849, '猴头菇', 841, 3, 0, '2023-03-27 13:06:53', '2023-03-27 13:06:53');
INSERT INTO `type` VALUES (850, '松珥', 841, 3, 0, '2023-03-27 13:06:53', '2023-03-27 13:06:53');
INSERT INTO `type` VALUES (851, '白口磨', 841, 3, 0, '2023-03-27 13:06:53', '2023-03-27 13:06:53');
INSERT INTO `type` VALUES (852, '牛肝菌', 841, 3, 0, '2023-03-27 13:06:53', '2023-03-27 13:06:53');
INSERT INTO `type` VALUES (853, '滑子菇', 841, 3, 0, '2023-03-27 13:06:53', '2023-03-27 13:06:53');
INSERT INTO `type` VALUES (854, '黄蘑', 841, 3, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (855, '白灵菇', 841, 3, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (856, '元蘑', 841, 3, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (857, '豆科', 801, 2, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (858, '豆角', 857, 3, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (859, '毛豆', 857, 3, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (860, '四季豆', 857, 3, 0, '2023-03-27 13:06:54', '2023-03-27 13:06:54');
INSERT INTO `type` VALUES (861, '豌豆', 857, 3, 0, '2023-03-27 13:06:55', '2023-03-27 13:06:55');
INSERT INTO `type` VALUES (862, '豆芽', 857, 3, 0, '2023-03-27 13:06:55', '2023-03-27 13:06:55');
INSERT INTO `type` VALUES (863, '蚕豆', 857, 3, 0, '2023-03-27 13:06:55', '2023-03-27 13:06:55');
INSERT INTO `type` VALUES (864, '芸豆', 857, 3, 0, '2023-03-27 13:06:55', '2023-03-27 13:06:55');
INSERT INTO `type` VALUES (865, '扁豆', 857, 3, 0, '2023-03-27 13:06:55', '2023-03-27 13:06:55');
INSERT INTO `type` VALUES (866, '黄豆芽', 857, 3, 0, '2023-03-27 13:06:55', '2023-03-27 13:06:55');
INSERT INTO `type` VALUES (867, '绿豆芽', 857, 3, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (868, '刀豆', 857, 3, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (869, '根茎蔬菜', 801, 2, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (870, '山药', 869, 3, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (871, '胡罗卜', 869, 3, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (872, '白萝卜', 869, 3, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (873, '红薯', 869, 3, 0, '2023-03-27 13:06:56', '2023-03-27 13:06:56');
INSERT INTO `type` VALUES (874, '芋头', 869, 3, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (875, '莲藕', 869, 3, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (876, '甜菜根', 869, 3, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (877, '芥菜头', 869, 3, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (878, '肉类大全', 0, 1, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (879, '猪肉', 878, 2, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (880, '排骨', 879, 3, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (881, '五花肉', 879, 3, 0, '2023-03-27 13:06:57', '2023-03-27 13:06:57');
INSERT INTO `type` VALUES (882, '廋肉', 879, 3, 0, '2023-03-27 13:06:58', '2023-03-27 13:06:58');
INSERT INTO `type` VALUES (883, '猪蹄', 879, 3, 0, '2023-03-27 13:06:58', '2023-03-27 13:06:58');
INSERT INTO `type` VALUES (884, '猪肝', 879, 3, 0, '2023-03-27 13:06:58', '2023-03-27 13:06:58');
INSERT INTO `type` VALUES (885, '腊肉', 879, 3, 0, '2023-03-27 13:06:58', '2023-03-27 13:06:58');
INSERT INTO `type` VALUES (886, '里脊', 879, 3, 0, '2023-03-27 13:06:58', '2023-03-27 13:06:58');
INSERT INTO `type` VALUES (887, '猪肚', 879, 3, 0, '2023-03-27 13:06:58', '2023-03-27 13:06:58');
INSERT INTO `type` VALUES (888, '肥肠', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (889, '午餐肉', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (890, '香肠', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (891, '猪心', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (892, '肉松', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (893, '火腿', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (894, '叉烧肉', 879, 3, 0, '2023-03-27 13:06:59', '2023-03-27 13:06:59');
INSERT INTO `type` VALUES (895, '猪皮', 879, 3, 0, '2023-03-27 13:07:00', '2023-03-27 13:07:00');
INSERT INTO `type` VALUES (896, '猪腰', 879, 3, 0, '2023-03-27 13:07:00', '2023-03-27 13:07:00');
INSERT INTO `type` VALUES (897, '猪血', 879, 3, 0, '2023-03-27 13:07:00', '2023-03-27 13:07:00');
INSERT INTO `type` VALUES (898, '猪耳朵', 879, 3, 0, '2023-03-27 13:07:00', '2023-03-27 13:07:00');
INSERT INTO `type` VALUES (899, '肉馅', 879, 3, 0, '2023-03-27 13:07:00', '2023-03-27 13:07:00');
INSERT INTO `type` VALUES (900, '猪肘', 879, 3, 0, '2023-03-27 13:07:00', '2023-03-27 13:07:00');
INSERT INTO `type` VALUES (901, '咸肉', 879, 3, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (902, '猪骨', 879, 3, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (903, '猪肺', 879, 3, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (904, '熏肉', 879, 3, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (905, '鸡肉', 878, 2, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (906, '鸡胸肉', 905, 3, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (907, '鸡腿', 905, 3, 0, '2023-03-27 13:07:01', '2023-03-27 13:07:01');
INSERT INTO `type` VALUES (908, '鸡翅', 905, 3, 0, '2023-03-27 13:07:02', '2023-03-27 13:07:02');
INSERT INTO `type` VALUES (909, '鸡爪', 905, 3, 0, '2023-03-27 13:07:02', '2023-03-27 13:07:02');
INSERT INTO `type` VALUES (910, '鸡胗', 905, 3, 0, '2023-03-27 13:07:02', '2023-03-27 13:07:02');
INSERT INTO `type` VALUES (911, '三黄鸡', 905, 3, 0, '2023-03-27 13:07:02', '2023-03-27 13:07:02');
INSERT INTO `type` VALUES (912, '土鸡', 905, 3, 0, '2023-03-27 13:07:02', '2023-03-27 13:07:02');
INSERT INTO `type` VALUES (913, '乌鸦', 905, 3, 0, '2023-03-27 13:07:02', '2023-03-27 13:07:02');
INSERT INTO `type` VALUES (914, '母鸡', 905, 3, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (915, '公鸡', 905, 3, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (916, '鸡肝', 905, 3, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (917, '柴鸡', 905, 3, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (918, '牛肉', 878, 2, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (919, '牛排', 918, 3, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (920, '牛腩', 918, 3, 0, '2023-03-27 13:07:03', '2023-03-27 13:07:03');
INSERT INTO `type` VALUES (921, '肥牛', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (922, '牛里脊', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (923, '牛腱', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (924, '牛肚', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (925, '牛仔骨', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (926, '牛尾', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (927, '牛蹄筋', 918, 3, 0, '2023-03-27 13:07:04', '2023-03-27 13:07:04');
INSERT INTO `type` VALUES (928, '牛肋骨', 918, 3, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (929, '百叶', 918, 3, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (930, '肋条', 918, 3, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (931, '羊肉', 878, 2, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (932, '羊皮', 931, 3, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (933, '羊排', 931, 3, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (934, '羊蝎子', 931, 3, 0, '2023-03-27 13:07:05', '2023-03-27 13:07:05');
INSERT INTO `type` VALUES (935, '羊肝', 931, 3, 0, '2023-03-27 13:07:06', '2023-03-27 13:07:06');
INSERT INTO `type` VALUES (936, '羊肚', 931, 3, 0, '2023-03-27 13:07:06', '2023-03-27 13:07:06');
INSERT INTO `type` VALUES (937, '羊骨', 931, 3, 0, '2023-03-27 13:07:06', '2023-03-27 13:07:06');
INSERT INTO `type` VALUES (938, '羊后腿', 931, 3, 0, '2023-03-27 13:07:06', '2023-03-27 13:07:06');
INSERT INTO `type` VALUES (939, '羊腰', 931, 3, 0, '2023-03-27 13:07:06', '2023-03-27 13:07:06');
INSERT INTO `type` VALUES (940, '羊里脊', 931, 3, 0, '2023-03-27 13:07:06', '2023-03-27 13:07:06');
INSERT INTO `type` VALUES (941, '其他', 878, 2, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (942, '鸭肉', 941, 3, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (943, '鸽子', 941, 3, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (944, '牛蛙', 941, 3, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (945, '兔肉', 941, 3, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (946, '鹅肉', 941, 3, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (947, '驴肉', 941, 3, 0, '2023-03-27 13:07:07', '2023-03-27 13:07:07');
INSERT INTO `type` VALUES (948, '鹌鹑', 941, 3, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (949, '田鸡', 941, 3, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (950, '马肉', 941, 3, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (951, '主食', 0, 1, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (952, '面食', 951, 2, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (953, '面条', 952, 3, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (954, '凉面', 952, 3, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (955, '包子', 952, 3, 0, '2023-03-27 13:07:08', '2023-03-27 13:07:08');
INSERT INTO `type` VALUES (956, '饺子', 952, 3, 0, '2023-03-27 13:07:09', '2023-03-27 13:07:09');
INSERT INTO `type` VALUES (957, '拌面', 952, 3, 0, '2023-03-27 13:07:09', '2023-03-27 13:07:09');
INSERT INTO `type` VALUES (958, '馒头', 952, 3, 0, '2023-03-27 13:07:09', '2023-03-27 13:07:09');
INSERT INTO `type` VALUES (959, '焖面', 952, 3, 0, '2023-03-27 13:07:09', '2023-03-27 13:07:09');
INSERT INTO `type` VALUES (960, '馄饨', 952, 3, 0, '2023-03-27 13:07:09', '2023-03-27 13:07:09');
INSERT INTO `type` VALUES (961, '花卷', 952, 3, 0, '2023-03-27 13:07:09', '2023-03-27 13:07:09');
INSERT INTO `type` VALUES (962, '煎饺', 952, 3, 0, '2023-03-27 13:07:10', '2023-03-27 13:07:10');
INSERT INTO `type` VALUES (963, '锅贴', 952, 3, 0, '2023-03-27 13:07:10', '2023-03-27 13:07:10');
INSERT INTO `type` VALUES (964, '蒸饺', 952, 3, 0, '2023-03-27 13:07:10', '2023-03-27 13:07:10');
INSERT INTO `type` VALUES (965, '虾饺', 952, 3, 0, '2023-03-27 13:07:10', '2023-03-27 13:07:10');
INSERT INTO `type` VALUES (966, '水晶胶', 952, 3, 0, '2023-03-27 13:07:10', '2023-03-27 13:07:10');
INSERT INTO `type` VALUES (967, '中华饺子全席', 952, 3, 0, '2023-03-27 13:07:10', '2023-03-27 13:07:10');
INSERT INTO `type` VALUES (968, '饼', 951, 2, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (969, '手抓饼', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (970, '煎饼', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (971, '葱油饼', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (972, '馅饼', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (973, '玉米饼', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (974, '炒饼', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (975, '肉夹馍', 968, 3, 0, '2023-03-27 13:07:11', '2023-03-27 13:07:11');
INSERT INTO `type` VALUES (976, '发面饼', 968, 3, 0, '2023-03-27 13:07:12', '2023-03-27 13:07:12');
INSERT INTO `type` VALUES (977, '春饼', 968, 3, 0, '2023-03-27 13:07:12', '2023-03-27 13:07:12');
INSERT INTO `type` VALUES (978, '春卷', 968, 3, 0, '2023-03-27 13:07:12', '2023-03-27 13:07:12');
INSERT INTO `type` VALUES (979, '锅盔', 968, 3, 0, '2023-03-27 13:07:12', '2023-03-27 13:07:12');
INSERT INTO `type` VALUES (980, '火烧', 968, 3, 0, '2023-03-27 13:07:12', '2023-03-27 13:07:12');
INSERT INTO `type` VALUES (981, '盒子', 968, 3, 0, '2023-03-27 13:07:12', '2023-03-27 13:07:12');
INSERT INTO `type` VALUES (982, '灌饼', 968, 3, 0, '2023-03-27 13:07:13', '2023-03-27 13:07:13');
INSERT INTO `type` VALUES (983, '馕', 968, 3, 0, '2023-03-27 13:07:13', '2023-03-27 13:07:13');
INSERT INTO `type` VALUES (984, '西式主食', 951, 2, 0, '2023-03-27 13:07:13', '2023-03-27 13:07:13');
INSERT INTO `type` VALUES (985, '披萨', 984, 3, 0, '2023-03-27 13:07:13', '2023-03-27 13:07:13');
INSERT INTO `type` VALUES (986, '土豆泥', 984, 3, 0, '2023-03-27 13:07:13', '2023-03-27 13:07:13');
INSERT INTO `type` VALUES (987, '三明治', 984, 3, 0, '2023-03-27 13:07:13', '2023-03-27 13:07:13');
INSERT INTO `type` VALUES (988, '意大利面', 984, 3, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (989, '汉堡', 984, 3, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (990, '粥', 951, 2, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (991, '皮蛋瘦肉粥', 990, 3, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (992, '小米粥', 990, 3, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (993, '海鲜粥', 990, 3, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (994, '南瓜粥', 990, 3, 0, '2023-03-27 13:07:14', '2023-03-27 13:07:14');
INSERT INTO `type` VALUES (995, '绿豆粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (996, '八宝粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (997, '燕麦粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (998, '山药粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (999, '红豆粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (1000, '玉米粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (1001, '青菜粥', 990, 3, 0, '2023-03-27 13:07:15', '2023-03-27 13:07:15');
INSERT INTO `type` VALUES (1002, '水果粥', 990, 3, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1003, '米饭', 951, 2, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1004, '炒饭', 1003, 3, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1005, '煲仔饭', 1003, 3, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1006, '焖饭', 1003, 3, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1007, '蛋包饭', 1003, 3, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1008, '盖浇饭', 1003, 3, 0, '2023-03-27 13:07:16', '2023-03-27 13:07:16');
INSERT INTO `type` VALUES (1009, '拌饭', 1003, 3, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1010, '抓饭', 1003, 3, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1011, '菜式菜系', 0, 1, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1012, '菜式', 1011, 2, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1013, '下饭菜', 1012, 3, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1014, '快手菜', 1012, 3, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1015, '凉菜', 1012, 3, 0, '2023-03-27 13:07:17', '2023-03-27 13:07:17');
INSERT INTO `type` VALUES (1016, '素食', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1017, '宴客菜', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1018, '私房菜', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1019, '下酒菜', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1020, '下午茶', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1021, '野炊', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1022, '小吃', 1012, 3, 0, '2023-03-27 13:07:18', '2023-03-27 13:07:18');
INSERT INTO `type` VALUES (1023, '中国菜', 1011, 2, 0, '2023-03-27 13:07:19', '2023-03-27 13:07:19');
INSERT INTO `type` VALUES (1024, '豫菜', 1023, 3, 0, '2023-03-27 13:07:19', '2023-03-27 13:07:19');
INSERT INTO `type` VALUES (1025, '川菜', 1023, 3, 0, '2023-03-27 13:07:19', '2023-03-27 13:07:19');
INSERT INTO `type` VALUES (1026, '粤菜', 1023, 3, 0, '2023-03-27 13:07:19', '2023-03-27 13:07:19');
INSERT INTO `type` VALUES (1027, '湘菜', 1023, 3, 0, '2023-03-27 13:07:19', '2023-03-27 13:07:19');
INSERT INTO `type` VALUES (1028, '东北菜', 1023, 3, 0, '2023-03-27 13:07:19', '2023-03-27 13:07:19');
INSERT INTO `type` VALUES (1029, '浙菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1030, '鲁菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1031, '淮扬菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1032, '徽菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1033, '苏菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1034, '闽菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1035, '云南菜', 1023, 3, 0, '2023-03-27 13:07:20', '2023-03-27 13:07:20');
INSERT INTO `type` VALUES (1036, '潮州菜', 1023, 3, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1037, '西北菜', 1023, 3, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1038, '新疆菜', 1023, 3, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1039, '外国菜', 1011, 2, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1040, '日本料理', 1039, 3, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1041, '韩国料理', 1039, 3, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1042, '泰国菜', 1039, 3, 0, '2023-03-27 13:07:21', '2023-03-27 13:07:21');
INSERT INTO `type` VALUES (1043, '意大利菜', 1039, 3, 0, '2023-03-27 13:07:22', '2023-03-27 13:07:22');
INSERT INTO `type` VALUES (1044, '法国料理', 1039, 3, 0, '2023-03-27 13:07:22', '2023-03-27 13:07:22');
INSERT INTO `type` VALUES (1045, '东南亚菜', 1039, 3, 0, '2023-03-27 13:07:22', '2023-03-27 13:07:22');
INSERT INTO `type` VALUES (1046, '西班牙菜', 1039, 3, 0, '2023-03-27 13:07:22', '2023-03-27 13:07:22');
INSERT INTO `type` VALUES (1047, '德国菜', 1039, 3, 0, '2023-03-27 13:07:22', '2023-03-27 13:07:22');
INSERT INTO `type` VALUES (1048, '墨西哥菜', 1039, 3, 0, '2023-03-27 13:07:22', '2023-03-27 13:07:22');
INSERT INTO `type` VALUES (1049, '印度菜', 1039, 3, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1050, '瑞士菜', 1039, 3, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1051, '各地小吃', 1011, 2, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1052, '河南小吃', 1051, 3, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1053, '北京小吃', 1051, 3, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1054, '天津小吃', 1051, 3, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1055, '河北小吃', 1051, 3, 0, '2023-03-27 13:07:23', '2023-03-27 13:07:23');
INSERT INTO `type` VALUES (1056, '蒙古小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1057, '上海小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1058, '山东小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1059, '江苏小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1060, '浙江小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1061, '安徽小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1062, '吉林小吃', 1051, 3, 0, '2023-03-27 13:07:24', '2023-03-27 13:07:24');
INSERT INTO `type` VALUES (1063, '辽宁小吃', 1051, 3, 0, '2023-03-27 13:07:25', '2023-03-27 13:07:25');
INSERT INTO `type` VALUES (1064, '陕西小吃', 1051, 3, 0, '2023-03-27 13:07:25', '2023-03-27 13:07:25');
INSERT INTO `type` VALUES (1065, '新疆小吃', 1051, 3, 0, '2023-03-27 13:07:25', '2023-03-27 13:07:25');
INSERT INTO `type` VALUES (1066, '宁夏小吃', 1051, 3, 0, '2023-03-27 13:07:25', '2023-03-27 13:07:25');
INSERT INTO `type` VALUES (1067, '甘肃小吃', 1051, 3, 0, '2023-03-27 13:07:25', '2023-03-27 13:07:25');
INSERT INTO `type` VALUES (1068, '青海小吃', 1051, 3, 0, '2023-03-27 13:07:25', '2023-03-27 13:07:25');
INSERT INTO `type` VALUES (1069, '湖北小吃', 1051, 3, 0, '2023-03-27 13:07:26', '2023-03-27 13:07:26');
INSERT INTO `type` VALUES (1070, '湖南小吃', 1051, 3, 0, '2023-03-27 13:07:26', '2023-03-27 13:07:26');
INSERT INTO `type` VALUES (1071, '江西小吃', 1051, 3, 0, '2023-03-27 13:07:26', '2023-03-27 13:07:26');
INSERT INTO `type` VALUES (1072, '重庆小吃', 1051, 3, 0, '2023-03-27 13:07:26', '2023-03-27 13:07:26');
INSERT INTO `type` VALUES (1073, '四川小吃', 1051, 3, 0, '2023-03-27 13:07:26', '2023-03-27 13:07:26');
INSERT INTO `type` VALUES (1074, '云南小吃', 1051, 3, 0, '2023-03-27 13:07:26', '2023-03-27 13:07:26');
INSERT INTO `type` VALUES (1075, '贵州小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1076, '西藏小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1077, '广东小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1078, '福建小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1079, '广西小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1080, '海南小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1081, '香港小吃', 1051, 3, 0, '2023-03-27 13:07:27', '2023-03-27 13:07:27');
INSERT INTO `type` VALUES (1082, '台湾小吃', 1051, 3, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1083, '成都小吃', 1051, 3, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1084, '黑龙江小吃', 1051, 3, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1085, '水产海鲜', 0, 1, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1086, '虾', 1085, 2, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1087, '虾仁', 1086, 3, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1088, '基围虾', 1086, 3, 0, '2023-03-27 13:07:28', '2023-03-27 13:07:28');
INSERT INTO `type` VALUES (1089, '河虾', 1086, 3, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1090, '皮皮虾', 1086, 3, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1091, '海虾', 1086, 3, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1092, '北极虾', 1086, 3, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1093, '草虾', 1086, 3, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1094, '淡水鱼', 1085, 2, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1095, '鲈鱼', 1094, 3, 0, '2023-03-27 13:07:29', '2023-03-27 13:07:29');
INSERT INTO `type` VALUES (1096, '草鱼', 1094, 3, 0, '2023-03-27 13:07:30', '2023-03-27 13:07:30');
INSERT INTO `type` VALUES (1097, '鲫鱼', 1094, 3, 0, '2023-03-27 13:07:30', '2023-03-27 13:07:30');
INSERT INTO `type` VALUES (1098, '黑鱼', 1094, 3, 0, '2023-03-27 13:07:30', '2023-03-27 13:07:30');
INSERT INTO `type` VALUES (1099, '罗非鱼', 1094, 3, 0, '2023-03-27 13:07:30', '2023-03-27 13:07:30');
INSERT INTO `type` VALUES (1100, '黄鳝', 1094, 3, 0, '2023-03-27 13:07:30', '2023-03-27 13:07:30');
INSERT INTO `type` VALUES (1101, '泥鳅', 1094, 3, 0, '2023-03-27 13:07:30', '2023-03-27 13:07:30');
INSERT INTO `type` VALUES (1102, '清江鱼', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1103, '鲶鱼', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1104, '银鱼', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1105, '黄腊丁', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1106, '鳊鱼', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1107, '鲢鱼', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1108, '胖头鱼', 1094, 3, 0, '2023-03-27 13:07:31', '2023-03-27 13:07:31');
INSERT INTO `type` VALUES (1109, '青鱼', 1094, 3, 0, '2023-03-27 13:07:32', '2023-03-27 13:07:32');
INSERT INTO `type` VALUES (1110, '棱边鱼', 1094, 3, 0, '2023-03-27 13:07:32', '2023-03-27 13:07:32');
INSERT INTO `type` VALUES (1111, '白鱼', 1094, 3, 0, '2023-03-27 13:07:32', '2023-03-27 13:07:32');
INSERT INTO `type` VALUES (1112, '鲮鱼', 1094, 3, 0, '2023-03-27 13:07:32', '2023-03-27 13:07:32');
INSERT INTO `type` VALUES (1115, '海水鱼', 1085, 2, 0, '2023-03-27 13:11:38', '2023-03-27 13:11:38');
INSERT INTO `type` VALUES (1116, '带鱼', 1115, 3, 0, '2023-03-27 13:11:38', '2023-03-27 13:11:38');
INSERT INTO `type` VALUES (1117, '龙利鱼', 1115, 3, 0, '2023-03-27 13:11:38', '2023-03-27 13:11:38');
INSERT INTO `type` VALUES (1118, '鳕鱼', 1115, 3, 0, '2023-03-27 13:11:38', '2023-03-27 13:11:38');
INSERT INTO `type` VALUES (1119, '黄花鱼', 1115, 3, 0, '2023-03-27 13:11:38', '2023-03-27 13:11:38');
INSERT INTO `type` VALUES (1120, '三文鱼', 1115, 3, 0, '2023-03-27 13:11:38', '2023-03-27 13:11:38');
INSERT INTO `type` VALUES (1121, '金枪鱼', 1115, 3, 0, '2023-03-27 13:11:39', '2023-03-27 13:11:39');
INSERT INTO `type` VALUES (1122, '秋刀鱼', 1115, 3, 0, '2023-03-27 13:11:39', '2023-03-27 13:11:39');
INSERT INTO `type` VALUES (1123, '海鲈鱼', 1115, 3, 0, '2023-03-27 13:11:39', '2023-03-27 13:11:39');
INSERT INTO `type` VALUES (1124, '比目鱼', 1115, 3, 0, '2023-03-27 13:11:39', '2023-03-27 13:11:39');
INSERT INTO `type` VALUES (1125, '沙丁鱼', 1115, 3, 0, '2023-03-27 13:11:39', '2023-03-27 13:11:39');
INSERT INTO `type` VALUES (1126, '海鳗', 1115, 3, 0, '2023-03-27 13:11:39', '2023-03-27 13:11:39');
INSERT INTO `type` VALUES (1127, '金钱鱼', 1115, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1128, '梭鱼', 1115, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1129, '贝类', 1085, 2, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1130, '生蚝', 1129, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1131, '花蛤', 1129, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1132, '扇贝', 1129, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1133, '哈利', 1129, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1134, '海瓜子', 1129, 3, 0, '2023-03-27 13:11:40', '2023-03-27 13:11:40');
INSERT INTO `type` VALUES (1135, '青口贝', 1129, 3, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1136, '螃蟹', 1085, 2, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1137, '梭子蟹', 1136, 3, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1138, '青蟹', 1136, 3, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1139, '帝王蟹', 1136, 3, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1140, '花蟹', 1136, 3, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1141, '面包蟹', 1136, 3, 0, '2023-03-27 13:11:41', '2023-03-27 13:11:41');
INSERT INTO `type` VALUES (1142, '大闸蟹', 1136, 3, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1143, '蟹肉', 1136, 3, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1144, '蟹黄', 1136, 3, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1145, '烘培', 0, 1, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1146, '蛋糕', 1145, 2, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1147, '纸杯蛋糕', 1146, 3, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1148, '戚风蛋糕', 1146, 3, 0, '2023-03-27 13:11:42', '2023-03-27 13:11:42');
INSERT INTO `type` VALUES (1149, '蛋糕卷', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1150, '慕斯', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1151, '芝士蛋糕', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1152, '奶油蛋糕', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1153, '千层蛋糕', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1154, '舒芙蕾', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1155, '6寸蛋糕', 1146, 3, 0, '2023-03-27 13:11:43', '2023-03-27 13:11:43');
INSERT INTO `type` VALUES (1156, '乳酪蛋糕', 1146, 3, 0, '2023-03-27 13:11:44', '2023-03-27 13:11:44');
INSERT INTO `type` VALUES (1157, '裸蛋糕', 1146, 3, 0, '2023-03-27 13:11:44', '2023-03-27 13:11:44');
INSERT INTO `type` VALUES (1158, '抹茶蛋糕', 1146, 3, 0, '2023-03-27 13:11:44', '2023-03-27 13:11:44');
INSERT INTO `type` VALUES (1159, '8寸蛋糕', 1146, 3, 0, '2023-03-27 13:11:44', '2023-03-27 13:11:44');
INSERT INTO `type` VALUES (1160, '草莓蛋糕', 1146, 3, 0, '2023-03-27 13:11:44', '2023-03-27 13:11:44');
INSERT INTO `type` VALUES (1161, '玛芬', 1146, 3, 0, '2023-03-27 13:11:44', '2023-03-27 13:11:44');
INSERT INTO `type` VALUES (1162, '黑森林', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1163, '玛德琳', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1164, '蒙布朗', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1165, '裱花蛋糕', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1166, '翻糖蛋糕', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1167, '彩虹蛋糕', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1168, '歌剧院', 1146, 3, 0, '2023-03-27 13:11:45', '2023-03-27 13:11:45');
INSERT INTO `type` VALUES (1169, '蜂窝蛋糕', 1146, 3, 0, '2023-03-27 13:11:46', '2023-03-27 13:11:46');
INSERT INTO `type` VALUES (1170, '大理石蛋糕', 1146, 3, 0, '2023-03-27 13:11:46', '2023-03-27 13:11:46');
INSERT INTO `type` VALUES (1171, '红丝绒', 1146, 3, 0, '2023-03-27 13:11:46', '2023-03-27 13:11:46');
INSERT INTO `type` VALUES (1172, '10寸蛋糕', 1146, 3, 0, '2023-03-27 13:11:46', '2023-03-27 13:11:46');
INSERT INTO `type` VALUES (1173, '12寸蛋糕', 1146, 3, 0, '2023-03-27 13:11:46', '2023-03-27 13:11:46');
INSERT INTO `type` VALUES (1174, '100%号烘培甄选', 1146, 3, 0, '2023-03-27 13:11:46', '2023-03-27 13:11:46');
INSERT INTO `type` VALUES (1175, '其他', 1145, 2, 0, '2023-03-27 13:11:47', '2023-03-27 13:11:47');
INSERT INTO `type` VALUES (1176, '蛋挞', 1175, 3, 0, '2023-03-27 13:11:47', '2023-03-27 13:11:47');

SET FOREIGN_KEY_CHECKS = 1;
