/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.33 : Database - nwu_public_resource
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nwu_public_resource` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nwu_public_resource`;

/*Table structure for table `classroom` */

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `building_id` int DEFAULT NULL COMMENT '3',
  `classroom_id` tinytext COMMENT 'J301',
  `state` int DEFAULT NULL COMMENT 'free/classing',
  `num` int DEFAULT NULL COMMENT 'studying_student num(state=free)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `classroom` */

/*Table structure for table `knowledge` */

DROP TABLE IF EXISTS `knowledge`;

CREATE TABLE `knowledge` (
  `subject` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT 'classify',
  `sensitivity` int DEFAULT NULL COMMENT 'low/high',
  `low_text` text,
  `high_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `view_num` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `knowledge` */

/*Table structure for table `library` */

DROP TABLE IF EXISTS `library`;

CREATE TABLE `library` (
  `floor_num` int NOT NULL,
  `floor_x` int NOT NULL,
  `floor_y` int NOT NULL,
  `state` int NOT NULL COMMENT 'free/not free',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`floor_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `library` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` tinytext NOT NULL,
  `pwd` tinytext NOT NULL,
  `Email` tinytext NOT NULL,
  `alwEml` int DEFAULT NULL,
  `profile` tinytext,
  `state` int DEFAULT NULL,
  `pmtGrp` int DEFAULT NULL,
  `regDate` tinytext,
  `logDate` tinytext,
  `ipLocate` tinytext,
  `fieldList` tinytext,
  `role` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

/*Table structure for table `wall` */

DROP TABLE IF EXISTS `wall`;

CREATE TABLE `wall` (
  `publish_id` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '发布者id',
  `pu_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `pu_state` int DEFAULT NULL COMMENT 'pass/auditting/failed',
  `comments_id` tinytext,
  `co_text` text,
  `co_state` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wall` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
