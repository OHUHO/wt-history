/*
 Navicat Premium Data Transfer

 Source Server         : sys_mysql_3306
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : history_platform

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 13/07/2024 10:30:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for h_admin
-- ----------------------------
DROP TABLE IF EXISTS `h_admin`;
CREATE TABLE `h_admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '头像',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '描述',
  `is_alive` int DEFAULT '0' COMMENT '是否被禁',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_admin
-- ----------------------------
BEGIN;
INSERT INTO `h_admin` (`admin_id`, `username`, `password`, `avatar`, `description`, `is_alive`, `is_delete`, `create_time`) VALUES (1, '京茶吉鹿', '123456', NULL, NULL, 0, 0, '2023-02-19 15:40:29');
COMMIT;

-- ----------------------------
-- Table structure for h_article
-- ----------------------------
DROP TABLE IF EXISTS `h_article`;
CREATE TABLE `h_article` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文章标题',
  `article_cover` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文章封面',
  `article_summary` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '文章摘要',
  `article_author_id` bigint DEFAULT NULL COMMENT '文章作者id',
  `article_content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '文章内容（富文本）',
  `article_content_html` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '文章内容（html）',
  `article_dynasty_id` int DEFAULT NULL COMMENT '文章属于的朝代id',
  `article_label_id` int DEFAULT NULL COMMENT '文章属于的词条标签',
  `article_event` tinytext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '文章事件',
  `good_num` bigint DEFAULT '0' COMMENT '文章获赞数量',
  `is_alive` int DEFAULT '1' COMMENT '文章是否被审核通过',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文章发布时间',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_article
-- ----------------------------
BEGIN;
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (1, '比殷墟更早！为什么说良渚遗址是中华五千年文明的实证？', NULL, '良渚古城是当时世界上最大的城市之一，它所代表的良渚文明，完全可以和同', 1, '“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n', '<p>“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n</p>', 1, NULL, '良渚文明', 1, 0, 0, '2023-02-17 10:02:20');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (2, '新手也能get的大唐流行妆', NULL, '时过境迁，虽然今日的审美相比于一千多年前已然发生了翻天覆地的变化，但女子们的爱美之心却始终如一……', 1, '各位唐朝观众朋友们大家好，我是你们练习时长两年半的美妆博主甲老师。\r\n\r\n最近听很多长安的粉丝反映说甲老师分享的内容比较难懂，新人上手会遇到很多问题，所以甲老师特地策划了今天的这个专题，跟大家分享一个“新手也能get的大唐流行妆”，七个步骤教你化出长安最流行的妆容，时髦值不输杨贵妃。\r\n\r\n（以下多图预警）\r\n\r\n\r\n...底妆\r\n\r\n化妆的第一步当然是要先上底妆，我们的皮肤就好像一张画布，要在上妆前将画布上的瑕疵都遮盖住，才能更好地进行“作画”。\r\n\r\n有些小仙女不知道，其实这个傅面的粉也是有区别的。在先秦到汉代这段时间，妹子们把米磨成粉就来抹底妆了。\r\n\r\n后来又有人发明了用粱米和粟米发酵制成的粉，北魏贾思勰把这种做法记了下来，现在我们很多长安的妹子用的也是这种粉。听说这种制粉的办法沿用了一千多年。各位唐朝观众朋友们大家好，我是你们练习时长两年半的美妆博主甲老师。\r\n\r\n最近听很多长安的粉丝反映说甲老师分享的内容比较难懂，新人上手会遇到很多问题，所以甲老师特地策划了今天的这个专题，跟大家分享一个“新手也能get的大唐流行妆”，七个步骤教你化出长安最流行的妆容，时髦值不输杨贵妃。\r\n\r\n（以下多图预警）\r\n\r\n\r\n...底妆\r\n\r\n化妆的第一步当然是要先上底妆，我们的皮肤就好像一张画布，要在上妆前将画布上的瑕疵都遮盖住，才能更好地进行“作画”。\r\n\r\n有些小仙女不知道，其实这个傅面的粉也是有区别的。在先秦到汉代这段时间，妹子们把米磨成粉就来抹底妆了。\r\n\r\n后来又有人发明了用粱米和粟米发酵制成的粉，北魏贾思勰把这种做法记了下来，现在我们很多长安的妹子用的也是这种粉。听说这种制粉的办法沿用了一千多年。', '<p>各位唐朝观众朋友们大家好，我是你们练习时长两年半的美妆博主甲老师。\r\n\r\n最近听很多长安的粉丝反映说甲老师分享的内容比较难懂，新人上手会遇到很多问题，所以甲老师特地策划了今天的这个专题，跟大家分享一个“新手也能get的大唐流行妆”，七个步骤教你化出长安最流行的妆容，时髦值不输杨贵妃。\r\n\r\n（以下多图预警）\r\n\r\n\r\n...\r\n底妆\r\n \r\n\r\n化妆的第一步当然是要先上底妆，我们的皮肤就好像一张画布，要在上妆前将画布上的瑕疵都遮盖住，才能更好地进行“作画”。\r\n\r\n有些小仙女不知道，其实这个傅面的粉也是有区别的。在先秦到汉代这段时间，妹子们把米磨成粉就来抹底妆了。\r\n\r\n后来又有人发明了用粱米和粟米发酵制成的粉，北魏贾思勰把这种做法记了下来，现在我们很多长安的妹子用的也是这种粉。听说这种制粉的办法沿用了一千多年。</p>', 1, NULL, '唐装', 44, 0, 0, '2023-02-17 10:09:20');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (3, '爱因斯坦大脑、肖邦心脏、伽利略手指……当个名人有多惨？', NULL, '人们总是执着地通过对名人旧物的留恋来挽留那些早已逝去的灵魂，就连他们的器官也不放过……', 1, '随着生活物质水平的提高，搞点收藏也不是什么新鲜事了，画风也越来越猎奇。明星中有不少收藏怪咖，连一向严肃的博物馆也不正经起来了：失恋、UFO、死亡、厕所……各种正常人无法想象的主题层出不绝。但最重口味的，还是当属收藏名人器官的怪癖了！\r\n\r\n \r\n\r\n...\r\n爱因斯坦的大脑：智商太高，怪我咯\r\n \r\n\r\n众所周知，犹太人是出了名的聪明，单说世界顶级理论物理学家，十有八九都是犹太人。而爱因斯坦就是其中的翘楚。然而，人怕出名猪怕壮，估计连爱因斯坦本人都想不到，因为名声太响，他死后连大脑都被人偷了！', '<p>随着生活物质水平的提高，搞点收藏也不是什么新鲜事了，画风也越来越猎奇。明星中有不少收藏怪咖，连一向严肃的博物馆也不正经起来了：失恋、UFO、死亡、厕所……各种正常人无法想象的主题层出不绝。但最重口味的，还是当属收藏名人器官的怪癖了！\r\n\r\n \r\n\r\n...\r\n爱因斯坦的大脑：智商太高，怪我咯\r\n \r\n\r\n众所周知，犹太人是出了名的聪明，单说世界顶级理论物理学家，十有八九都是犹太人。而爱因斯坦就是其中的翘楚。然而，人怕出名猪怕壮，估计连爱因斯坦本人都想不到，因为名声太响，他死后连大脑都被人偷了！</p>', 1, NULL, '物理学家', 1, 0, 0, '2023-02-17 11:30:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (4, '最后的匈奴王城：被北魏拓跋焘所灭，城池毁于北宋', NULL, '大夏国虽则早已同赫连勃勃“一统万邦”的野心一道灰飞烟灭，但统万城灰白的残垣，却依旧书写着当年这群匈奴人的辉煌……', 1, '在一锄头下去就是一座古墓的西安，距离其将近8个小时车程的统万城实在显得太不起眼。它坐落在陕西省靖边县和横山县的交界处，位置偏僻，交通不便，即使走近一看，也不过是一堆堆黄土累成的堡垒。\r\n\r\n殊不知这片千疮百孔的废墟，竟是曾经的大夏国王都，也是匈奴人在世界上唯一的都城遗址，距今已有1600年的历史……', '<p>在一锄头下去就是一座古墓的西安，距离其将近8个小时车程的统万城实在显得太不起眼。它坐落在陕西省靖边县和横山县的交界处，位置偏僻，交通不便，即使走近一看，也不过是一堆堆黄土累成的堡垒。\r\n\r\n殊不知这片千疮百孔的废墟，竟是曾经的大夏国王都，也是匈奴人在世界上唯一的都城遗址，距今已有1600年的历史……</p>', 1, NULL, '匈奴王城', 1, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (5, '最后的匈奴王城：被北魏拓跋焘所灭，城池毁于北宋', NULL, '大夏国虽则早已同赫连勃勃“一统万邦”的野心一道灰飞烟灭，但统万城灰白的残垣，却依旧书写着当年这群匈奴人的辉煌……', 1, '在一锄头下去就是一座古墓的西安，距离其将近8个小时车程的统万城实在显得太不起眼。它坐落在陕西省靖边县和横山县的交界处，位置偏僻，交通不便，即使走近一看，也不过是一堆堆黄土累成的堡垒。\r\n\r\n殊不知这片千疮百孔的废墟，竟是曾经的大夏国王都，也是匈奴人在世界上唯一的都城遗址，距今已有1600年的历史……\r\n\r\n \r\n\r\n...\r\n统一万邦·赫连勃勃的野心\r\n \r\n\r\n众所周知，作为草原游牧民族的匈奴人，其王庭总是随着政权的迁移而随时发生变动。', '<p>在一锄头下去就是一座古墓的西安，距离其将近8个小时车程的统万城实在显得太不起眼。它坐落在陕西省靖边县和横山县的交界处，位置偏僻，交通不便，即使走近一看，也不过是一堆堆黄土累成的堡垒。\r\n\r\n殊不知这片千疮百孔的废墟，竟是曾经的大夏国王都，也是匈奴人在世界上唯一的都城遗址，距今已有1600年的历史……\r\n\r\n \r\n\r\n...\r\n统一万邦·赫连勃勃的野心\r\n \r\n\r\n众所周知，作为草原游牧民族的匈奴人，其王庭总是随着政权的迁移而随时发生变动。</p>', 1, NULL, '匈奴王城', 1, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (6, '新手也能get的大唐流行妆', NULL, '时过境迁，虽然今日的审美相比于一千多年前已然发生了翻天覆地的变化，但女子们的爱美之心却始终如一……', 1, '各位唐朝观众朋友们大家好，我是你们练习时长两年半的美妆博主甲老师。\r\n\r\n最近听很多长安的粉丝反映说甲老师分享的内容比较难懂，新人上手会遇到很多问题，所以甲老师特地策划了今天的这个专题，跟大家分享一个“新手也能get的大唐流行妆”，七个步骤教你化出长安最流行的妆容，时髦值不输杨贵妃。\r\n\r\n（以下多图预警）\r\n\r\n\r\n...\r\n底妆\r\n \r\n\r\n化妆的第一步当然是要先上底妆，我们的皮肤就好像一张画布，要在上妆前将画布上的瑕疵都遮盖住，才能更好地进行“作画”。\r\n\r\n有些小仙女不知道，其实这个傅面的粉也是有区别的。在先秦到汉代这段时间，妹子们把米磨成粉就来抹底妆了。\r\n\r\n后来又有人发明了用粱米和粟米发酵制成的粉，北魏贾思勰把这种做法记了下来，现在我们很多长安的妹子用的也是这种粉。听说这种制粉的办法沿用了一千多年。', '<p>各位唐朝观众朋友们大家好，我是你们练习时长两年半的美妆博主甲老师。\r\n\r\n最近听很多长安的粉丝反映说甲老师分享的内容比较难懂，新人上手会遇到很多问题，所以甲老师特地策划了今天的这个专题，跟大家分享一个“新手也能get的大唐流行妆”，七个步骤教你化出长安最流行的妆容，时髦值不输杨贵妃。\r\n\r\n（以下多图预警）\r\n\r\n\r\n...\r\n底妆\r\n \r\n\r\n化妆的第一步当然是要先上底妆，我们的皮肤就好像一张画布，要在上妆前将画布上的瑕疵都遮盖住，才能更好地进行“作画”。\r\n\r\n有些小仙女不知道，其实这个傅面的粉也是有区别的。在先秦到汉代这段时间，妹子们把米磨成粉就来抹底妆了。\r\n\r\n后来又有人发明了用粱米和粟米发酵制成的粉，北魏贾思勰把这种做法记了下来，现在我们很多长安的妹子用的也是这种粉。听说这种制粉的办法沿用了一千多年。</p>', 2, NULL, '唐装', 0, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (7, '爱因斯坦大脑、肖邦心脏、伽利略手指……当个名人有多惨？', NULL, '人们总是执着地通过对名人旧物的留恋来挽留那些早已逝去的灵魂，就连他们的器官也不放过……', 1, '随着生活物质水平的提高，搞点收藏也不是什么新鲜事了，画风也越来越猎奇。明星中有不少收藏怪咖，连一向严肃的博物馆也不正经起来了：失恋、UFO、死亡、厕所……各种正常人无法想象的主题层出不绝。但最重口味的，还是当属收藏名人器官的怪癖了！\r\n\r\n \r\n\r\n...\r\n爱因斯坦的大脑：智商太高，怪我咯\r\n \r\n\r\n众所周知，犹太人是出了名的聪明，单说世界顶级理论物理学家，十有八九都是犹太人。而爱因斯坦就是其中的翘楚。然而，人怕出名猪怕壮，估计连爱因斯坦本人都想不到，因为名声太响，他死后连大脑都被人偷了！', '<p>随着生活物质水平的提高，搞点收藏也不是什么新鲜事了，画风也越来越猎奇。明星中有不少收藏怪咖，连一向严肃的博物馆也不正经起来了：失恋、UFO、死亡、厕所……各种正常人无法想象的主题层出不绝。但最重口味的，还是当属收藏名人器官的怪癖了！\r\n\r\n \r\n\r\n...\r\n爱因斯坦的大脑：智商太高，怪我咯\r\n \r\n\r\n众所周知，犹太人是出了名的聪明，单说世界顶级理论物理学家，十有八九都是犹太人。而爱因斯坦就是其中的翘楚。然而，人怕出名猪怕壮，估计连爱因斯坦本人都想不到，因为名声太响，他死后连大脑都被人偷了！</p>', 2, NULL, '物理学家', 1, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (8, '比殷墟更早！为什么说良渚遗址是中华五千年文明的实证？', NULL, '良渚古城是当时世界上最大的城市之一，它所代表的良渚文明，完全可以和同', 1, '“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n', '<p>“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n</p>', 2, NULL, NULL, 0, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (9, '爱因斯坦大脑、肖邦心脏、伽利略手指……当个名人有多惨？', NULL, '大夏国虽则早已同赫连勃勃“一统万邦”的野心一道灰飞烟灭，但统万城灰白的残垣，却依旧书写着当年这群匈奴人的辉煌……', 1, '在一锄头下去就是一座古墓的西安，距离其将近8个小时车程的统万城实在显得太不起眼。它坐落在陕西省靖边县和横山县的交界处，位置偏僻，交通不便，即使走近一看，也不过是一堆堆黄土累成的堡垒。\r\n\r\n殊不知这片千疮百孔的废墟，竟是曾经的大夏国王都，也是匈奴人在世界上唯一的都城遗址，距今已有1600年的历史……', '<p>在一锄头下去就是一座古墓的西安，距离其将近8个小时车程的统万城实在显得太不起眼。它坐落在陕西省靖边县和横山县的交界处，位置偏僻，交通不便，即使走近一看，也不过是一堆堆黄土累成的堡垒。\r\n\r\n殊不知这片千疮百孔的废墟，竟是曾经的大夏国王都，也是匈奴人在世界上唯一的都城遗址，距今已有1600年的历史……</p>', 1, NULL, NULL, 1, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (10, '最后的匈奴王城：被北魏拓跋焘所灭，城池毁于北宋', NULL, '时过境迁，虽然今日的审美相比于一千多年前已然发生了翻天覆地的变化，但女子们的爱美之心却始终如一……', 1, '“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n', '<p>“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n</p>', 1, NULL, NULL, 0, 0, 1, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (11, '最后的匈奴王城：被北魏拓跋焘所灭，城池毁于北宋', NULL, '时过境迁，虽然今日的审美相比于一千多年前已然发生了翻天覆地的变化，但女子们的爱美之心却始终如一……', 1, '“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n', '<p>“打脸来得太快，就像龙卷风”不止适用于酷爱立人设的娱乐圈，历史界也屡试不爽。一直以来，西方学者都认为中华文明只始于距今3500年前后的殷商时期，没想到今年7月份“良渚古城遗址”就被联合国教科文组织列入了《世界遗产名录》。这意味着什么？\r\n\r\n中国新石器这一被远远低估的时代正清晰地展现在世人面前，诉说着来自五千多年前的文明，它改写了中国历史，更改写了世界历史！\r\n\r\n80年前的意外惊喜\r\n \r\n\r\n良渚文明的发现震惊海内外，但很少有人知道为了挖掘出这颗蒙尘的珍珠，竟走过了80个漫长岁月……\r\n\r\n良渚，指的是距今5300-4300年期间，在中国南方的长江下游环太湖流域曾经存在过的一个文明，它是迄今发现的中国大地上最早的国家。以良渚古城为核心的遗址就是良渚文明的中心。而关于它的发现，还要回溯到1936年。\r\n\r\n当时，浙江省立西湖博物馆的一位馆员施昕更，在对位于古荡的新石器时代遗址进行考古发掘时发现了一件黑陶。他觉得，类似的黑陶在自己的家乡良渚也曾见到过。于是不久后他就回到良渚，进行文物调查。\r\n\r\n在他的主持下，良渚陆续出土了黑陶、石器、玉器等数百件文物。这一次“小”发现，正式掀开了良渚遗址的一角。然而，随着次年抗战的爆发，良渚遗址的发掘和研究工作不得不被迫中断。\r\n\r\n</p>', 1, NULL, NULL, 0, 0, 0, '2023-02-17 12:10:12');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (12, '这是我发布的第一篇文章', 'http://localhost:8887/files/user/user-id-1/article/2023-02-19/cover/1676768143110_.jpg', '这是文章的一段摘要内容', 1, '**这是我发布的第一篇文章**\n# 下面是我使用富文本编辑器编写的内容\n## 这是一个二级标题\n### 这是一个三级标题\n#### 富文本标题的格式，几级标题，开始处就有几个 井号 ，需要注意的是，井号后面需要加一个空格，才能生效\n下面将是一张图片\n![20220514111007_ff92e.jpeg](http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676768124547_.jpg)', '<p><strong>这是我发布的第一篇文章</strong></p>\n<h1><a id=\"_1\"></a>下面是我使用富文本编辑器编写的内容</h1>\n<h2><a id=\"_2\"></a>这是一个二级标题</h2>\n<h3><a id=\"_3\"></a>这是一个三级标题</h3>\n<h4><a id=\"___4\"></a>富文本标题的格式，几级标题，开始处就有几个 井号 ，需要注意的是，井号后面需要加一个空格，才能生效</h4>\n<p>下面将是一张图片<br />\n<img src=\"http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676768124547_.jpg\" alt=\"20220514111007_ff92e.jpeg\" /></p>\n', 3, 5, '', 1, 0, 0, '2023-02-19 08:56:05');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (13, '静物：红卷心菜和洋葱', 'http://localhost:8887/files/user/user-id-1/article/2023-02-19/cover/1676768420132_.jpg', '1881年，梵高以传统的画法以及深暗的色彩画了一幅卷心菜和洋葱的静物作品。六年后在巴黎，他又采用了很相似的主题。这幅', 1, '# 静物：红卷心菜和洋葱\n==Still Life with Red Cabbages and Onions==\n文森特·梵高\n图片信息\n创作者：	文森特·梵高\n创作时间：	1887 年 （33岁）\n风格流派：	后印象派\n所属时期：	现代艺术\n创作地点：	法国，巴黎\n题材：	静物\n材质：	帆布 ,油画\n尺寸：	50.2×64.3cm\n收藏地点：	梵高博物馆\n\n## 画作描述\n1881年，梵高以传统的画法以及深暗的色彩画了一幅卷心菜和洋葱的静物作品。六年后在巴黎，他又采用了很相似的主题。这幅创作于1887年的静物油画充足地展现出了梵高作为艺术家所取得的长足进步。暗淡的调色板不见了，取而代之的是醒目而浓重的用色。画静物的传统方式被活跃的动态画法所代替，而这种动态画法正是梵高所推崇的典型绘画技巧。条纹式的效果可能是仿效版画的特征画法。但也有可能是梵高通过这种绘画技巧尝试静物画的新风格。\n\n## 画作推荐\n![grill.jpeg](http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676768344138_.jpg)', '<h1><a id=\"_0\"></a>静物：红卷心菜和洋葱</h1>\n<p><mark>Still Life with Red Cabbages and Onions</mark><br />\n文森特·梵高<br />\n图片信息<br />\n创作者：	文森特·梵高<br />\n创作时间：	1887 年 （33岁）<br />\n风格流派：	后印象派<br />\n所属时期：	现代艺术<br />\n创作地点：	法国，巴黎<br />\n题材：	静物<br />\n材质：	帆布 ,油画<br />\n尺寸：	50.2×64.3cm<br />\n收藏地点：	梵高博物馆</p>\n<h2><a id=\"_14\"></a>画作描述</h2>\n<p>1881年，梵高以传统的画法以及深暗的色彩画了一幅卷心菜和洋葱的静物作品。六年后在巴黎，他又采用了很相似的主题。这幅创作于1887年的静物油画充足地展现出了梵高作为艺术家所取得的长足进步。暗淡的调色板不见了，取而代之的是醒目而浓重的用色。画静物的传统方式被活跃的动态画法所代替，而这种动态画法正是梵高所推崇的典型绘画技巧。条纹式的效果可能是仿效版画的特征画法。但也有可能是梵高通过这种绘画技巧尝试静物画的新风格。</p>\n<h2><a id=\"_17\"></a>画作推荐</h2>\n<p><img src=\"http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676768344138_.jpg\" alt=\"grill.jpeg\" /></p>\n', 4, 1, '', 0, 0, 0, '2023-02-19 09:00:45');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (14, '（二）静物：红卷心菜和洋葱', 'http://localhost:8887/files/user/user-id-1/article/2023-02-19/cover/1676768548270_.jpg', '静物：红卷心菜和洋葱 梵高', 1, '# 静物：红卷心菜和洋葱\n==Still Life with Red Cabbages and Onions==\n文森特·梵高\n图片信息\n创作者：	文森特·梵高\n创作时间：	1887 年 （33岁）\n风格流派：	后印象派\n所属时期：	现代艺术\n创作地点：	法国，巴黎\n题材：	静物\n材质：	帆布 ,油画\n尺寸：	50.2×64.3cm\n收藏地点：	梵高博物馆\n\n## 画作描述\n1881年，梵高以传统的画法以及深暗的色彩画了一幅卷心菜和洋葱的静物作品。六年后在巴黎，他又采用了很相似的主题。这幅创作于1887年的静物油画充足地展现出了梵高作为艺术家所取得的长足进步。暗淡的调色板不见了，取而代之的是醒目而浓重的用色。画静物的传统方式被活跃的动态画法所代替，而这种动态画法正是梵高所推崇的典型绘画技巧。条纹式的效果可能是仿效版画的特征画法。但也有可能是梵高通过这种绘画技巧尝试静物画的新风格。\n\n## 画作推荐\n![grill.jpeg](http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676768344138_.jpg)', '<h1><a id=\"_0\"></a>静物：红卷心菜和洋葱</h1>\n<p><mark>Still Life with Red Cabbages and Onions</mark><br />\n文森特·梵高<br />\n图片信息<br />\n创作者：	文森特·梵高<br />\n创作时间：	1887 年 （33岁）<br />\n风格流派：	后印象派<br />\n所属时期：	现代艺术<br />\n创作地点：	法国，巴黎<br />\n题材：	静物<br />\n材质：	帆布 ,油画<br />\n尺寸：	50.2×64.3cm<br />\n收藏地点：	梵高博物馆</p>\n<h2><a id=\"_14\"></a>画作描述</h2>\n<p>1881年，梵高以传统的画法以及深暗的色彩画了一幅卷心菜和洋葱的静物作品。六年后在巴黎，他又采用了很相似的主题。这幅创作于1887年的静物油画充足地展现出了梵高作为艺术家所取得的长足进步。暗淡的调色板不见了，取而代之的是醒目而浓重的用色。画静物的传统方式被活跃的动态画法所代替，而这种动态画法正是梵高所推崇的典型绘画技巧。条纹式的效果可能是仿效版画的特征画法。但也有可能是梵高通过这种绘画技巧尝试静物画的新风格。</p>\n<h2><a id=\"_17\"></a>画作推荐</h2>\n<p><img src=\"http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676768344138_.jpg\" alt=\"grill.jpeg\" /></p>\n', 3, 5, '', 1, 0, 0, '2023-02-19 09:02:41');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (15, '静物：葡萄，梨和柠檬编辑画作  ', 'http://localhost:8887/files/user/user-id-1/article/2023-02-19/cover/1676768639699_.jpg', '静物：葡萄，梨和柠檬编辑画作  ，梵高作品', 1, '# 静物：葡萄，梨和柠檬编辑画作  \n扫一扫预览文章\nStill Life with Grapes, Pears and Lemons\n文森特·梵高\n图片信息\n创作者：	文森特·梵高\n创作时间：	1887 年 （33岁）\n风格流派：	后印象派\n所属时期：	现代艺术\n创作地点：	法国巴黎\n题材：	静物\n材质：	帆布,油画\n收藏地点：	梵高博物馆#', '<h1><a id=\"_0\"></a>静物：葡萄，梨和柠檬编辑画作</h1>\n<p>扫一扫预览文章<br />\nStill Life with Grapes, Pears and Lemons<br />\n文森特·梵高<br />\n图片信息<br />\n创作者：	文森特·梵高<br />\n创作时间：	1887 年 （33岁）<br />\n风格流派：	后印象派<br />\n所属时期：	现代艺术<br />\n创作地点：	法国巴黎<br />\n题材：	静物<br />\n材质：	帆布,油画<br />\n收藏地点：	梵高博物馆#</p>\n', 3, 2, '', 1, 0, 0, '2023-02-19 09:04:11');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (16, '发布文章——中国历史平台', 'http://localhost:8887/files/user/user-id-1/article/2023-02-19/cover/1676774843431_.jpg', '文章摘要', 1, '![1932202204111049231649645363820.jpg](http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676774837832_.jpg)', '<p><img src=\"http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676774837832_.jpg\" alt=\"1932202204111049231649645363820.jpg\" /></p>\n', 4, 8, '', 0, 0, 0, '2023-02-19 10:47:40');
INSERT INTO `h_article` (`article_id`, `article_title`, `article_cover`, `article_summary`, `article_author_id`, `article_content`, `article_content_html`, `article_dynasty_id`, `article_label_id`, `article_event`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (17, '中国历史平台——文章发布', 'http://localhost:8887/files/user/user-id-1/article/2023-02-19/cover/1676775468536_.jpg', '输入文章摘要', 1, '# 一级标题\n## 二级标题\n下面是图片\n![fullbackground.png](http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676775423769_.png)\n编辑器使用的是富文本，下面可以预览文章', '<h1><a id=\"_0\"></a>一级标题</h1>\n<h2><a id=\"_1\"></a>二级标题</h2>\n<p>下面是图片<br />\n<img src=\"http://localhost:8887/files/user/user-id-1/article/2023-02-19/1676775423769_.png\" alt=\"fullbackground.png\" /><br />\n编辑器使用的是富文本，下面可以预览文章</p>\n', 8, 5, '事件名称', 0, 1, 0, '2023-02-19 10:58:26');
COMMIT;

-- ----------------------------
-- Table structure for h_background
-- ----------------------------
DROP TABLE IF EXISTS `h_background`;
CREATE TABLE `h_background` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT ' ',
  `image_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图片背景',
  `describe` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '背景图片描述',
  `is_type` int DEFAULT NULL COMMENT '背景图片的类型（0：首页顶部，1：时间线条，2：时空地图，3：朝代背景）',
  `is_alive` int DEFAULT '0' COMMENT '是否启用该图片（每一种图片类型最多只能启用一张图片）',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_background
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for h_comment
-- ----------------------------
DROP TABLE IF EXISTS `h_comment`;
CREATE TABLE `h_comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` bigint DEFAULT NULL COMMENT '评论用户的id',
  `article_id` bigint DEFAULT NULL COMMENT '评论文章的id',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '评论的内容',
  `good_num` bigint DEFAULT '0' COMMENT '获赞数量',
  `is_alive` int DEFAULT '1' COMMENT '是否审核通过',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_comment
-- ----------------------------
BEGIN;
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (1, 1, 2, '评论《新手也能get的大唐流行妆》', 11, 0, 0, '2023-02-18 14:48:57');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (2, 1, 2, '第二条评论', 1, 1, 1, '2023-02-18 15:03:48');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (3, 1, 2, '第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论第三条评论哈哈哈哈', 0, 1, 0, '2023-02-18 15:06:23');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (4, 1, 1, '比殷墟更早！为什么说良渚遗址是中华五千年文明的实证？', 0, 0, 0, '2023-02-18 16:40:25');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (5, 1, 9, '评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容', 1, 1, 0, '2023-02-18 18:01:35');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (6, 1, 12, '评论内容：下面是我使用富文本编辑器编写的内容，希望多多点赞', 1, 0, 0, '2023-02-19 09:08:37');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (7, 1, 2, '下面是评论内容', 1, 0, 0, '2023-02-19 10:45:22');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (8, 1, 7, '评论内容', 0, 0, 0, '2023-02-19 10:54:45');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (9, 1, 14, '评论1', 0, 0, 0, '2023-02-19 10:59:20');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (10, 1, 13, '哈哈哈哈哈哈', 0, 1, 0, '2023-11-11 19:28:17');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (11, 1, 16, '哈哈好', 0, 1, 0, '2023-11-11 19:32:10');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (12, 1, 15, '微信公众号：京茶吉鹿', 0, 1, 0, '2024-06-26 23:35:00');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (13, 1, 15, '京茶吉鹿', 0, 1, 0, '2024-06-26 23:36:12');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (14, 1, 14, '微信公众号：京茶吉鹿', 0, 1, 0, '2024-06-26 23:37:45');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (15, 1, 2, '京茶吉鹿', 0, 0, 0, '2024-06-29 09:55:33');
INSERT INTO `h_comment` (`comment_id`, `user_id`, `article_id`, `content`, `good_num`, `is_alive`, `is_delete`, `create_time`) VALUES (16, 1, 2, '精彩绝伦', 0, 1, 0, '2024-06-29 09:58:12');
COMMIT;

-- ----------------------------
-- Table structure for h_dynasty
-- ----------------------------
DROP TABLE IF EXISTS `h_dynasty`;
CREATE TABLE `h_dynasty` (
  `dynasty_id` int NOT NULL AUTO_INCREMENT COMMENT '朝代id',
  `dynasty_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '朝代名字',
  `dynasty_describe` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '朝代的描述',
  `dynasty_cover` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '朝代的封面',
  `dynasty_period` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '朝代的时期',
  `dynasty_map` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '朝代的地图',
  `is_alive` int DEFAULT '0' COMMENT '朝代是否被关闭',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dynasty_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_dynasty
-- ----------------------------
BEGIN;
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (1, '文明曙光', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/knife4j-framework2.png', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (2, '夏朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline.jpg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/grill.jpeg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (3, '商朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao1.jpeg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (4, '西周', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao2.png', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (5, '春秋战国', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline.jpg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/jin.png', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (6, '秦朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (7, '汉朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (8, '三国两晋南北朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (9, '隋朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (10, '唐朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (11, '五代十国', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (12, '宋朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline.jpg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (13, '辽夏金', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline-1.jpeg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (14, '元朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline.jpg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (15, '明朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline.jpg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (16, '清朝', '中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。中国最早的古人类是170万年前的元谋人。此后人类不断繁衍，掌握了火焰和石器，在5万年前进化为现代人类。约1万年前，农业、畜牧业出现，各地涌现众多灿烂文明。', 'http://localhost:8887/files/dynasty-info/cover/timeline.jpg', '(约)公元前1700000年', 'http://localhost:8887/files/dynasty-info/maps/tangchao.jpg', 0, 0, '2023-02-17 18:42:30');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (17, '新朝代', '这是在中国历史平台后台，管理员添加的一个用于测试功能的朝代。', 'http://localhost:8887/files/dynasty-info/cover/1676991000507_.jpg', '2023年—2026年', 'http://localhost:8887/files/dynasty-info/maps/1676991022096_.png', 0, 0, '2023-02-21 08:30:05');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (18, NULL, NULL, 'http://localhost:8887/files/dynasty-info/cover/1676989132866_.jpg', NULL, 'http://localhost:8887/files/dynasty-info/maps/1676989132866_.jpg', 0, 1, '2023-02-21 22:18:55');
INSERT INTO `h_dynasty` (`dynasty_id`, `dynasty_name`, `dynasty_describe`, `dynasty_cover`, `dynasty_period`, `dynasty_map`, `is_alive`, `is_delete`, `create_time`) VALUES (19, '新时代', '新时代的中国青年', 'http://localhost:8887/files/dynasty-info/cover/1676991145918_.jpg', '2023年——XXX', 'http://localhost:8887/files/dynasty-info/maps/1676991145918_.jpg', 0, 1, '2023-02-21 22:52:32');
COMMIT;

-- ----------------------------
-- Table structure for h_event
-- ----------------------------
DROP TABLE IF EXISTS `h_event`;
CREATE TABLE `h_event` (
  `event_id` bigint NOT NULL AUTO_INCREMENT COMMENT '历史事件id',
  `event_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '时间名称',
  `dynasty_id` int DEFAULT NULL COMMENT '朝代id',
  `is_alive` int DEFAULT '0' COMMENT '是否被禁',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_event
-- ----------------------------
BEGIN;
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (1, '历史事件1', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (2, '历史事件2', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (3, '历史事件3', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (4, '历史事件4', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (5, '历史事件5', 2, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (6, '历史事件6', 2, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (7, '历史事件12', 1, 0, 0, '·2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (8, '历史事件13', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_event` (`event_id`, `event_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (9, '历史事件15', 2, 0, 0, '2023-02-18 18:34:10');
COMMIT;

-- ----------------------------
-- Table structure for h_good
-- ----------------------------
DROP TABLE IF EXISTS `h_good`;
CREATE TABLE `h_good` (
  `good_id` bigint NOT NULL AUTO_INCREMENT COMMENT '获赞id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `comment_id` bigint DEFAULT NULL COMMENT '评论的id',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`good_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_good
-- ----------------------------
BEGIN;
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (1, 1, 2, NULL, 1, '2023-02-18 15:56:12');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (2, 1, 2, 2, 1, '2023-02-18 16:18:14');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (3, 1, 2, 3, 1, '2023-02-18 16:38:55');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (4, 1, 1, NULL, 0, '2023-02-18 16:40:07');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (5, 1, 5, NULL, 0, '2023-02-18 16:40:49');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (6, 1, 4, NULL, 0, '2023-02-18 17:49:49');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (7, 1, 9, 5, 1, '2023-02-18 18:01:39');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (8, 1, 3, NULL, 1, '2023-02-18 18:27:39');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (9, 1, 12, NULL, 1, '2023-02-19 09:07:56');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (10, 1, 15, NULL, 0, '2023-02-19 09:09:14');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (11, 1, 14, NULL, 0, '2023-02-19 09:26:50');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (12, 1, 2, 7, 1, '2023-02-19 10:45:28');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (13, 1, 2, 7, 1, '2023-02-19 10:45:30');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (14, 1, 9, NULL, 1, '2023-02-19 10:48:35');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (15, 1, 7, NULL, 0, '2023-02-19 10:54:38');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (16, 1, 2, 1, 1, '2023-02-19 10:54:57');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (17, 1, 2, 1, 1, '2023-02-19 10:54:59');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (18, 1, 9, NULL, 0, '2023-02-19 10:55:48');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (19, 1, 12, 6, 1, '2023-02-19 10:59:43');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (20, 1, 12, NULL, 1, '2023-11-11 19:23:23');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (21, 1, 3, NULL, 0, '2023-11-11 19:25:51');
INSERT INTO `h_good` (`good_id`, `user_id`, `article_id`, `comment_id`, `is_delete`, `create_time`) VALUES (22, 1, 12, NULL, 0, '2024-06-26 23:00:05');
COMMIT;

-- ----------------------------
-- Table structure for h_label
-- ----------------------------
DROP TABLE IF EXISTS `h_label`;
CREATE TABLE `h_label` (
  `label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '词条标签id',
  `label_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '词条标签名字',
  `dynasty_id` int DEFAULT NULL COMMENT '朝代id',
  `is_alive` int DEFAULT '0' COMMENT '是否被禁用',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`label_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_label
-- ----------------------------
BEGIN;
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (1, '词条标签1', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (2, '词条标签2', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (3, '词条标签3', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (4, '词条标签4', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (5, '词条标签5', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (6, '词条标签6', 1, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (7, '词条标签8', 2, 0, 0, '2023-02-18 18:34:10');
INSERT INTO `h_label` (`label_id`, `label_name`, `dynasty_id`, `is_alive`, `is_delete`, `create_time`) VALUES (8, '词条标签12', 2, 0, 0, '2023-02-18 18:34:10');
COMMIT;

-- ----------------------------
-- Table structure for h_user
-- ----------------------------
DROP TABLE IF EXISTS `h_user`;
CREATE TABLE `h_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户名（注册手机号码）',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户头像',
  `is_alive` int DEFAULT '0' COMMENT '用户是否可用',
  `is_delete` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15284734594 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of h_user
-- ----------------------------
BEGIN;
INSERT INTO `h_user` (`user_id`, `username`, `password`, `nickname`, `avatar`, `is_alive`, `is_delete`, `create_time`) VALUES (1, '15284734528', '$2a$10$CMgDcoJGWY7COH9mvhGBduxR6Bs2vA4L/MuM9Y94rQS4/vxiALLJO', '中国历史平台', 'http://localhost:8887/files/user/user-id-1/info/2023-02-19/avatar/1676775533464_.jpg', 0, 0, '2023-02-17 17:00:09');
INSERT INTO `h_user` (`user_id`, `username`, `password`, `nickname`, `avatar`, `is_alive`, `is_delete`, `create_time`) VALUES (2, '15288473472', '$2a$10$QxekuO.pjeWJ4B3Md2ny5ekRf4zzk9wZoa/8pKNfM9/N7JnaRT9qG', '默认昵称', NULL, 0, 0, '2023-02-19 10:51:16');
INSERT INTO `h_user` (`user_id`, `username`, `password`, `nickname`, `avatar`, `is_alive`, `is_delete`, `create_time`) VALUES (3, '15284734589', '$2a$10$bpm7sO22iq9M3IpAM55n0.gpCPmDpcr3OxB/a/Vp7enS3WD.19agC', '默认昵称', NULL, 0, 0, '2023-02-19 10:53:56');
INSERT INTO `h_user` (`user_id`, `username`, `password`, `nickname`, `avatar`, `is_alive`, `is_delete`, `create_time`) VALUES (15284734593, '15283475469', '$2a$10$9E/J05i1yg3iovGigOshzOvlRg2bHDo3wVJucTFHyXJavK.uw/Jpm', '我的昵称', NULL, 0, 0, '2023-02-22 20:06:15');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
