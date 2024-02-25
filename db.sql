-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: localhost    Database: nwu_public_resource
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `request_id` int unsigned NOT NULL AUTO_INCREMENT,
  `building` int NOT NULL COMMENT '3',
  `floor` int NOT NULL,
  `classroom` int NOT NULL,
  `state` int NOT NULL COMMENT 'free/classing',
  `request_uid` int DEFAULT NULL COMMENT '申请人id',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (2,0,1,2,0,2021117403);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knowledge`
--

DROP TABLE IF EXISTS `knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `knowledge` (
  `id` int NOT NULL,
  `subject` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT 'classify',
  `sensitivity` int DEFAULT NULL COMMENT 'low/high',
  `low_text` text,
  `high_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `view_num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knowledge`
--

LOCK TABLES `knowledge` WRITE;
/*!40000 ALTER TABLE `knowledge` DISABLE KEYS */;
INSERT INTO `knowledge` VALUES (1,'机器学习',0,'机器学习基础;https://pan.baidu.com;机器学习进阶资料;https://pan.baidu.com',NULL,0),(2,'数据结构',0,'数据结构基础;https://pan.baidu.com;数据结构高级教程;https://pan.baidu.com','数据结构考研资料;https://pan.baidu.com',0);
/*!40000 ALTER TABLE `knowledge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library_log`
--

DROP TABLE IF EXISTS `library_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `floor_num` int NOT NULL,
  `pos` int NOT NULL,
  `state` int NOT NULL COMMENT '0释放 1占用',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_log`
--

LOCK TABLES `library_log` WRITE;
/*!40000 ALTER TABLE `library_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uid` int NOT NULL,
  `name` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `pwd` tinytext NOT NULL,
  `Email` tinytext NOT NULL,
  `alw_eml` int DEFAULT NULL,
  `profile` tinytext,
  `state` int DEFAULT NULL,
  `pmt_grp` int DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `log_date` datetime DEFAULT NULL,
  `ipLocate` tinytext,
  `fieldList` tinytext,
  `role` tinytext,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (123,'lettle','123321','123',0,NULL,0,0,'2024-02-24 12:36:58',NULL,NULL,NULL,'stu');
INSERT INTO `user` VALUES (2021117110,'admin','888888','25252525@qq.com',0,NULL,0,0,'2024-02-25 15:36:58',NULL,NULL,NULL,'admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wall_article`
--

DROP TABLE IF EXISTS `wall_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wall_article` (
  `article_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `article_uid` int unsigned NOT NULL COMMENT '发布者id',
  `article_text` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `article_state` int DEFAULT NULL COMMENT 'pass/auditting/failed',
  `article_username` tinytext,
  `publish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wall_article`
--

LOCK TABLES `wall_article` WRITE;
/*!40000 ALTER TABLE `wall_article` DISABLE KEYS */;
INSERT INTO `wall_article` VALUES (5,123,'发布一条带发布时间的信息',1,'lettle','2024-02-25 13:19:12');
/*!40000 ALTER TABLE `wall_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wall_comment`
--

DROP TABLE IF EXISTS `wall_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wall_comment` (
  `comment_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment_uid` int unsigned NOT NULL COMMENT '发布者id',
  `publish_id` int unsigned NOT NULL COMMENT '评论的文章id',
  `comment_text` text,
  `comment_state` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wall_comment`
--

LOCK TABLES `wall_comment` WRITE;
/*!40000 ALTER TABLE `wall_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `wall_comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-25 14:42:22
