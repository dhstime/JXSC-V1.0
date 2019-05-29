-- 1.5版本,为测试版,修复了部分表不匹配的问题

DROP database IF EXISTS jxsys;
CREATE database jxsys DEFAULT CHARACTER SET utf8;
USE jxsys;
SET FOREIGN_KEY_CHECKS=0;
--
-- Table structure for table `cart_table`
--

DROP TABLE IF EXISTS cart_table;
CREATE TABLE cart_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  product_id int(11) DEFAULT NULL,
  quantity int(11) DEFAULT NULL COMMENT '数量',
  checked int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  islike int(11) DEFAULT NULL COMMENT '是否关注,1=已关注,0=未关注',
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY user_id_index (user_id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart_table`
--

LOCK TABLES cart_table WRITE;

INSERT INTO cart_table VALUES (124,2,12,1,1,0,NULL,NULL),(125,2,11,1,0,1,NULL,NULL),(126,3,13,1,1,0,NULL,NULL),(127,3,24,0,0,1,NULL,NULL),(128,3,3,0,0,1,NULL,NULL),(129,4,5,3,1,0,NULL,NULL),(130,5,5,2,1,0,NULL,NULL),(131,426,5,2,1,0,NULL,NULL),(132,462,5,2,1,0,NULL,NULL),(133,462,3,1,1,0,NULL,NULL),(134,426,15,8,1,0,NULL,NULL),(142,28,2,2,1,0,'2019-04-23 17:15:27','2019-04-23 17:15:27'),(143,28,1,3,1,0,'2019-04-23 17:15:29','2019-04-23 17:15:29'),(144,28,7,11,1,0,'2019-04-23 17:15:35','2019-04-23 17:15:35'),(145,28,3,1,1,0,'2019-04-23 17:21:45','2019-04-23 17:21:45'),(146,28,5,1,1,0,'2019-04-23 17:25:37','2019-04-23 17:25:37'),(147,1,1,3,0,0,'2019-04-23 17:28:55','2019-04-23 17:28:55'),(148,1,2,0,1,0,'2019-04-23 17:28:57','2019-04-23 17:28:57'),(149,1,3,1,0,0,'2019-04-23 17:28:59','2019-04-23 17:28:59');

UNLOCK TABLES;

--
-- Table structure for table `inventory_table`
--

DROP TABLE IF EXISTS inventory_table;

CREATE TABLE inventory_table (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  product_id int(11) NOT NULL COMMENT '商品ID',
  current_count int(11) unsigned DEFAULT '0' COMMENT '当前商品数量',
  modified_time datetime DEFAULT NULL COMMENT '最后修改时间',
  sales_volume int(11) unsigned DEFAULT '0' COMMENT '销量',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id),
  UNIQUE KEY index_inventory (id)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `inventory_table`
--

LOCK TABLES inventory_table WRITE;

INSERT INTO inventory_table VALUES (3,3,551,'2019-04-23 15:52:30',11,'2019-04-20 14:39:24'),(4,4,121,'2019-04-20 14:39:24',100,'2019-04-20 14:39:24'),(5,5,690,'2019-04-20 14:39:24',60,'2019-04-20 14:39:24'),(7,7,10,'2019-04-20 14:39:25',320,'2019-04-20 14:39:25'),(8,8,0,'2019-04-20 14:39:25',100,'2019-04-20 14:39:25'),(9,9,112,'2019-04-20 14:39:25',100,'2019-04-20 14:39:25'),(10,10,177,'2019-04-20 14:39:25',140,'2019-04-20 14:39:25'),(11,11,178,'2019-04-20 14:39:25',100,'2019-04-20 14:39:25'),(12,12,540,'2019-04-20 14:39:25',340,'2019-04-20 14:39:25'),(13,13,500,'2019-04-20 14:39:25',100,'2019-04-20 14:39:25'),(14,14,456,'2019-04-20 14:39:25',120,'2019-04-20 14:39:25'),(15,15,10,'2019-04-20 14:39:25',251,'2019-04-20 14:39:25'),(16,16,1000,'2019-04-20 14:39:25',520,'2019-04-20 14:39:25'),(17,17,800,'2019-04-20 14:39:25',101,'2019-04-20 14:39:25'),(18,18,1000,'2019-04-20 14:39:25',190,'2019-04-20 14:39:25'),(19,19,170,'2019-04-20 14:39:25',100,'2019-04-20 14:39:25'),(20,20,450,'2019-04-20 14:39:25',130,'2019-04-20 14:39:25'),(21,21,197,'2019-04-20 14:39:25',147,'2019-04-20 14:39:25'),(22,22,120,'2019-04-20 14:39:25',140,'2019-04-20 14:39:25'),(24,24,400,'2019-04-20 14:39:25',180,'2019-04-20 14:39:25'),(25,67,100,'2019-04-23 15:44:29',0,NULL),(27,1,100,'2019-04-23 15:59:13',0,NULL),(28,211,2121,'2019-04-23 16:56:15',1111,NULL),(29,2,11,'2019-04-23 17:28:17',211,NULL),(30,121,100,'2019-04-23 17:43:49',0,NULL);

UNLOCK TABLES;

--
-- Table structure for table `logs_table`
--

DROP TABLE IF EXISTS logs_table;

CREATE TABLE logs_table (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(50) DEFAULT NULL COMMENT '用户名',
  operation varchar(50) DEFAULT NULL COMMENT '用户操作',
  method varchar(200) DEFAULT NULL COMMENT '请求方法',
  params varchar(5000) DEFAULT NULL COMMENT '请求参数',
  time bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  ip varchar(64) DEFAULT NULL COMMENT 'IP地址',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='系统日志';


--
-- Dumping data for table `logs_table`
--

LOCK TABLES logs_table WRITE;

INSERT INTO logs_table VALUES (9,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',3,'0:0:0:0:0:0:0:1','2018-04-17 19:53:38'),(10,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',2,'0:0:0:0:0:0:0:1','2018-04-17 19:54:05'),(11,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',1,'0:0:0:0:0:0:0:1','2018-04-17 19:54:36'),(12,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',155,'0:0:0:0:0:0:0:1','2018-04-18 15:14:44'),(13,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',165,'0:0:0:0:0:0:0:1','2018-04-19 18:52:35'),(14,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',75,'0:0:0:0:0:0:0:1','2018-04-19 19:10:36'),(15,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',69,'0:0:0:0:0:0:0:1','2018-04-19 19:12:46'),(16,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',187,'0:0:0:0:0:0:0:1','2018-04-19 23:27:14'),(17,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',103,'0:0:0:0:0:0:0:1','2018-04-20 13:11:37'),(18,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',85,'0:0:0:0:0:0:0:1','2018-04-20 13:55:04'),(19,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',89,'0:0:0:0:0:0:0:1','2018-04-20 13:57:12'),(20,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',69,'0:0:0:0:0:0:0:1','2018-04-20 13:58:32'),(21,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',291,'0:0:0:0:0:0:0:1','2018-04-20 15:22:55'),(22,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',158,'0:0:0:0:0:0:0:1','2018-04-22 16:20:56'),(23,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',94,'0:0:0:0:0:0:0:1','2018-04-22 17:05:34'),(24,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',138,'0:0:0:0:0:0:0:1','2018-04-22 17:20:32'),(25,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',124,'0:0:0:0:0:0:0:1','2018-04-22 17:24:12'),(26,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',75,'0:0:0:0:0:0:0:1','2018-04-22 17:31:51'),(27,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',148,'0:0:0:0:0:0:0:1','2018-04-22 17:33:25'),(28,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',69,'0:0:0:0:0:0:0:1','2018-04-22 17:39:26'),(29,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',120,'0:0:0:0:0:0:0:1','2018-04-22 19:10:28'),(30,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',104,'0:0:0:0:0:0:0:1','2018-04-22 19:23:56'),(31,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',79,'0:0:0:0:0:0:0:1','2018-04-22 19:42:40'),(32,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',71,'0:0:0:0:0:0:0:1','2018-04-22 19:58:49'),(33,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',201,'0:0:0:0:0:0:0:1','2018-04-22 20:02:01'),(34,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',119,'0:0:0:0:0:0:0:1','2018-04-22 20:26:31'),(35,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',131,'0:0:0:0:0:0:0:1','2018-04-22 20:58:21'),(36,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',9,'192.168.43.1','2018-04-22 21:32:25'),(37,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',6,'192.168.43.183','2018-04-22 21:34:40'),(38,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',4,'192.168.43.211','2018-04-22 22:10:04'),(39,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',71,'0:0:0:0:0:0:0:1','2018-04-23 08:47:21'),(40,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',4,'0:0:0:0:0:0:0:1','2018-04-23 18:22:51'),(41,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',133,'0:0:0:0:0:0:0:1','2018-04-28 17:51:31'),(42,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',185,'0:0:0:0:0:0:0:1','2018-05-15 23:22:01'),(43,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',114,'0:0:0:0:0:0:0:1','2018-05-16 09:55:16'),(44,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',184,'0:0:0:0:0:0:0:1','2018-05-20 08:05:15'),(45,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',254,'172.20.10.1','2018-05-27 10:22:56'),(46,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin \"',8,'172.20.10.9','2018-05-27 10:22:58'),(47,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',9,'172.20.10.11','2018-05-27 10:23:18'),(48,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',5,'172.20.10.14','2018-05-27 10:23:25'),(49,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin \"',5,'172.20.10.9','2018-05-27 10:23:36'),(50,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',102858,'192.168.152.1','2018-05-27 11:42:19'),(51,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',5496830,'192.168.152.1','2018-05-27 13:14:26'),(52,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',100,'0:0:0:0:0:0:0:1','2018-05-28 12:40:09'),(53,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',187,'0:0:0:0:0:0:0:1','2018-05-31 19:43:43'),(54,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',454942,'0:0:0:0:0:0:0:1','2018-05-31 19:53:32'),(55,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',220,'0:0:0:0:0:0:0:1','2018-06-04 13:01:37'),(56,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',4208,'0:0:0:0:0:0:0:1','2018-06-04 13:43:18'),(57,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',139,'0:0:0:0:0:0:0:1','2018-06-04 13:54:55'),(58,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',200,'0:0:0:0:0:0:0:1','2018-06-05 08:42:41'),(59,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',182,'0:0:0:0:0:0:0:1','2018-06-05 13:10:56'),(60,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',139,'0:0:0:0:0:0:0:1','2018-06-05 13:16:21'),(61,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',5,'0:0:0:0:0:0:0:1','2018-06-05 13:17:07'),(62,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',186,'0:0:0:0:0:0:0:1','2018-06-05 13:19:22'),(63,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',139,'0:0:0:0:0:0:0:1','2018-06-05 13:33:09'),(64,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',146,'0:0:0:0:0:0:0:1','2018-06-05 18:47:17'),(65,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',79,'0:0:0:0:0:0:0:1','2018-06-05 18:59:03'),(66,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',65,'0:0:0:0:0:0:0:1','2018-06-05 19:24:08'),(67,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',70,'0:0:0:0:0:0:0:1','2018-06-05 19:24:56'),(68,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',87,'0:0:0:0:0:0:0:1','2018-06-05 19:39:55'),(69,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',71,'0:0:0:0:0:0:0:1','2018-06-05 19:43:30'),(70,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',71,'0:0:0:0:0:0:0:1','2018-06-05 19:51:27'),(71,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',196,'0:0:0:0:0:0:0:1','2018-06-05 22:29:07'),(72,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',161,'0:0:0:0:0:0:0:1','2018-06-05 22:32:27'),(73,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',4,'0:0:0:0:0:0:0:1','2018-06-05 22:36:33'),(74,'admin','分页查询配置信息','com.jt.sys.service.impl.SysConfigServiceImpl.findPageObjects','[null,1]',33,'0:0:0:0:0:0:0:1','2018-07-11 19:00:43'),(75,'admin','分页查询配置信息','com.jt.sys.service.impl.SysConfigServiceImpl.findPageObjects','[null,1]',10,'0:0:0:0:0:0:0:1','2018-07-11 19:00:52'),(76,'admin','分页查询配置信息','com.jt.sys.service.impl.SysConfigServiceImpl.findPageObjects','[null,1]',183,'0:0:0:0:0:0:0:1','2018-08-15 18:38:22'),(77,'admin',NULL,'com.jt.sys.service.impl.SysRoleServiceImpl.findPageObjects','[null, 1]',147,'0:0:0:0:0:0:0:1',NULL),(78,'admin',NULL,'com.jt.sys.service.impl.SysRoleServiceImpl.findPageObjects','[null, 1]',5,'0:0:0:0:0:0:0:1',NULL),(79,'admin',NULL,'com.jt.sys.service.impl.SysRoleServiceImpl.findPageObjects','[null, 1]',7,'0:0:0:0:0:0:0:1',NULL),(80,'admin',NULL,'com.jt.sys.service.impl.SysRoleServiceImpl.findPageObjects','[null, 1]',6,'0:0:0:0:0:0:0:1',NULL);

UNLOCK TABLES;

--
-- Table structure for table `menu_table`
--

DROP TABLE IF EXISTS menu_table;

CREATE TABLE menu_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL COMMENT '资源名称',
  url varchar(200) DEFAULT NULL COMMENT '资源URL',
  type int(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  sort int(11) DEFAULT NULL COMMENT '排序',
  note varchar(100) DEFAULT NULL COMMENT '备注',
  parentId int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  permission varchar(500) DEFAULT NULL COMMENT '授权(如：user:create)',
  createdTime datetime DEFAULT NULL,
  modifiedTime datetime DEFAULT NULL,
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `menu_table`
--

LOCK TABLES menu_table WRITE;

INSERT INTO menu_table VALUES (8,'系统管理','请求路径',1,8,NULL,NULL,'sys:list','2017-07-12 15:15:59','2017-07-21 11:16:00','admin','admin'),(24,'配置管理','config/doFindPageObjects.do',1,24,NULL,8,'sys:config:view','2017-07-12 15:15:59','2018-10-29 12:15:19','admin',NULL),(25,'日志管理','log/doFindPageObject.do',1,25,NULL,8,'sys:log:view','2017-07-12 15:15:59','2018-10-29 12:15:48','admin',NULL),(45,'用户管理','user/doUserListUI.do',1,45,NULL,8,'sys:user:view','2017-07-12 15:15:59','2018-10-29 12:16:41','admin',NULL),(46,'菜单管理','menu/doMenuListUI.do',1,46,NULL,8,'sys:menu:view','2017-07-12 15:15:59','2018-10-29 12:17:01','admin',NULL),(47,'角色管理','role/doRoleListUI.do',1,47,NULL,8,'sys:role:view','2017-07-12 15:15:59','2018-10-29 12:17:46','admin',NULL),(48,'组织管理','dept/doFindPageObjects.do',1,48,NULL,8,'sys:org:view','2017-07-12 15:15:59','2018-10-29 12:16:20','admin',NULL),(115,'查看','',2,1,NULL,46,'sys:menu:view','2017-07-13 16:33:41','2017-07-21 11:09:05',NULL,NULL),(116,'新增','',2,2,NULL,46,'sys:menu:add','2017-07-13 16:34:02','2017-07-21 11:09:22',NULL,NULL),(117,'修改','',2,3,NULL,46,'sys:menu:update','2017-07-13 16:34:25','2017-07-21 11:09:45',NULL,NULL),(118,'删除','',2,4,NULL,46,'sys:menu:delete','2017-07-13 16:34:46','2017-07-21 11:10:12',NULL,NULL),(119,'查看','',2,1,NULL,45,'sys:user:view','2017-07-13 16:35:05','2017-07-21 11:12:46',NULL,NULL),(120,'查看','',2,1,NULL,47,'sys:role:view','2017-07-13 16:35:26','2017-07-21 11:13:43',NULL,NULL),(126,'新增','',2,2,NULL,45,'sys:user:add','2017-07-21 11:11:34','2017-07-21 11:11:34',NULL,NULL),(127,'修改','',2,3,NULL,45,'sys:user:update','2017-07-21 11:11:56','2017-07-21 11:11:56',NULL,NULL),(128,'新增','',2,2,NULL,47,'sys:role:add','2017-07-21 11:14:24','2017-07-21 11:14:24',NULL,NULL),(129,'修改','',2,3,NULL,47,'sys:role:update','2017-07-21 11:14:48','2017-07-21 11:14:48',NULL,NULL),(130,'删除','',2,4,NULL,47,'sys:role:delete','2017-07-21 11:15:09','2017-07-21 11:15:09',NULL,NULL),(131,'禁用启用','user/doValidById.do',1,111111,NULL,45,'sys:user:valid','2018-07-18 19:24:48','2018-07-18 19:24:48',NULL,NULL),(132,'日志删除','log/doDeleteObject.do',2,10,NULL,25,'sys:log:delete','2018-10-29 12:18:42','2018-10-29 12:18:42',NULL,NULL),(133,'添加配置','config/doSaveObject.do',1,2,NULL,24,'sys:config:save','2018-10-29 12:20:07','2018-10-29 12:20:07',NULL,NULL),(134,'添加配置','config/doSaveObject.do',1,2,NULL,24,'sys:config:save','2018-10-29 12:20:08','2018-10-29 12:20:08',NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS order_table;

CREATE TABLE order_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_name varchar(50) DEFAULT NULL,
  type varchar(50) DEFAULT NULL,
  trade_name varchar(50) DEFAULT NULL,
  add_time datetime DEFAULT NULL,
  purchase_quantity int(11) DEFAULT NULL,
  pay_time datetime DEFAULT NULL,
  pay_type varchar(50) DEFAULT NULL,
  price double(8,2) DEFAULT NULL,
  distribution_loading varchar(50) DEFAULT NULL,
  order_status varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY index_order (id)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `order_table`
--

LOCK TABLES order_table WRITE;

INSERT INTO order_table VALUES (1,'张三','手机','vivoX10','2018-04-26 14:49:07',2,'2018-04-26 14:50:38','微支付',600.00,'寒石快递','正在配送'),(2,'李四','手机','oppoR15','2018-07-23 15:23:32',5,'2018-07-24 11:40:45','微支付',2500.00,'寒石快递','正在配送'),(3,'王五','手机','三星MAX','2018-02-10 06:15:45',1,'2018-03-11 06:17:20','微支付',21351.00,'寒石快递','正在配送'),(4,'赵六','手机','iphone20','2018-05-12 12:42:14',9,'2018-05-12 13:32:04','微支付',100000.00,'寒石快递','正在配送'),(5,'阿萨德','手机','vivoX10','2018-04-26 14:49:07',2,'2018-04-26 14:50:38','微支付',600.00,'寒石快递','正在配送'),(6,'撒大声地','手机','oppoR15','2018-07-23 15:23:32',5,'2018-07-24 11:40:45','微支付',2500.00,'寒石快递','正在配送'),(7,'钢铁侠','手机','三星MAX','2018-02-10 06:15:45',1,'2018-03-11 06:17:20','微支付',21351.00,'寒石快递','正在配送'),(8,'蜘蛛侠','手机','iphone20','2018-05-12 12:42:14',9,'2018-05-12 13:32:04','微支付',100000.00,'寒石快递','正在配送'),(9,'蝙蝠侠','手机','vivoX10','2018-04-26 14:49:07',2,'2018-04-26 14:50:38','微支付',600.00,'寒石快递','正在配送'),(10,'纲手','手机','oppoR15','2018-07-23 15:23:32',5,'2018-07-24 11:40:45','微支付',2500.00,'寒石快递','正在配送'),(11,'自来也','手机','三星MAX','2018-02-10 06:15:45',1,'2018-03-11 06:17:20','微支付',21351.00,'寒石快递','正在配送'),(12,'大蛇丸','手机','iphone20','2018-05-12 12:42:14',9,'2018-05-12 13:32:04','微支付',100000.00,'寒石快递','正在配送'),(13,'金刚','手机','vivoX10','2018-04-26 14:49:07',2,'2018-04-26 14:50:38','微支付',600.00,'寒石快递','正在配送'),(14,'骷髅巨蜥','手机','oppoR15','2018-07-23 15:23:32',5,'2018-07-24 11:40:45','微支付',2500.00,'寒石快递','正在配送'),(15,'哥斯拉','手机','三星MAX','2018-02-10 06:15:45',1,'2018-03-11 06:17:20','微支付',21351.00,'寒石快递','正在配送'),(16,'木乃伊','手机','iphone20','2018-05-12 12:42:14',9,'2018-05-12 13:32:04','微支付',100000.00,'寒石快递','正在配送'),(17,'admin','小米22','121','2019-04-23 19:39:25',3,'2019-04-23 19:39:25','微支付',1211.00,'寒石快递','正在配送'),(18,'admin','小米','小米9','2019-04-23 19:39:25',1,'2019-04-23 19:39:25','微支付',3299.00,'寒石快递','正在配送'),(19,'admin','小米','小米9','2019-04-23 19:43:17',1,'2019-04-23 19:43:17','微支付',3211.00,'寒石快递','正在配送');

UNLOCK TABLES;

--
-- Table structure for table `product_table`
--

DROP TABLE IF EXISTS product_table;

CREATE TABLE product_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  brand varchar(50) DEFAULT NULL,
  trade_name varchar(100) DEFAULT NULL,
  message varchar(100) DEFAULT NULL,
  price double DEFAULT NULL,
  color varchar(50) DEFAULT NULL,
  status int(1) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `product_table`
--

LOCK TABLES product_table WRITE;

INSERT INTO product_table VALUES (1,'小米22','121','12',1211,'黑',1),(2,'小米','小米9','骁龙855 游戏手机 幻彩蓝 全网通8GB+128GB',3211,'深空灰',NULL),(3,'小米','小米9','骁龙855 游戏手机 幻彩蓝 全网通8GB+128GB',3299,'幻彩紫',1),(4,'小米','小米MIX2S','全面屏游戏拍照手机 6GB+128GB 黑色 骁龙845处理器 全网通4G 陶瓷手机',2699,'黑',1),(5,'小米','小米MIX2S','全面屏游戏拍照手机 6GB+128GB 黑色 骁龙845处理器 全网通4G 陶瓷手机',2699,'白',1),(7,'华为','华为HUAWEIP30','超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机8GB+64GB珠光贝母全网通双4G手机',3988,'天空之境',1),(8,'华为','华为HUAWEIP30','超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机8GB+64GB珠光贝母全网通双4G手机',3988,'亮黑色',1),(9,'华为','华为HUAWEIP30','超感光徕卡三摄麒麟980AI智能芯片全面屏屏内指纹版手机8GB+64GB珠光贝母全网通双4G手机',3988,'极光色',1),(10,'华为','华为V20','胡歌同款 麒麟980芯片 魅眼全视屏 4800万深感相机 6GB+128GB 幻夜黑 移动联通电信4G全面屏手机',2798,'幻夜黑',1),(11,'华为','华为V20','胡歌同款 麒麟980芯片 魅眼全视屏 4800万深感相机 6GB+128GB 幻夜黑 移动联通电信4G全面屏手机',2798,'幻夜红',1),(12,'Apple','Apple iphone X','(A1865) 64GB 深空灰色 移动联通电信4G手机6199',6199,'银色',1),(13,'Apple','Apple iphone X','(A1865) 64GB 深空灰色 移动联通电信4G手机6199',6199,'深空灰色',1),(14,'Apple','Apple iphone XS Max','(A2104) 256GB 金色 移动联通电信4G手机 双卡双待',9699,'深空灰色',1),(15,'Apple','Apple iphone XS Max','(A2104) 256GB 金色 移动联通电信4G手机 双卡双待',9699,'金色',1),(16,'OPPO','OPPO Reno','全面屏拍照手机 6G+256G 雾海绿 全网通 移动联通电信 双卡双待手机',3299,'雾海绿',1),(17,'OPPO','OPPO Reno','全面屏拍照手机 6G+256G 雾海绿 全网通 移动联通电信 双卡双待手机',3299,'极夜黑',1),(18,'OPPO','OPPO R17','2500万美颜拍照 6.4英寸水滴屏 光感屏幕指纹 8G+128G',2999,'雾光金',1),(19,'OPPO','OPPO R17','2500万美颜拍照 6.4英寸水滴屏 光感屏幕指纹 8G+128G',2999,'激光蓝',1),(20,'OPPO','OPPO R17','2500万美颜拍照 6.4英寸水滴屏 光感屏幕指纹 8G+128G',2999,'雾光紫',1),(21,'VIVO','vivo X27','4800万广角夜景三摄 零界全面屏拍照手机',3198,'雀羽蓝',1),(22,'VIVO','vivo X27','4800万广角夜景三摄 零界全面屏拍照手机',3198,'翡翠色',1),(24,'VIVO','vivo NEX','AI三摄 游戏手机 10GB+128GB 冰原蓝',4998,'星様紫',1),(67,'67','89','34',45,'56',1),(121,'2','12','11',1111,'21',1);

UNLOCK TABLES;

--
-- Table structure for table `role_menus_table`
--

DROP TABLE IF EXISTS role_menus_table;

CREATE TABLE role_menus_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_id int(11) DEFAULT NULL COMMENT '角色ID',
  menu_id int(11) DEFAULT NULL COMMENT 'ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1391 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

--
-- Dumping data for table `role_menus_table`
--

LOCK TABLES role_menus_table WRITE;
INSERT INTO role_menus_table VALUES (1232,1,8),(1233,1,24),(1234,1,25),(1235,1,45),(1236,1,119),(1237,1,126),(1238,1,127),(1239,1,46),(1240,1,115),(1241,1,116),(1242,1,117),(1243,1,118),(1244,1,47),(1245,1,120),(1246,1,128),(1247,1,129),(1248,1,130),(1249,1,48),(1352,47,8),(1353,47,24),(1354,47,133),(1355,47,134),(1356,47,25),(1357,47,132),(1358,47,45),(1359,47,119),(1360,47,126),(1361,47,127),(1362,47,131),(1363,47,46),(1364,47,115),(1365,47,116),(1366,47,117),(1367,47,118),(1368,47,47),(1369,47,120),(1370,47,128),(1371,47,129),(1372,47,130),(1373,47,48),(1374,46,8),(1375,46,24),(1376,46,45),(1377,46,119),(1378,46,126),(1379,46,127),(1380,46,46),(1381,46,115),(1382,46,116),(1383,46,117),(1384,46,118),(1385,46,47),(1386,46,120),(1387,46,128),(1388,46,129),(1389,46,130),(1390,46,48);

UNLOCK TABLES;

--
-- Table structure for table `role_table`
--

DROP TABLE IF EXISTS role_table;

CREATE TABLE role_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL COMMENT '角色名称',
  note varchar(500) DEFAULT NULL COMMENT '备注',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id),
  UNIQUE KEY index_role (id)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `role_table`
--

LOCK TABLES role_table WRITE;

INSERT INTO role_table VALUES (23,'卖家','客户','2018-09-12 17:23:11','2018-11-15 09:14:55',NULL,NULL),(46,'管理员','管理者','2018-07-06 19:25:36','2018-11-13 08:25:29',NULL,NULL),(47,'买家','客户','2018-07-18 19:23:11','2018-11-13 08:24:25',NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `sys_configs`
--

DROP TABLE IF EXISTS sys_configs;

CREATE TABLE sys_configs (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL COMMENT '参数名',
  value varchar(50) DEFAULT NULL COMMENT '参数值',
  note varchar(100) DEFAULT NULL COMMENT '备注',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='配置管理';


--
-- Dumping data for table `sys_configs`
--

LOCK TABLES sys_configs WRITE;
INSERT INTO sys_configs VALUES (4,'uploadPath','/upload/images','上传路径','2018-04-22 17:39:55','2018-04-22 17:39:55',NULL,NULL),(5,'downloadPath','/downloads/','文件下载路径','2018-04-22 17:40:41','2018-04-22 17:40:41',NULL,NULL),(6,'555','100','aaa','2018-05-28 12:40:27','2018-05-28 12:40:27',NULL,NULL),(7,'p1','v1','wqe','2018-06-05 08:44:17','2018-06-05 08:44:17',NULL,NULL),(8,'p2','v2','ppp','2018-06-05 13:07:52','2018-06-05 13:07:52',NULL,NULL),(9,'aaaaaaaa1','aa','aa','2018-06-05 19:01:56','2018-06-05 19:01:56',NULL,NULL),(10,'d','spring-boot-02-quickstart','sd','2018-06-05 19:02:50','2018-06-05 19:02:50',NULL,NULL),(11,'ddd','sdfasdf','sadf','2018-06-05 19:40:03','2018-06-05 19:40:03',NULL,NULL),(12,'数据库端口','3306','数据库监听端口','2018-06-05 19:43:37','2018-10-29 12:09:36',NULL,NULL),(13,'端口','8080','服务器端口','2018-06-05 22:36:47','2018-10-29 12:09:06',NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `sys_depts`
--

DROP TABLE IF EXISTS sys_depts;

CREATE TABLE sys_depts (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL COMMENT '资源名称',
  parentId int(11) DEFAULT NULL COMMENT '上级部门',
  sort int(11) DEFAULT NULL COMMENT '排序',
  note varchar(100) DEFAULT NULL COMMENT '备注',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='部门管理';


--
-- Dumping data for table sys_depts
--

LOCK TABLES sys_depts WRITE;

INSERT INTO sys_depts VALUES (2,'产品部',NULL,1,'负责产品设计及研发','2018-04-19 18:59:09','2018-10-29 12:21:55','admin',NULL),(3,'计调部',NULL,2,'负责计划安排','2018-04-19 19:15:05','2018-10-29 12:22:52',NULL,NULL),(5,'A部',2,1,'111111','2018-07-08 23:42:01','2019-04-17 16:03:20',NULL,NULL),(6,'工程部',3,1,'工厂','2019-04-16 18:38:10','2019-04-16 18:38:10',NULL,NULL),(7,'食品部',2,2,'12','2019-04-16 18:38:41','2019-04-16 18:38:41',NULL,NULL),(8,'嘻嘻部',6,1,'121','2019-04-17 15:56:02','2019-04-17 15:56:02',NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `sys_dicts`
--

DROP TABLE IF EXISTS sys_dicts;

CREATE TABLE sys_dicts (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL COMMENT '名字',
  type varchar(20) DEFAULT NULL COMMENT '类型',
  code varchar(20) DEFAULT NULL COMMENT '字典码',
  value varchar(100) DEFAULT NULL COMMENT '字典值',
  sort int(11) DEFAULT NULL COMMENT '排序',
  valid tinyint(4) DEFAULT '1' COMMENT '有效',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典管理';


--
-- Dumping data for table `sys_dicts`
--

LOCK TABLES sys_dicts WRITE;

UNLOCK TABLES;

--
-- Table structure for table sys_logs
--

DROP TABLE IF EXISTS sys_logs;

CREATE TABLE sys_logs (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(50) DEFAULT NULL COMMENT '用户名',
  operation varchar(50) DEFAULT NULL COMMENT '用户操作',
  method varchar(200) DEFAULT NULL COMMENT '请求方法',
  params varchar(5000) DEFAULT NULL COMMENT '请求参数',
  time bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  ip varchar(64) DEFAULT NULL COMMENT 'IP地址',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 COMMENT='系统日志';


--
-- Dumping data for table `sys_logs`
--

LOCK TABLES sys_logs WRITE;

INSERT INTO sys_logs VALUES (37,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',6,'192.168.43.183','2018-04-22 21:34:40'),(38,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',4,'192.168.43.211','2018-04-22 22:10:04'),(39,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',71,'0:0:0:0:0:0:0:1','2018-04-23 08:47:21'),(40,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',4,'0:0:0:0:0:0:0:1','2018-04-23 18:22:51'),(41,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',133,'0:0:0:0:0:0:0:1','2018-04-28 17:51:31'),(42,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',185,'0:0:0:0:0:0:0:1','2018-05-15 23:22:01'),(43,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',114,'0:0:0:0:0:0:0:1','2018-05-16 09:55:16'),(44,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',184,'0:0:0:0:0:0:0:1','2018-05-20 08:05:15'),(45,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',254,'172.20.10.1','2018-05-27 10:22:56'),(46,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin \"',8,'172.20.10.9','2018-05-27 10:22:58'),(47,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',9,'172.20.10.11','2018-05-27 10:23:18'),(48,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',5,'172.20.10.14','2018-05-27 10:23:25'),(49,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin \"',5,'172.20.10.9','2018-05-27 10:23:36'),(50,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',102858,'192.168.152.1','2018-05-27 11:42:19'),(51,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',5496830,'192.168.152.1','2018-05-27 13:14:26'),(52,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',100,'0:0:0:0:0:0:0:1','2018-05-28 12:40:09'),(53,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',187,'0:0:0:0:0:0:0:1','2018-05-31 19:43:43'),(54,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',454942,'0:0:0:0:0:0:0:1','2018-05-31 19:53:32'),(55,'admin','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',220,'0:0:0:0:0:0:0:1','2018-06-04 13:01:37'),(59,'里斯','登陆操作','com.jt.sys.service.impl.SysUserServiceImpl.login()','\"admin\"',182,'0:0:0:0:0:0:0:1','2018-06-05 13:10:56'),(60,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,1]',38,'192.168.1.1','2019-04-17 15:53:38'),(61,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',3,'192.168.1.1','2019-04-17 15:53:43'),(62,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',4,'192.168.1.1','2019-04-17 15:53:44'),(63,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,8]',3,'192.168.1.1','2019-04-17 15:53:45'),(64,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,8]',4,'192.168.1.1','2019-04-17 15:53:46'),(65,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,8]',5,'192.168.1.1','2019-04-17 15:53:47'),(66,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,9]',3,'192.168.1.1','2019-04-17 15:53:48'),(67,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,9]',3,'192.168.1.1','2019-04-17 15:53:49'),(68,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,9]',3,'192.168.1.1','2019-04-17 15:53:51'),(69,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,10]',3,'192.168.1.1','2019-04-17 15:53:52'),(70,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,10]',3,'192.168.1.1','2019-04-17 15:53:54'),(71,'jinyong','查看日志','com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,10]',3,'192.168.1.1','2019-04-17 15:53:56'),(84,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,4]',3,'192.168.1.1','2019-04-17 15:56:51'),(85,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,5]',5,'192.168.1.1','2019-04-17 15:56:52'),(87,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,3]',3,'192.168.1.1','2019-04-17 15:56:53'),(88,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,2]',2,'192.168.1.1','2019-04-17 15:56:53'),(99,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,6]',2,'192.168.1.1','2019-04-17 15:57:35'),(101,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,8]',3,'192.168.1.1','2019-04-17 15:57:36'),(102,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,9]',3,'192.168.1.1','2019-04-17 15:57:37'),(103,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,10]',3,'192.168.1.1','2019-04-17 15:57:38'),(110,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,1]',4,'192.168.1.1','2019-04-17 15:58:42'),(111,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[109,108,104]]',3,'192.168.1.1','2019-04-17 15:58:46'),(112,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,1]',4,'192.168.1.1','2019-04-17 15:58:46'),(113,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,2]',4,'192.168.1.1','2019-04-17 15:58:49'),(114,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,3]',3,'192.168.1.1','2019-04-17 15:58:50'),(115,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,4]',6,'192.168.1.1','2019-04-17 15:58:50'),(116,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,5]',3,'192.168.1.1','2019-04-17 15:58:51'),(117,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,6]',4,'192.168.1.1','2019-04-17 15:58:51'),(118,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',3,'192.168.1.1','2019-04-17 15:58:51'),(119,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[93,92,91]]',3,'192.168.1.1','2019-04-17 15:58:55'),(120,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',4,'192.168.1.1','2019-04-17 15:58:56'),(121,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[95,94,90]]',2,'192.168.1.1','2019-04-17 15:59:00'),(122,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',3,'192.168.1.1','2019-04-17 15:59:01'),(123,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[98,96,89]]',3,'192.168.1.1','2019-04-17 15:59:06'),(124,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',4,'192.168.1.1','2019-04-17 15:59:07'),(125,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[100,97,86]]',2,'192.168.1.1','2019-04-17 15:59:11'),(126,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',4,'192.168.1.1','2019-04-17 15:59:12'),(127,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,8]',3,'192.168.1.1','2019-04-17 15:59:14'),(128,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,9]',3,'192.168.1.1','2019-04-17 15:59:14'),(129,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,10]',5,'192.168.1.1','2019-04-17 15:59:15'),(130,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,11]',3,'192.168.1.1','2019-04-17 15:59:15'),(131,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,12]',2,'192.168.1.1','2019-04-17 15:59:15'),(132,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,13]',4,'192.168.1.1','2019-04-17 15:59:16'),(133,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,14]',3,'192.168.1.1','2019-04-17 15:59:17'),(134,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,15]',3,'192.168.1.1','2019-04-17 15:59:18'),(135,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,16]',4,'192.168.1.1','2019-04-17 15:59:19'),(136,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,17]',4,'192.168.1.1','2019-04-17 15:59:19'),(137,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,18]',3,'192.168.1.1','2019-04-17 15:59:19'),(138,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,19]',3,'192.168.1.1','2019-04-17 15:59:19'),(139,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,20]',2,'192.168.1.1','2019-04-17 15:59:20'),(140,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,21]',3,'192.168.1.1','2019-04-17 15:59:20'),(141,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',3,'192.168.1.1','2019-04-17 15:59:20'),(142,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,23]',3,'192.168.1.1','2019-04-17 15:59:21'),(143,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,24]',3,'192.168.1.1','2019-04-17 15:59:21'),(144,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,25]',3,'192.168.1.1','2019-04-17 15:59:22'),(145,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,26]',3,'192.168.1.1','2019-04-17 15:59:22'),(146,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,27]',3,'192.168.1.1','2019-04-17 15:59:22'),(147,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,28]',3,'192.168.1.1','2019-04-17 15:59:23'),(148,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,29]',3,'192.168.1.1','2019-04-17 15:59:23'),(149,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,30]',2,'192.168.1.1','2019-04-17 15:59:23'),(150,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,31]',2,'192.168.1.1','2019-04-17 15:59:23'),(151,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,30]',3,'192.168.1.1','2019-04-17 15:59:24'),(152,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,29]',4,'192.168.1.1','2019-04-17 15:59:26'),(153,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,28]',4,'192.168.1.1','2019-04-17 15:59:26'),(154,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,27]',3,'192.168.1.1','2019-04-17 15:59:26'),(155,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,26]',1,'192.168.1.1','2019-04-17 15:59:26'),(156,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,25]',3,'192.168.1.1','2019-04-17 15:59:26'),(157,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,24]',2,'192.168.1.1','2019-04-17 15:59:27'),(158,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,23]',3,'192.168.1.1','2019-04-17 15:59:27'),(159,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',2,'192.168.1.1','2019-04-17 15:59:27'),(160,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[77,76,75]]',3,'192.168.1.1','2019-04-17 15:59:32'),(161,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',4,'192.168.1.1','2019-04-17 15:59:33'),(162,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[79,78,74]]',2,'192.168.1.1','2019-04-17 15:59:39'),(163,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',3,'192.168.1.1','2019-04-17 15:59:40'),(164,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[81,80,73]]',3,'192.168.1.1','2019-04-17 15:59:45'),(165,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',3,'192.168.1.1','2019-04-17 15:59:45'),(166,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[83,82,72]]',4,'192.168.1.1','2019-04-17 15:59:51'),(167,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',4,'192.168.1.1','2019-04-17 15:59:52'),(168,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,23]',3,'192.168.1.1','2019-04-17 15:59:55'),(169,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,22]',3,'192.168.1.1','2019-04-17 15:59:57'),(170,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,21]',3,'192.168.1.1','2019-04-17 15:59:57'),(171,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,20]',3,'192.168.1.1','2019-04-17 15:59:58'),(172,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,19]',3,'192.168.1.1','2019-04-17 15:59:58'),(173,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,18]',2,'192.168.1.1','2019-04-17 15:59:58'),(174,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,17]',2,'192.168.1.1','2019-04-17 15:59:59'),(175,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,16]',2,'192.168.1.1','2019-04-17 15:59:59'),(176,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,15]',4,'192.168.1.1','2019-04-17 15:59:59'),(177,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,14]',3,'192.168.1.1','2019-04-17 15:59:59'),(178,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,13]',2,'192.168.1.1','2019-04-17 15:59:59'),(179,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,12]',3,'192.168.1.1','2019-04-17 16:00:00'),(180,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,11]',3,'192.168.1.1','2019-04-17 16:00:00'),(181,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,10]',2,'192.168.1.1','2019-04-17 16:00:00'),(182,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,9]',2,'192.168.1.1','2019-04-17 16:00:00'),(183,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,8]',2,'192.168.1.1','2019-04-17 16:00:00'),(184,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,7]',2,'192.168.1.1','2019-04-17 16:00:01'),(185,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,6]',3,'192.168.1.1','2019-04-17 16:00:01'),(186,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,5]',2,'192.168.1.1','2019-04-17 16:00:01'),(187,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,4]',3,'192.168.1.1','2019-04-17 16:00:01'),(188,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,3]',3,'192.168.1.1','2019-04-17 16:00:01'),(189,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,2]',2,'192.168.1.1','2019-04-17 16:00:01'),(190,'jinyong',NULL,'com.db.sys.service.impl.SysLogServiceImpl.findPageObjects','[null,1]',3,'192.168.1.1','2019-04-17 16:00:02'),(194,'jinyong','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[192,193,191]]',3,'192.168.1.1','2019-04-17 16:02:54'),(195,'jinyong','修改部门','com.db.sys.service.impl.SysDeptServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":5,\"name\":\"A部\",\"parentId\":2,\"sort\":1,\"note\":\"111111\"}]',3,'192.168.1.1','2019-04-17 16:03:20'),(196,'jinyong','修改菜单','com.db.sys.service.impl.SysMenuServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":131,\"name\":\"禁用启用\",\"url\":\"user/doValidById.do\",\"type\":2,\"sort\":11,\"note\":null,\"parentId\":45,\"permission\":\"sys:user:valid\"}]',2,'192.168.1.1','2019-04-17 16:03:54'),(197,'jinyong','添加角色','com.db.sys.service.impl.SysRoleServiceImpl.saveObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":9,\"name\":\"大王\",\"note\":\"负责巡山\"},[8,24,133,134,45,119,127,131,46,115,116,117,118,47,128,130]]',8,'192.168.1.1','2019-04-17 16:04:21'),(198,'jinyong','添加用户','com.db.sys.service.impl.SysUserServiceImpl.saveObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":30,\"username\":\"dawang\",\"password\":\"eb0d942eb83f5190d917b3f0a282854c\",\"salt\":\"f1e932a3-3ada-45a6-9921-283a79fc7d90\",\"email\":\"1@2\",\"mobile\":\"123\",\"valid\":1,\"deptId\":2},[9]]',9,'192.168.1.1','2019-04-17 16:04:48'),(199,'jinyong','修改密码','com.db.sys.service.impl.SysUserServiceImpl.updatePassword','[\"123456\",\"123\",\"123\"]',9,'192.168.1.1','2019-04-17 16:05:03'),(200,'jinyong','禁/启用户','com.db.sys.service.impl.SysUserServiceImpl.validById','[29,0,\"jinyong\"]',11,'192.168.0.181','2019-04-17 16:54:27'),(204,'admin','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[202]]',11,'0:0:0:0:0:0:0:1','2019-04-21 20:23:09'),(205,'admin','删除日志','com.db.sys.service.impl.SysLogServiceImpl.deleteObjects','[[203,201]]',4,'0:0:0:0:0:0:0:1','2019-04-21 20:24:15'),(206,'admin','修改角色','com.db.sys.service.impl.SysRoleServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":9,\"name\":\"大王\",\"note\":\"负责巡山\"},[8,24,133,134,45,119,127,131,46,115,116,117,118,47,128,130]]',15,'0:0:0:0:0:0:0:1','2019-04-23 09:35:14'),(207,'admin','修改用户','com.db.sys.service.impl.SysUserServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":1,\"username\":\"admin\",\"password\":null,\"salt\":null,\"email\":\"admin@t.cn\",\"mobile\":\"13624356789\",\"valid\":1,\"deptId\":3},[4,7,8,9]]',6,'0:0:0:0:0:0:0:1','2019-04-23 09:35:43'),(208,'admin','修改角色','com.db.sys.service.impl.SysRoleServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":9,\"name\":\"大王\",\"note\":\"负责巡山\"},[8,24,133,134,45,119,127,131,46,115,116,117,118,47,128,130]]',6,'0:0:0:0:0:0:0:1','2019-04-23 09:35:57'),(209,'admin','添加菜单','com.db.sys.service.impl.SysMenuServiceImpl.saveObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":null,\"name\":\"部门管理\",\"url\":\"dept/doDeptListUI\",\"type\":1,\"sort\":6,\"note\":null,\"parentId\":8,\"permission\":\"dept:menus:add\"}]',2,'0:0:0:0:0:0:0:1','2019-04-23 09:37:08'),(210,'admin','添加菜单','com.db.sys.service.impl.SysMenuServiceImpl.saveObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":null,\"name\":\"日志管理\",\"url\":\"log/doLogList.do\",\"type\":1,\"sort\":8,\"note\":null,\"parentId\":8,\"permission\":\"log:menus:add\"}]',2,'0:0:0:0:0:0:0:1','2019-04-23 09:38:06'),(211,'admin','修改角色','com.db.sys.service.impl.SysRoleServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":9,\"name\":\"大王\",\"note\":\"负责巡山\"},[8,24,133,134,45,119,127,131,46,115,116,117,118,47,128,130,135,136]]',5,'0:0:0:0:0:0:0:1','2019-04-23 09:38:33'),(212,'admin','修改用户','com.db.sys.service.impl.SysUserServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":28,\"username\":\"jinyong\",\"password\":null,\"salt\":null,\"email\":\"123@qq.com\",\"mobile\":\"12121212121\",\"valid\":1,\"deptId\":3},[7]]',5,'0:0:0:0:0:0:0:1','2019-04-23 09:38:56'),(213,'admin','修改用户','com.db.sys.service.impl.SysUserServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":28,\"username\":\"jinyong\",\"password\":null,\"salt\":null,\"email\":\"123@qq.com\",\"mobile\":\"12121212121\",\"valid\":1,\"deptId\":3},[7,8]]',6,'0:0:0:0:0:0:0:1','2019-04-23 09:39:03'),(214,'admin','修改角色','com.db.sys.service.impl.SysRoleServiceImpl.updateObject','[{\"createdUser\":null,\"modifiedUser\":null,\"createdTime\":null,\"modifiedTime\":null,\"id\":8,\"name\":\"工程师\",\"note\":\"写工程\"},[8,45,119,127,46,117,136]]',5,'0:0:0:0:0:0:0:1','2019-04-23 09:39:20'),(215,'admin','禁/启用户','com.db.sys.service.impl.SysUserServiceImpl.validById','[30,0,\"admin\"]',13,'0:0:0:0:0:0:0:1','2019-04-23 15:28:54'),(216,'admin','禁/启用户','com.db.sys.service.impl.SysUserServiceImpl.validById','[30,1,\"admin\"]',4,'0:0:0:0:0:0:0:1','2019-04-23 15:28:56');

UNLOCK TABLES;

--
-- Table structure for table `sys_menus`
--

DROP TABLE IF EXISTS sys_menus;

CREATE TABLE sys_menus (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL COMMENT '资源名称',
  url varchar(200) DEFAULT NULL COMMENT '资源URL',
  type int(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  sort int(11) DEFAULT NULL COMMENT '排序',
  note varchar(100) DEFAULT NULL COMMENT '备注',
  parentId int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  permission varchar(500) DEFAULT NULL COMMENT '授权(如：user:create)',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COMMENT='资源管理';


--
-- Dumping data for table `sys_menus`
--

LOCK TABLES sys_menus WRITE;

INSERT INTO sys_menus VALUES (8,'系统管理','请求路径',1,8,NULL,NULL,'sys:list','2017-07-12 15:15:59','2017-07-21 11:16:00','admin','admin'),(24,'配置管理','config/doFindPageObjects.do',1,24,NULL,8,'sys:config:view','2017-07-12 15:15:59','2018-10-29 12:15:19','admin',NULL),(45,'用户管理','user/doUserListUI.do',1,45,NULL,8,'sys:user:view','2017-07-12 15:15:59','2018-10-29 12:16:41','admin',NULL),(46,'菜单管理','menu/doMenuListUI.do',1,46,NULL,8,'sys:menu:view','2017-07-12 15:15:59','2018-10-29 12:17:01','admin',NULL),(47,'角色管理','role/doRoleListUI.do',1,47,NULL,8,'sys:role:view','2017-07-12 15:15:59','2018-10-29 12:17:46','admin',NULL),(115,'查看','',2,1,NULL,46,'sys:menu:view','2017-07-13 16:33:41','2017-07-21 11:09:05',NULL,NULL),(116,'新增','',2,2,NULL,46,'sys:menu:add','2017-07-13 16:34:02','2017-07-21 11:09:22',NULL,NULL),(117,'修改','',2,12,NULL,46,'sys:menu:update','2017-07-13 16:34:25','2019-04-10 10:54:56',NULL,NULL),(118,'删除','',2,4,NULL,46,'sys:menu:delete','2017-07-13 16:34:46','2017-07-21 11:10:12',NULL,NULL),(119,'查看','',2,1,NULL,45,'sys:user:view','2017-07-13 16:35:05','2017-07-21 11:12:46',NULL,NULL),(127,'修改','',2,3,NULL,45,'sys:user:update','2017-07-21 11:11:56','2017-07-21 11:11:56',NULL,NULL),(128,'新增','',2,2,NULL,47,'sys:role:add','2017-07-21 11:14:24','2017-07-21 11:14:24',NULL,NULL),(130,'删除','',2,41,NULL,47,'sys:role:delete','2017-07-21 11:15:09','2019-04-10 10:54:29',NULL,NULL),(131,'禁用启用','user/doValidById.do',2,11,NULL,45,'sys:user:valid','2018-07-18 19:24:48','2019-04-17 16:03:53',NULL,NULL),(133,'添加配置','config/doSaveObject.do',1,2,NULL,24,'sys:config:save','2018-10-29 12:20:07','2018-10-29 12:20:07',NULL,NULL),(134,'添加配置','config/doSaveObject.do',1,2,NULL,24,'sys:config:save','2018-10-29 12:20:08','2018-10-29 12:20:08',NULL,NULL),(135,'部门管理','dept/doDeptListUI',1,6,NULL,8,'dept:menus:add','2019-04-23 09:37:08','2019-04-23 09:37:08',NULL,NULL),(136,'日志管理','log/doLogList.do',1,8,NULL,8,'log:menus:add','2019-04-23 09:38:05','2019-04-23 09:38:05',NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `sys_role_menus`
--

DROP TABLE IF EXISTS sys_role_menus;

CREATE TABLE sys_role_menus (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_id int(11) DEFAULT NULL COMMENT '角色ID',
  menu_id int(11) DEFAULT NULL COMMENT 'ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1531 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';


--
-- Dumping data for table `sys_role_menus`
--

LOCK TABLES sys_role_menus WRITE;

INSERT INTO sys_role_menus VALUES (1232,1,8),(1233,1,24),(1235,1,45),(1236,1,119),(1238,1,127),(1239,1,46),(1240,1,115),(1241,1,116),(1242,1,117),(1243,1,118),(1244,1,47),(1246,1,128),(1248,1,130),(1352,47,8),(1353,47,24),(1354,47,133),(1355,47,134),(1358,47,45),(1359,47,119),(1361,47,127),(1362,47,131),(1363,47,46),(1364,47,115),(1365,47,116),(1366,47,117),(1367,47,118),(1368,47,47),(1370,47,128),(1372,47,130),(1374,46,8),(1375,46,24),(1376,46,45),(1377,46,119),(1379,46,127),(1380,46,46),(1381,46,115),(1382,46,116),(1383,46,117),(1384,46,118),(1385,46,47),(1387,46,128),(1389,46,130),(1390,1,8),(1391,1,45),(1392,1,119),(1393,1,46),(1394,1,116),(1395,2,8),(1396,2,45),(1397,2,119),(1398,2,46),(1399,2,116),(1423,3,8),(1424,3,24),(1425,3,133),(1426,4,8),(1427,4,46),(1428,4,115),(1429,4,116),(1430,5,8),(1431,5,46),(1432,5,116),(1433,5,117),(1434,6,8),(1435,6,24),(1436,6,133),(1437,6,134),(1438,6,45),(1439,6,119),(1441,6,127),(1442,6,131),(1443,6,46),(1444,6,115),(1445,6,116),(1446,6,117),(1447,6,118),(1448,6,47),(1449,6,128),(1450,6,130),(1451,7,8),(1452,7,45),(1453,7,131),(1506,9,8),(1507,9,24),(1508,9,133),(1509,9,134),(1510,9,45),(1511,9,119),(1512,9,127),(1513,9,131),(1514,9,46),(1515,9,115),(1516,9,116),(1517,9,117),(1518,9,118),(1519,9,47),(1520,9,128),(1521,9,130),(1522,9,135),(1523,9,136),(1524,8,8),(1525,8,45),(1526,8,119),(1527,8,127),(1528,8,46),(1529,8,117),(1530,8,136);

UNLOCK TABLES;

--
-- Table structure for table `sys_roles`
--

DROP TABLE IF EXISTS sys_roles;

CREATE TABLE sys_roles (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL COMMENT '角色名称',
  note varchar(500) DEFAULT NULL COMMENT '备注',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色';


--
-- Dumping data for table `sys_roles`
--

LOCK TABLES sys_roles WRITE;

INSERT INTO sys_roles VALUES (3,'软件时1','122','2019-04-11 09:52:12','2019-04-11 10:23:32',NULL,NULL),(4,'软件工程师','编写软件','2019-04-11 16:13:30','2019-04-11 16:13:30',NULL,NULL),(5,'程序员鼓励师','用身心给程序员工作的动力','2019-04-11 16:14:16','2019-04-11 16:14:16',NULL,NULL),(6,'总监','监视一切的发展','2019-04-11 16:14:45','2019-04-11 16:14:45',NULL,NULL),(7,'禁用测试','禁用测试','2019-04-15 14:56:21','2019-04-15 14:56:21',NULL,NULL),(8,'工程师','写工程','2019-04-17 15:57:17','2019-04-23 09:39:19',NULL,NULL),(9,'大王','负责巡山','2019-04-17 16:04:20','2019-04-23 09:38:33',NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `sys_user_roles`
--

DROP TABLE IF EXISTS sys_user_roles;

CREATE TABLE sys_user_roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) DEFAULT NULL COMMENT '用户ID',
  role_id int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';


--
-- Dumping data for table `sys_user_roles`
--

LOCK TABLES sys_user_roles WRITE;

INSERT INTO sys_user_roles VALUES (1,22,3),(3,24,4),(4,25,6),(5,23,4),(6,26,4),(7,27,4),(8,27,5),(12,29,6),(13,29,7),(14,30,9),(19,1,4),(20,1,7),(21,1,8),(22,1,9),(24,28,7),(25,28,8);

UNLOCK TABLES;

--
-- Table structure for table `sys_users`
--

DROP TABLE IF EXISTS sys_users;

CREATE TABLE sys_users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL COMMENT '用户名',
  password varchar(100) DEFAULT NULL COMMENT '密码',
  salt varchar(50) DEFAULT NULL COMMENT '盐  密码加密时前缀，使加密后的值不同',
  email varchar(100) DEFAULT NULL COMMENT '邮箱',
  mobile varchar(100) DEFAULT NULL COMMENT '手机号',
  valid tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常  默认值 ：1',
  deptId int(11) DEFAULT NULL,
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  createdUser varchar(20) DEFAULT NULL COMMENT '创建用户',
  modifiedUser varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (id),
  UNIQUE KEY username (username)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='系统用户';

--
-- Dumping data for table `sys_users`
--

LOCK TABLES sys_users WRITE;

INSERT INTO sys_users VALUES (1,'admin','267415cbb5a76b4408a4dff4b82d272f','420b4f05-ff62-4f2b-9a05-a35fb2ab39aa','admin@t.cn','13624356789',1,3,NULL,'2019-04-23 09:35:42',NULL,'admin'),(2,'zhangli','bdcf69375bdb532e50279b91eb00940d','5e7cbd36-e897-4951-b42b-19809caf3caa','zhangli@t.cn','13678909876',0,3,'2017-07-18 10:01:51','2018-04-22 20:49:19',NULL,'admin'),(3,'wangke','c5dc32ec66041aeddf432b3146bd2257','5e3e1475-1ea9-4a6a-976e-b07545827139','wangke@t.cn','18678900987',1,3,'2017-07-18 11:40:53','2018-11-13 08:24:45',NULL,NULL),(4,'zhangql','+HBpqtPuj9KLBIpneR5X0A==','ed487fac-9952-45c9-acaa-21dab9c689cc','zhangql@t.cn','13678909876',1,2,'2017-07-18 12:17:30','2018-07-18 19:27:14',NULL,'admin'),(5,'fanwei','1acab7425d6dfae670f26bd160518902','34fbedb2-e135-4f8d-b595-24360edc348d','fanwei@t.cn','13876545678',0,3,'2017-07-20 17:03:22','2018-07-18 19:27:07',NULL,'admin'),(6,'wumei','431ebdcccf3404787a144f9ba669a8e2','8a14f46f-7a17-4dfe-85ab-08e63cb618ce','wumei@t.cn','13567898765',1,2,'2017-07-21 10:57:40','2018-04-22 20:46:49',NULL,NULL),(7,'user-003','689c673a0d8bda7ee795dd45a126ae96','3faa1d2b-a99f-4ffb-9d29-0e71563258af','t@t.com','123',1,3,'2018-01-12 23:19:58','2018-04-22 20:46:07',NULL,'admin'),(9,'user-002','e10adc3949ba59abbe56e057f20f883e',NULL,'t@t.com','123',1,3,'2018-01-12 23:20:31','2018-04-22 20:45:55',NULL,NULL),(12,'user-001','5bf6593afd106aa544000d559f0c2241','9528e727-2901-4746-8558-9010d9607da2','t@t.com','123',1,3,'2018-01-13 01:48:32','2018-04-22 20:45:37',NULL,NULL),(13,'user-c','2630d8bd50c76abf001a9daceeae97e6','30fff766-e245-4a93-9f5e-6eb2c2cec494','t@t.com','123456',0,3,'2018-01-13 02:01:56','2018-04-22 20:43:58',NULL,'admin'),(15,'user-b','2ce586af95c6431112092f653659c85f','eaedbaee-d760-40e4-b71e-ccecf01b6187','t@t.com','123456',1,3,'2018-01-13 02:02:06','2018-04-22 20:54:10',NULL,'admin'),(16,'user-a','710058cf374a38d76510d009f63bf28d','e8e35b96-bbdd-4090-81ee-b71a36141760','1@t.com','121212',NULL,2,'2018-04-22 19:43:11','2018-04-22 20:54:02',NULL,NULL),(17,'aaaaaaa','573a6f32087ea8346c39d3b7c0166a41','f60ba9da-76ba-44ef-8a25-8d3a2ad2b53b','11','11111111',0,3,'2018-07-09 00:44:03','2019-04-11 14:23:07',NULL,'admin'),(18,'aa','06af7a09abd673ca920fb25aaf45760d','3f327de1-4285-4e67-9675-b942d5850fda','aaa','111111',0,2,'2018-07-09 00:45:16','2019-04-11 15:18:55',NULL,'admin'),(19,'qq','be17afaeeb2da6337b7ebdf984ee2e26','e4479018-7b70-4c1c-a4cb-9d9ec2b046b5','asaf','1111111111',1,2,'2018-07-09 00:52:06','2019-04-11 14:54:26',NULL,'admin'),(20,'龚仕林','9354ca6fa55ed1f6f5e88f813bd2c55b','719d0bad-4a4e-440c-9a2f-143744c8c7e9','12@12.1','121212121',1,5,'2019-04-11 16:07:53','2019-04-11 16:10:15',NULL,'admin'),(22,'qw','7cff96959735f1c892fa3d52c4cd5130','8c4c51be-92e2-4860-b4c8-426011a163f8','12@12.1','121212121',1,5,'2019-04-11 16:09:46','2019-04-11 16:09:59',NULL,'admin'),(23,'龚士林2','2e8ca54f72a0f4e8263150a130ce17cc','c646c973-12ec-4447-af2f-97ae8876ff2e','211','1',0,2,'2019-04-11 16:12:45','2019-04-11 18:47:19',NULL,'admin'),(24,'qqqqq','64c4261225f6c7922eccf846aeca3c53','ae148bc4-28e3-48b8-89f0-3d20052ce760','12','12',1,5,'2019-04-11 17:20:07','2019-04-11 17:20:07',NULL,NULL),(25,'jiajia','edb5026c1fa8892a9805ef5db0871f06','0f7ac11e-2d06-4f32-81de-ac680e120533','qw','wwq',1,2,'2019-04-11 18:38:20','2019-04-11 18:38:20',NULL,NULL),(26,'wanghaibo','b24a9e39010fed6bcad0cedc619c9998','ef76b360-680d-4fda-b106-143cfd1d677a','123@qq.com','1212111211',1,3,'2019-04-15 11:47:18','2019-04-16 10:51:38',NULL,'admin'),(27,'jiajiajia','1f7f8b595cf7ddb9cc9c1eb0d446e956','91ddeeb7-db9b-477a-ae2f-932a52b006f0','12@11.com','12345678987',0,5,'2019-04-15 14:47:47','2019-04-17 10:30:22',NULL,'jinyong'),(28,'jinyong','9a9a3681aad617b3d87405fd88c23106','9bcf7fdf-334d-433a-8ae2-5006d43679fd','123@qq.com','12121212121',1,3,'2019-04-15 14:56:52','2019-04-23 09:39:02',NULL,'jinyong'),(29,'ceshi2','9994bd7a44a2af9bb4dbdbdc521ad5fa','6e4dfe2b-f751-4c77-8a4b-d8b2aa0ff107','1212','111',0,5,'2019-04-16 18:37:46','2019-04-17 16:54:41',NULL,'jinyong'),(30,'dawang','eb0d942eb83f5190d917b3f0a282854c','f1e932a3-3ada-45a6-9921-283a79fc7d90','1@2','123',1,2,'2019-04-17 16:04:48','2019-04-23 15:28:56',NULL,'admin');

UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS user_table;

CREATE TABLE user_table (
  id int(10) NOT NULL AUTO_INCREMENT,
  password varchar(100) NOT NULL COMMENT '密码',
  username varchar(50) NOT NULL COMMENT '用户名',
  province varchar(50) DEFAULT NULL COMMENT '省',
  city varchar(50) DEFAULT NULL COMMENT '市',
  district varchar(50) DEFAULT NULL COMMENT '区',
  street varchar(50) DEFAULT NULL COMMENT '街道',
  door varchar(50) DEFAULT NULL COMMENT '门号',
  member varchar(10) DEFAULT NULL COMMENT '会员',
  email varchar(100) DEFAULT NULL COMMENT '邮箱',
  mobile varchar(100) DEFAULT NULL COMMENT '手机号',
  createdTime datetime DEFAULT NULL COMMENT '创建时间',
  modifiedTime datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY username (username),
  UNIQUE KEY index_user (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `user_table`
--

LOCK TABLES user_table WRITE;

INSERT INTO user_table VALUES (1,'e10adc3949ba59abbe56e057f20f883e','admin',NULL,NULL,NULL,NULL,NULL,'钻石会员','37147621@qq.com','1322546768','2019-04-20 14:39:25','2019-04-20 14:39:25'),(2,'e10adc3949ba59abbe56e057f20f883e','龙江手机卖场','江苏省','南京',NULL,'草场门大街','89号','黄金会员','3gds621@163.com','14325687689','2019-04-20 14:39:25','2019-04-20 14:39:25'),(3,'e10adc3949ba59abbe56e057f20f883e','延龄巷天翼手机卖场','江苏省','南京市','秦淮区','延龄巷','60号','黄金会员','98962199@qq.com','17254676800','2019-04-20 14:39:25','2019-04-20 14:39:25'),(4,'e10adc3949ba59abbe56e057f20f883e','小明','江苏省','南京市','玄武区','珠江路','273-1','青铜会员','3714565451@sina.com','17342254968','2019-04-20 14:39:25','2019-04-20 14:39:25'),(5,'e10adc3949ba59abbe56e057f20f883e','小花','江苏省','南京市','秦淮区','中山东路','56号','青铜会员','9471451abc@sina.com','14342224968','2019-04-20 14:39:25','2019-04-20 14:39:25');

UNLOCK TABLES;

-- Dump completed on 2019-04-23 19:46:33
