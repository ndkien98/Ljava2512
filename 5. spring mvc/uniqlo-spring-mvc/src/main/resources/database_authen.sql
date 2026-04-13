CREATE DATABASE  IF NOT EXISTS `uniqlo_education` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `uniqlo_education`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: uniqlo_education
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `sku_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `sku_id` (`sku_id`),
  CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`sku_id`) REFERENCES `product_skus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (1,1,1,2,'2026-03-11 15:34:56','2026-03-11 15:34:56'),(2,1,4,1,'2026-03-11 15:34:56','2026-03-11 15:34:56'),(3,2,7,1,'2026-03-11 15:34:56','2026-03-11 15:34:56'),(4,2,10,2,'2026-03-11 15:34:56','2026-03-11 15:34:56'),(5,3,13,1,'2026-03-11 15:34:56','2026-03-11 15:34:56'),(6,4,19,3,'2026-03-11 15:34:56','2026-03-11 15:34:56'),(7,5,25,1,'2026-03-11 15:34:56','2026-03-11 15:34:56');
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `categories_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Thời Trang Nam',NULL,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(2,'Thời Trang Nữ',NULL,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(3,'Thời Trang Trẻ Em',NULL,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(4,'Áo Thun Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(5,'Áo Sơ Mi Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(6,'Quần Tây Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(7,'Quần Jean Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(8,'Áo Khoác Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(9,'Áo Len Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(10,'Đồ Lót Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(11,'Đồ Thể Thao Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(12,'Đồ Mặc Nhà Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(13,'Phụ Kiện Nam',1,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(14,'Áo Thun Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(15,'Áo Kiểu Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(16,'Quần Tây Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(17,'Chân Váy Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(18,'Váy Đầm Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(19,'Áo Khoác Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(20,'Đồ Lót Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(21,'Đồ Thể Thao Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(22,'Đồ Mặc Nhà Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(23,'Phụ Kiện Nữ',2,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(24,'Áo Thun Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(25,'Áo Sơ Mi Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(26,'Quần Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(27,'Váy Đầm Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(28,'Áo Khoác Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(29,'Đồ Lót Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(30,'Đồ Thể Thao Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(31,'Đồ Ngủ Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(32,'Phụ Kiện Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(33,'Giày Dép Trẻ Em',3,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `color_code` varchar(50) NOT NULL,
  `hex_code` varchar(10) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `colors_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'09 ĐEN','#000000','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(2,'00 TRẮNG','#FFFFFF','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(3,'69 XANH NAVY','#001F3F','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(4,'03 XÁM','#808080','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(5,'12 HỒNG','#FFC0CB','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(6,'32 BE','#F5F5DC','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(7,'56 Ô LIU','#808000','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(8,'66 XANH DƯƠNG','#0074D9','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(9,'18 ĐỎ','#FF4136','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(10,'54 XANH LÁ','#2ECC40','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL);
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `color_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `is_main` tinyint(1) DEFAULT '0',
  `sort_order` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `color_id` (`color_id`),
  CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `product_images_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,482557,1,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(2,482557,1,'https://picsum.photos/seed/2/300/200',0,2,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(3,482557,3,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(4,482557,3,'https://picsum.photos/seed/4/300/200',0,2,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(5,482557,2,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(9,482559,3,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(10,482559,1,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(11,482559,4,'https://picsum.photos/seed/2/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(12,482560,3,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(13,482560,4,'https://picsum.photos/seed/4/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(14,482560,1,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(15,482561,2,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(16,482561,3,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(17,482561,8,'https://picsum.photos/seed/2/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(18,482562,2,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(19,482562,1,'https://picsum.photos/seed/4/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(20,482562,5,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(21,482563,3,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(22,482563,1,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(23,482563,4,'https://picsum.photos/seed/2/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(24,482564,1,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(25,482564,6,'https://picsum.photos/seed/4/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(26,482564,3,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(27,482565,1,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(28,482565,5,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(29,482565,2,'https://picsum.photos/seed/2/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(30,482566,6,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(31,482566,3,'https://picsum.photos/seed/4/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(32,482566,1,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(33,482567,9,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(34,482567,8,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(35,482567,10,'https://picsum.photos/seed/2/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(36,482568,3,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(37,482568,1,'https://picsum.photos/seed/4/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(38,482568,4,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(39,482569,9,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(40,482569,3,'https://picsum.photos/seed/1/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(41,482569,1,'https://picsum.photos/seed/2/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(42,482570,1,'https://picsum.photos/seed/3/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(43,482570,4,'https://picsum.photos/seed/4/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(44,482570,3,'https://picsum.photos/seed/5/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(45,482571,4,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(46,482571,1,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20'),(47,482571,3,'https://picsum.photos/seed/6/300/200',1,1,'2026-03-11 15:34:56','2026-03-17 14:35:20');
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_skus`
--

DROP TABLE IF EXISTS `product_skus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_skus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `color_id` int NOT NULL,
  `size_id` int NOT NULL,
  `sku_code` varchar(100) DEFAULT NULL,
  `original_price` decimal(15,2) NOT NULL,
  `sale_price` decimal(15,2) DEFAULT NULL,
  `stock_quantity` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_code` (`sku_code`),
  KEY `product_id` (`product_id`),
  KEY `color_id` (`color_id`),
  KEY `size_id` (`size_id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `product_skus_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `product_skus_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  CONSTRAINT `product_skus_ibfk_3` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `product_skus_ibfk_4` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_skus`
--

LOCK TABLES `product_skus` WRITE;
/*!40000 ALTER TABLE `product_skus` DISABLE KEYS */;
INSERT INTO `product_skus` VALUES (1,482557,1,4,'482557-09ĐEN-M',290000.00,232000.00,100,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(2,482557,3,5,'482557-69NAVY-L',290000.00,232000.00,150,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(3,482557,2,3,'482557-00TRẮNG-S',290000.00,NULL,80,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(4,482558,1,5,'482558-09ĐEN-L',1290000.00,990000.00,50,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(5,482558,3,4,'482558-69NAVY-M',1290000.00,990000.00,60,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(6,482558,4,6,'482558-03XÁM-XL',1290000.00,NULL,40,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(7,482559,3,4,'482559-69NAVY-M',790000.00,632000.00,120,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(8,482559,1,5,'482559-09ĐEN-L',790000.00,632000.00,100,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(9,482559,4,3,'482559-03XÁM-S',790000.00,NULL,90,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(10,482560,3,4,'482560-69NAVY-M',590000.00,NULL,80,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(11,482560,4,5,'482560-03XÁM-L',590000.00,NULL,70,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(12,482560,1,4,'482560-09ĐEN-M',590000.00,472000.00,85,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(13,482561,2,4,'482561-00TRẮNG-M',490000.00,NULL,100,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(14,482561,3,5,'482561-69NAVY-L',490000.00,NULL,95,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(15,482561,8,4,'482561-66XANHDUONG-M',490000.00,392000.00,90,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(16,482562,2,3,'482562-00TRẮNG-S',390000.00,312000.00,110,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(17,482562,1,4,'482562-09ĐEN-M',390000.00,312000.00,100,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(18,482562,5,3,'482562-12HỒNG-S',390000.00,NULL,95,'2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(19,482563,3,3,'482563-69NAVY-S',890000.00,712000.00,120,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(20,482563,1,4,'482563-09ĐEN-M',890000.00,712000.00,130,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(21,482563,4,5,'482563-03XÁM-L',890000.00,NULL,80,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(22,482564,1,3,'482564-09ĐEN-S',690000.00,NULL,75,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(23,482564,6,4,'482564-32BE-M',690000.00,552000.00,85,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(24,482564,3,4,'482564-69NAVY-M',690000.00,NULL,90,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(25,482565,1,3,'482565-09ĐEN-S',990000.00,792000.00,60,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(26,482565,5,4,'482565-12HỒNG-M',990000.00,792000.00,70,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(27,482565,2,5,'482565-00TRẮNG-L',990000.00,NULL,55,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(28,482566,6,3,'482566-32BE-S',1190000.00,952000.00,50,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(29,482566,3,4,'482566-69NAVY-M',1190000.00,952000.00,55,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(30,482566,1,4,'482566-09ĐEN-M',1190000.00,NULL,45,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(31,482567,9,2,'482567-18ĐỎ-XS',190000.00,152000.00,100,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(32,482567,8,3,'482567-66XANHDUONG-S',190000.00,152000.00,110,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(33,482567,10,2,'482567-54XANHLA-XS',190000.00,NULL,95,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(34,482568,3,3,'482568-69NAVY-S',390000.00,NULL,85,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(35,482568,1,4,'482568-09ĐEN-M',390000.00,312000.00,90,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(36,482568,4,3,'482568-03XÁM-S',390000.00,NULL,80,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(37,482569,9,3,'482569-18ĐỎ-S',790000.00,632000.00,70,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(38,482569,3,4,'482569-69NAVY-M',790000.00,632000.00,75,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(39,482569,1,3,'482569-09ĐEN-S',790000.00,NULL,65,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(40,482570,1,2,'482570-09ĐEN-XS',290000.00,NULL,100,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(41,482570,4,3,'482570-03XÁM-S',290000.00,232000.00,95,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(42,482570,3,2,'482570-69NAVY-XS',290000.00,NULL,90,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(43,482571,4,3,'482571-03XÁM-S',490000.00,392000.00,85,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(44,482571,1,4,'482571-09ĐEN-M',490000.00,392000.00,90,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL),(45,482571,3,3,'482571-69NAVY-S',490000.00,NULL,80,'2026-03-11 15:34:56',1,'2026-03-11 15:34:56',NULL);
/*!40000 ALTER TABLE `product_skus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL,
  `category_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `material_info` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `avatar` text,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (482557,4,'Áo Thun Nam Cổ Tròn AIRism','Áo thun mềm mại và thoáng khí với công nghệ AIRism','AIRism, 100% Cotton','2026-03-11 15:34:55',1,'2026-03-19 13:30:47',NULL,'https://picsum.photos/seed/1/300/200'),(482558,5,'Áo Phao Lông Vũ Siêu Nhẹ Nam','Áo phao nhẹ và ấm áp hoàn hảo cho mọi mùa','Lông vũ, Vỏ Nylon','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/2/300/200'),(482559,6,'Quần Jean Nam Dáng Ôm','Quần jean dáng ôm cổ điển với độ co giãn thoải mái','98% Cotton, 2% Spandex','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/3/300/200'),(482560,7,'Áo Len Cổ Tròn Merino Cao Cấp Nam','Áo len lông cừu merino cao cấp','100% Lông Cừu Merino','2026-03-11 15:34:55',1,'2026-03-19 13:30:47',NULL,'https://picsum.photos/seed/4/300/200'),(482561,8,'Áo Sơ Mi Oxford Cotton Supima Nam','Áo sơ mi oxford cổ điển bằng cotton Supima cao cấp','100% Cotton Supima','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/5/300/200'),(482562,14,'Áo Thun Dài Tay Chống UV AIRism Nữ','Chống tia UV với công nghệ AIRism','AIRism, Polyester pha','2026-03-11 15:34:55',1,'2026-03-19 13:30:47',NULL,'https://picsum.photos/seed/6/300/200'),(482563,18,'Quần Jean Nữ Co Giãn Cực Đại Lưng Cao','Quần jean lưng cao với độ co giãn tối ưu','95% Cotton, 5% Spandex','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/1/300/200'),(482564,19,'Chân Váy Dài Rayon Nữ','Chân váy thanh lịch cho mọi dịp','100% Rayon','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/2/300/200'),(482565,20,'Váy Đầm Mềm Mại Nữ','Váy đầm thoải mái và phong cách','Cotton pha','2026-03-11 15:34:55',1,'2026-03-19 13:30:47',NULL,'https://picsum.photos/seed/3/300/200'),(482566,21,'Áo Khoác Chống UV Gấp Gọn Nữ','Áo khoác nhẹ có thể gấp gọn với khả năng chống UV','Polyester','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/4/300/200'),(482567,24,'Áo Thun Họa Tiết Cotton Trẻ Em','Áo thun họa tiết vui nhộn cho trẻ em','100% Cotton','2026-03-11 15:34:55',1,'2026-03-19 13:30:47',NULL,'https://picsum.photos/seed/5/300/200'),(482568,27,'Quần Jean Co Giãn Trẻ Em','Quần jean co giãn thoải mái cho trẻ em năng động','98% Cotton, 2% Spandex','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/6/300/200'),(482569,29,'Áo Khoác Pufftech Trẻ Em','Áo khoác ấm áp và nhẹ nhàng cho trẻ em','Lông tổng hợp, Nylon','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/1/300/200'),(482570,30,'Bộ Đồ Lót HEATTECH Trẻ Em','Đồ lót giữ nhiệt cho thời tiết lạnh','Công nghệ HEATTECH','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/2/300/200'),(482571,31,'Quần Jogger Co Giãn Tối Đa Trẻ Em','Quần jogger thoải mái cho hàng ngày','Cotton pha co giãn','2026-03-11 15:34:55',1,'2026-03-19 13:30:48',NULL,'https://picsum.photos/seed/3/300/200');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `user_id` int NOT NULL,
  `sku_id` int DEFAULT NULL,
  `rating` tinyint NOT NULL,
  `comment` text,
  `user_height` varchar(50) DEFAULT NULL,
  `user_weight` varchar(50) DEFAULT NULL,
  `fit_status` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`),
  KEY `sku_id` (`sku_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`sku_id`) REFERENCES `product_skus` (`id`),
  CONSTRAINT `reviews_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,482557,2,1,5,'Áo thun chất lượng tuyệt vời, rất thoải mái!','170cm','65kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(2,482557,3,2,4,'Chất liệu tốt nhưng màu hơi phai sau khi giặt','175cm','70kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(3,482558,1,4,5,'Áo khoác hoàn hảo cho mùa đông, rất ấm!','180cm','75kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(4,482559,4,7,5,'Quần jean tốt nhất tôi từng mua!','165cm','55kg','Runs small','2026-03-11 15:34:56','2026-03-11 15:34:56'),(5,482560,5,10,4,'Áo len đẹp nhưng hơi đắt','172cm','68kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(6,482562,2,16,5,'Yêu thích tính năng chống UV!','160cm','50kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(7,482563,3,19,5,'Co giãn cực tốt và thoải mái','168cm','58kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(8,482565,4,25,4,'Váy đầm xinh đẹp, hoàn hảo cho mùa hè','165cm','52kg','Runs large','2026-03-11 15:34:56','2026-03-11 15:34:56'),(9,482567,1,34,5,'Con tôi rất thích chiếc áo này!','120cm','25kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56'),(10,482569,5,40,5,'Áo khoác xuất sắc cho trẻ em, rất bền','130cm','30kg','True to size','2026-03-11 15:34:56','2026-03-11 15:34:56');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `size_code` varchar(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `sizes_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes`
--

LOCK TABLES `sizes` WRITE;
/*!40000 ALTER TABLE `sizes` DISABLE KEYS */;
INSERT INTO `sizes` VALUES (1,'XXS','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(2,'XS','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(3,'S','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(4,'M','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(5,'L','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(6,'XL','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(7,'XXL','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(8,'3XL','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(9,'4XL','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL),(10,'5XL','2026-03-11 15:34:55',1,'2026-03-11 15:34:55',NULL);
/*!40000 ALTER TABLE `sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `gender` enum('Male','Female','Decline to state') DEFAULT NULL,
  `role` varchar(20) DEFAULT 'USER',
  `remember_token` varchar(255) DEFAULT NULL,
  `avatar` varchar(500) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nguyễn Văn An','nguyenvanan@email.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO','1990-05-15','Male','USER',NULL,NULL,'2026-03-11 15:34:55','2026-03-11 15:34:55'),(2,'Trần Thị Bình','tranthib@email.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO','1995-08-20','Female','USER',NULL,NULL,'2026-03-11 15:34:55','2026-03-11 15:34:55'),(3,'Lê Văn Cường','levanc@email.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO','1988-03-10','Male','USER',NULL,NULL,'2026-03-11 15:34:55','2026-03-11 15:34:55'),(4,'Phạm Thị Dung','phamthid@email.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO','1992-11-25','Female','USER',NULL,NULL,'2026-03-11 15:34:55','2026-03-11 15:34:55'),(5,'Hoàng Văn Đức','hoangvane@email.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO','1985-07-30','Male','USER',NULL,NULL,'2026-03-11 15:34:55','2026-03-11 15:34:55'),(6,'kiennd','kiennd20@gmail.com','WZRHGrsBESr8wYFZ9sx0tPURuZgG2lmzyvWpwXPKz8U=',NULL,NULL,'USER',NULL,NULL,'2026-03-11 15:55:40','2026-03-11 15:55:40'),(7,'Admin Uniqlo','admin@uniqlo.com','0192023a7bbd73250516f069df18b500',NULL,NULL,'ADMIN',NULL,NULL,'2026-03-22 15:41:39','2026-03-22 15:41:39');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit_stats`
--

DROP TABLE IF EXISTS `visit_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visit_stats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `visit_count` bigint NOT NULL DEFAULT '0',
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit_stats`
--

LOCK TABLES `visit_stats` WRITE;
/*!40000 ALTER TABLE `visit_stats` DISABLE KEYS */;
INSERT INTO `visit_stats` VALUES (1,25,'2026-03-22 15:48:04');
/*!40000 ALTER TABLE `visit_stats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-22 22:53:10
