

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `disable` tinyint(4) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户群组表';

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('1', '超级管理员', null, '1502176570185', '1502176570185', '1', '1');
INSERT INTO `t_group` VALUES ('2', '公司领导', null, '1502176570185', '1502176570185', '1', '1');
INSERT INTO `t_group` VALUES ('3', '销售管理', null, '1502176570185', '1502176570185', '1', '1');
INSERT INTO `t_group` VALUES ('4', '测试群组', null, '1502176570185', '1502264450218', '1', null);

-- ----------------------------
-- Table structure for `t_group_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_group_rule`;
CREATE TABLE `t_group_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) DEFAULT NULL,
  `rule_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='群组规则对应表';

-- ----------------------------
-- Records of t_group_rule
-- ----------------------------
INSERT INTO `t_group_rule` VALUES ('84', '4', '11');
INSERT INTO `t_group_rule` VALUES ('83', '4', '12');
INSERT INTO `t_group_rule` VALUES ('82', '4', '3');
INSERT INTO `t_group_rule` VALUES ('66', '1', '12');
INSERT INTO `t_group_rule` VALUES ('65', '1', '11');
INSERT INTO `t_group_rule` VALUES ('64', '1', '10');
INSERT INTO `t_group_rule` VALUES ('81', '4', '1');
INSERT INTO `t_group_rule` VALUES ('80', '4', '2');
INSERT INTO `t_group_rule` VALUES ('63', '1', '9');
INSERT INTO `t_group_rule` VALUES ('62', '1', '8');
INSERT INTO `t_group_rule` VALUES ('61', '1', '7');
INSERT INTO `t_group_rule` VALUES ('60', '1', '6');
INSERT INTO `t_group_rule` VALUES ('59', '1', '5');
INSERT INTO `t_group_rule` VALUES ('58', '1', '4');
INSERT INTO `t_group_rule` VALUES ('38', '2', '2');
INSERT INTO `t_group_rule` VALUES ('39', '2', '5');
INSERT INTO `t_group_rule` VALUES ('40', '2', '6');
INSERT INTO `t_group_rule` VALUES ('41', '2', '1');
INSERT INTO `t_group_rule` VALUES ('57', '1', '3');
INSERT INTO `t_group_rule` VALUES ('56', '1', '2');
INSERT INTO `t_group_rule` VALUES ('55', '1', '1');
INSERT INTO `t_group_rule` VALUES ('67', '1', '13');
INSERT INTO `t_group_rule` VALUES ('68', '1', '14');
INSERT INTO `t_group_rule` VALUES ('79', '4', '14');
INSERT INTO `t_group_rule` VALUES ('78', '4', '8');
INSERT INTO `t_group_rule` VALUES ('77', '4', '5');

-- ----------------------------
-- Table structure for `t_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_rule`;
CREATE TABLE `t_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `rule` varchar(255) DEFAULT NULL COMMENT '规则，可以是网址或权限码',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型 1主模块 2子功能 3子权限',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `condition` varchar(255) DEFAULT NULL COMMENT '规则条件',
  `description` varchar(500) DEFAULT NULL COMMENT '规则说明',
  `pinyin` varchar(200) DEFAULT NULL COMMENT '拼音',
  `py` varchar(50) DEFAULT NULL COMMENT '拼音首字母',
  `create_date` bigint(20) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `disable` tinyint(4) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c0jkwht6ywnk1ybp47l3lirb4` (`creator_id`),
  KEY `FK_4pehd7q9yldy13rcmaq6qxgg5` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='权限规则表';

-- ----------------------------
-- Records of t_rule
-- ----------------------------
INSERT INTO `t_rule` VALUES ('1', '系统管理', '#', '1', 'fa fa-cog', '', '', null, null, '1502176570185', '1502176570185', '1', '1', null);
INSERT INTO `t_rule` VALUES ('2', '用户管理', 'user', '2', 'fa fa-user', '', '', 'yonghuguanli', 'yhgl', '1502176570185', '1502176570185', '1', '1', '1');
INSERT INTO `t_rule` VALUES ('3', '群组管理', 'group', '2', 'fa fa-group', '', '', 'qunzuguanli', 'qzgl', '1502176570185', '1502176570185', '1', '1', '1');
INSERT INTO `t_rule` VALUES ('4', '规则管理', 'rule', '2', 'fa fa-bars', '', '', 'guizeguanli', 'gzgl', '1502176570185', '1502176570185', '1', '1', '1');
INSERT INTO `t_rule` VALUES ('5', '编辑', 'user:edit', '3', 'fa fa-circle-o', null, null, null, null, '1502176570185', '1502176570185', '1', '1', '2');
INSERT INTO `t_rule` VALUES ('6', '浏览', 'user:view', '3', 'fa fa-circle-o', null, null, null, null, '1502176570185', '1502176570185', '1', '1', '2');
INSERT INTO `t_rule` VALUES ('7', '编辑', 'group:edit', '3', 'fa fa-circle-o', null, null, null, null, '1502176570185', '1502176570185', '1', '1', '3');
INSERT INTO `t_rule` VALUES ('8', '浏览', 'group:view', '3', 'fa fa-circle-o', null, null, null, null, '1502176570185', '1502176570185', '1', '1', '3');
INSERT INTO `t_rule` VALUES ('9', '编辑', 'rule:edit', '3', 'fa fa-circle-o', null, null, null, null, '1502176570185', '1502176570185', '1', '1', '4');
INSERT INTO `t_rule` VALUES ('10', '浏览', 'rule:view', '3', 'fa fa-circle-o', null, null, null, null, '1502176570185', '1502176570185', '1', '1', '4');
INSERT INTO `t_rule` VALUES ('11', '企业微信', '#', '1', 'fa fa-wechat', '', '', null, null, '1502176570185', '1502176570185', '1', null, null);
INSERT INTO `t_rule` VALUES ('12', '配置管理', 'wechat/config', '2', 'fa fa-cog', '', '', 'peizhiguanli', 'pzgl', '1502176570185', '1502176570185', '1', null, '11');
INSERT INTO `t_rule` VALUES ('13', '编辑', 'wechat:config:edit', '3', 'fa fa-circle-o', '', '', null, null, '1502176570185', '1502176570185', '1', null, '12');
INSERT INTO `t_rule` VALUES ('14', '浏览', 'wechat:config:view', '3', 'fa fa-circle-o', '', '', null, null, '1502176570185', '1502176570185', '1', null, '12');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL COMMENT '头像',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `create_date` bigint(20) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `disable` tinyint(4) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t78l2cirmxpv84uno3lccys6k` (`username`),
  KEY `FK_6coqltcpch9xs4klqv0l153h2` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'ADMIN', '570d5a41541fa023c013b28af415ac87e281adc0', 'b81f7c116271946a', '管理员', 'admin@ssopay.com', '13888888999', '', '', '1502264450218', '1503042948534', '1', '1');
INSERT INTO `t_user` VALUES ('2', 'LIUYES', 'a5e2a61fbc7b79ae45fcc6135d5362522ac707b2', '92a856b031ebae9a', '采飞扬', '1@ssopay.com', '13888888888', '', '', '1502264450218', '1503017973707', '1', null);
INSERT INTO `t_user` VALUES ('3', 'LIUYES2', '8ada0f5e8c2d6b527fe3f3b288051e2454ffde66', 'f31e58aece548759', '采飞扬2', '2@ssopay.com', '13888888888', null, '', '1502176570185', '1502176570185', '1', null);
INSERT INTO `t_user` VALUES ('4', 'LIUYES3', '3cdeb84cab14acaf86341916736f60f630cb7ff8', '56ab2f3ca255541a', '采飞扬3', '3@ssopay.com', '13888888888', null, '', '1502176570185', '1502176570185', '1', null);
INSERT INTO `t_user` VALUES ('5', 'LIUYES4', 'a26d3fd434c60868bad873b1a38ff28e8229eb28', '2dfc57f8dc6aba56', '采飞扬4', '4@ssopay.com', '13888888888', null, '', '1502176570185', '1502176570185', '1', null);
INSERT INTO `t_user` VALUES ('6', 'LIUYES5', '47f735ad805b148404b1ee48db26bc78ffb30141', '526698550082051e', '采飞扬5', '5@ssopay.com', '13888888888', null, '', '1502176570185', '1502176570185', '1', null);
INSERT INTO `t_user` VALUES ('7', 'LIUYES6', null, null, '采飞扬6', 'test@qq.com', '13888888999', null, null, '1502176570185', '1502176570185', '1', '1');
INSERT INTO `t_user` VALUES ('8', 'LIUYES7', null, null, null, null, null, null, null, '1502176570185', '1502176570185', '-1', '2');
INSERT INTO `t_user` VALUES ('9', 'LIUYES8', null, null, null, null, null, null, null, '1502176570185', '1502176570185', '-1', '3');
INSERT INTO `t_user` VALUES ('10', 'LIUYES9', null, null, null, null, null, null, null, '1502176570185', '1502176570185', '-1', '4');
INSERT INTO `t_user` VALUES ('11', 'LIUYES10', null, null, null, null, null, null, null, '1502176570185', '1502176570185', '-1', '5');
INSERT INTO `t_user` VALUES ('12', 'LIUYES11', null, null, null, null, null, null, null, '1502176570185', '1502176570185', '-1', '6');
INSERT INTO `t_user` VALUES ('15', 'LIUYES12', 'd1e78d73e40aa94bb1bcee97bb1d1da3928e395d', 'ac4541c710b3b02b', '采飞扬12', 'test@qq.com', '13888888999', null, null, '1502176570185', '1502176570185', '1', null);
INSERT INTO `t_user` VALUES ('16', 'LIUYES13', '8779a51db28c261f770dd1ba4046ff8c09bd20ab', '5ed107f54e56d594', '采飞扬13', 'test@qq.com', '13888888999', null, null, '1502176570185', '1502176570185', '1', null);

-- ----------------------------
-- Table structure for `t_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8lc3paikf472d23sd5xk07xiu` (`group_id`),
  KEY `FK_dimglsguye2195cu29s6o8urh` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='用户群组对应表';

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES ('3', '3', '3');
INSERT INTO `t_user_group` VALUES ('6', '16', '1');
INSERT INTO `t_user_group` VALUES ('16', '4', '2');
INSERT INTO `t_user_group` VALUES ('17', '4', '3');
INSERT INTO `t_user_group` VALUES ('20', '6', '2');
INSERT INTO `t_user_group` VALUES ('21', '6', '3');
INSERT INTO `t_user_group` VALUES ('26', '7', '2');
INSERT INTO `t_user_group` VALUES ('30', '2', '1');
INSERT INTO `t_user_group` VALUES ('31', '2', '2');
INSERT INTO `t_user_group` VALUES ('34', '1', '1');
