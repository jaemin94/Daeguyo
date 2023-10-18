-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: daeguyo
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `tbl_cart`
--

DROP TABLE IF EXISTS `tbl_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cart` (
  `cart_id` varchar(255) NOT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `selected_option` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `user_cart_idx` (`u_email`),
  KEY `menu_cart_idx` (`menu_id`),
  CONSTRAINT `menu_cart` FOREIGN KEY (`menu_id`) REFERENCES `tbl_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_cart` FOREIGN KEY (`u_email`) REFERENCES `tbl_user` (`u_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cart`
--

LOCK TABLES `tbl_cart` WRITE;
/*!40000 ALTER TABLE `tbl_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_coupons`
--

DROP TABLE IF EXISTS `tbl_coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_coupons` (
  `coupon_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `deductedPrice` int DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `expiredDate` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`coupon_id`),
  KEY `user_coupon_idx` (`u_email`),
  CONSTRAINT `user_coupon` FOREIGN KEY (`u_email`) REFERENCES `tbl_user` (`u_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_coupons`
--

LOCK TABLES `tbl_coupons` WRITE;
/*!40000 ALTER TABLE `tbl_coupons` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_emailcheck`
--

DROP TABLE IF EXISTS `tbl_emailcheck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_emailcheck` (
  `email` varchar(255) DEFAULT NULL,
  `emailcomfirm` varchar(255) NOT NULL,
  PRIMARY KEY (`emailcomfirm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_emailcheck`
--

LOCK TABLES `tbl_emailcheck` WRITE;
/*!40000 ALTER TABLE `tbl_emailcheck` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_emailcheck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_menu`
--

DROP TABLE IF EXISTS `tbl_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_menu` (
  `menu_id` varchar(255) NOT NULL,
  `res_id` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `menu_detail` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `options` varchar(255) DEFAULT NULL,
  `menu_catagory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `res_menu_idx` (`res_id`),
  CONSTRAINT `res_menu` FOREIGN KEY (`res_id`) REFERENCES `tbl_res` (`res_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_menu`
--

LOCK TABLES `tbl_menu` WRITE;
/*!40000 ALTER TABLE `tbl_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_order`
--

DROP TABLE IF EXISTS `tbl_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_order` (
  `order_id` varchar(255) NOT NULL,
  `coupon_id` int DEFAULT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `res_id` varchar(255) DEFAULT NULL,
  `selecte_option` varchar(255) DEFAULT NULL,
  `order_amount` int DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_order_idx` (`u_email`),
  KEY `coupon_order_idx` (`coupon_id`),
  KEY `menu_order_idx` (`menu_id`),
  KEY `res_order_idx` (`res_id`),
  CONSTRAINT `coupon_order` FOREIGN KEY (`coupon_id`) REFERENCES `tbl_coupons` (`coupon_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_order` FOREIGN KEY (`menu_id`) REFERENCES `tbl_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `res_order` FOREIGN KEY (`res_id`) REFERENCES `tbl_res` (`res_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_order` FOREIGN KEY (`u_email`) REFERENCES `tbl_user` (`u_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_order`
--

LOCK TABLES `tbl_order` WRITE;
/*!40000 ALTER TABLE `tbl_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_payment`
--

DROP TABLE IF EXISTS `tbl_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_payment` (
  `payment_id` int NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `pay_method` varchar(255) DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  `pay_status` tinyint DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `order_payment_idx` (`order_id`),
  CONSTRAINT `order_payment` FOREIGN KEY (`order_id`) REFERENCES `tbl_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_payment`
--

LOCK TABLES `tbl_payment` WRITE;
/*!40000 ALTER TABLE `tbl_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_phonecheck`
--

DROP TABLE IF EXISTS `tbl_phonecheck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_phonecheck` (
  `phone` varchar(255) DEFAULT NULL,
  `smscomfirmnum` varchar(255) NOT NULL,
  PRIMARY KEY (`smscomfirmnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_phonecheck`
--

LOCK TABLES `tbl_phonecheck` WRITE;
/*!40000 ALTER TABLE `tbl_phonecheck` DISABLE KEYS */;
INSERT INTO `tbl_phonecheck` VALUES ('01049919570','75594');
/*!40000 ALTER TABLE `tbl_phonecheck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_res`
--

DROP TABLE IF EXISTS `tbl_res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_res` (
  `res_id` varchar(255) NOT NULL,
  `res_rating` double DEFAULT NULL,
  `res_name` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `open_status` tinyint DEFAULT NULL,
  `food_catagory` varchar(255) DEFAULT NULL,
  `res_image` varchar(255) DEFAULT NULL,
  `min_order_price` varchar(255) DEFAULT NULL,
  `order_tax` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_res`
--

LOCK TABLES `tbl_res` WRITE;
/*!40000 ALTER TABLE `tbl_res` DISABLE KEYS */;
INSERT INTO `tbl_res` VALUES ('1',4.8,'호랑이식당','중구','000-0000-0000','1234',1,'한식','https://cdn.imweb.me/upload/S2020020306340f9e8280d/47b2fd36fa326.jpg','20,000','3,000','라면,일식,중식');
/*!40000 ALTER TABLE `tbl_res` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_review`
--

DROP TABLE IF EXISTS `tbl_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_review` (
  `r_id` varchar(255) NOT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `res_id` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `img` blob,
  `content` varchar(255) DEFAULT NULL,
  `reviewdate` datetime DEFAULT NULL,
  PRIMARY KEY (`r_id`),
  KEY `user_review_idx` (`u_email`),
  KEY `order_review_idx` (`order_id`),
  KEY `res_review_idx` (`res_id`),
  CONSTRAINT `order_review` FOREIGN KEY (`order_id`) REFERENCES `tbl_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `res_review` FOREIGN KEY (`res_id`) REFERENCES `tbl_res` (`res_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_review` FOREIGN KEY (`u_email`) REFERENCES `tbl_user` (`u_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_review`
--

LOCK TABLES `tbl_review` WRITE;
/*!40000 ALTER TABLE `tbl_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_email` varchar(255) NOT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (2,'leejm_1994@naver.com','04999 서울 광진구 면목로 1  (군자동) 집갈래','$2a$10$5mJ/CRCTBUl0PRthF60IX.7n.oTczWM5TbfuIX4Nsj6BniXcxNgku','01049919570','재민','ROLE_User');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-12  8:16:30
