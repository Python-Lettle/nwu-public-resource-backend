/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.15 : Database - nwu_public_resource
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nwu_public_resource` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nwu_public_resource`;

/*Table structure for table `classroom` */

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `request_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `building` int(11) NOT NULL COMMENT '3',
  `floor` int(11) NOT NULL,
  `classroom` int(11) NOT NULL,
  `state` int(11) NOT NULL COMMENT 'free/classing',
  `request_uid` int(11) DEFAULT NULL COMMENT '申请人id',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `classroom` */

insert  into `classroom`(`request_id`,`building`,`floor`,`classroom`,`state`,`request_uid`) values (2,0,1,2,0,2021117403);

/*Table structure for table `knowledge` */

DROP TABLE IF EXISTS `knowledge`;

CREATE TABLE `knowledge` (
  `id` int(11) NOT NULL,
  `subject` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT 'classify',
  `sensitivity` int(11) DEFAULT NULL COMMENT 'low/high',
  `low_text` text,
  `high_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `view_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `knowledge` */

/*Table structure for table `library_log` */

DROP TABLE IF EXISTS `library_log`;

CREATE TABLE `library_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `floor_num` int(11) NOT NULL,
  `pos` int(11) NOT NULL,
  `state` int(11) NOT NULL COMMENT '0释放 1占用',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `library_log` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `name` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `pwd` tinytext NOT NULL,
  `Email` tinytext NOT NULL,
  `alw_eml` int(11) DEFAULT NULL,
  `profile` tinytext,
  `state` int(11) DEFAULT NULL,
  `pmt_grp` int(11) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `log_date` datetime DEFAULT NULL,
  `ipLocate` tinytext,
  `fieldList` tinytext,
  `role` tinytext,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`uid`,`name`,`pwd`,`Email`,`alw_eml`,`profile`,`state`,`pmt_grp`,`reg_date`,`log_date`,`ipLocate`,`fieldList`,`role`) values (123,'lettle','123321','123',0,NULL,0,0,'2024-02-24 12:36:58',NULL,NULL,NULL,'stu');

/*Table structure for table `wall_article` */

DROP TABLE IF EXISTS `wall_article`;

CREATE TABLE `wall_article` (
  `article_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_uid` int(11) unsigned NOT NULL COMMENT '发布者id',
  `article_text` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `article_state` int(11) DEFAULT NULL COMMENT 'pass/auditting/failed',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wall_article` */

insert  into `wall_article`(`article_id`,`article_uid`,`article_text`,`article_state`) values (1,2021117403,'我发表了第一篇文章！',2);

/*Table structure for table `wall_comment` */

DROP TABLE IF EXISTS `wall_comment`;

CREATE TABLE `wall_comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment_uid` int(11) unsigned NOT NULL COMMENT '发布者id',
  `publish_id` int(10) unsigned NOT NULL COMMENT '评论的文章id',
  `comment_text` text,
  `comment_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wall_comment` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
