-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: railwayreservation
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
-- Table structure for table `trip_entry`
--

DROP TABLE IF EXISTS `trip_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_entry` (
  `trip_id` bigint NOT NULL AUTO_INCREMENT,
  `train_no` bigint NOT NULL,
  `train_name` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `travel_start_date` date DEFAULT NULL,
  `travel_start_time` time DEFAULT NULL,
  `travel_end_date` date DEFAULT NULL,
  `travel_end_time` time DEFAULT NULL,
  `trip_status` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `seats_remaining_in_seater` decimal(18,0) DEFAULT NULL,
  `rac_remaining_in_seater` decimal(18,0) DEFAULT NULL,
  `waitinglist_remaining_in_seater` decimal(18,0) DEFAULT NULL,
  `seats_remaining_in_sleeper` decimal(18,0) DEFAULT NULL,
  `rac_remaining_in_sleeper` decimal(18,0) DEFAULT NULL,
  `waitinglist_remaining_in_sleeper` decimal(18,0) DEFAULT NULL,
  `maximum_rac_count` decimal(18,0) DEFAULT NULL,
  `maximum_waitinglist_count` decimal(18,0) DEFAULT NULL,
  `seater_fair` decimal(18,2) DEFAULT NULL,
  `sleeper_fair` decimal(18,2) DEFAULT NULL,
  `departure_place` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `arrival_place` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`trip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_entry`
--

LOCK TABLES `trip_entry` WRITE;
/*!40000 ALTER TABLE `trip_entry` DISABLE KEYS */;
INSERT INTO `trip_entry` VALUES (1,1001,'NELLAI EXPRESS','2023-12-20','06:00:00','2023-12-20','23:00:00','ONLINE',4,8,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(2,1002,'CHENNAI EGMORE EXPRESS','2023-12-20','05:00:00','2023-12-20','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI'),(3,1001,'NELLAI EXPRESS','2023-12-21','06:00:00','2023-12-21','23:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(4,1002,'CHENNAI EGMORE EXPRESS','2023-12-21','05:00:00','2023-12-21','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI'),(5,1001,'NELLAI EXPRESS','2023-12-22','06:00:00','2023-12-22','23:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(6,1002,'CHENNAI EGMORE EXPRESS','2023-12-22','05:00:00','2023-12-22','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI'),(7,1001,'NELLAI EXPRESS','2023-12-23','06:00:00','2023-12-23','23:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(8,1002,'CHENNAI EGMORE EXPRESS','2023-12-23','05:00:00','2023-12-23','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI'),(9,1001,'NELLAI EXPRESS','2023-12-24','06:00:00','2023-12-24','23:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(10,1002,'CHENNAI EGMORE EXPRESS','2023-12-24','05:00:00','2023-12-24','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI'),(11,1001,'NELLAI EXPRESS','2023-12-25','06:00:00','2023-12-25','23:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(12,1002,'CHENNAI EGMORE EXPRESS','2023-12-25','05:00:00','2023-12-25','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI'),(13,1001,'NELLAI EXPRESS','2023-12-26','06:00:00','2023-12-26','23:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'TIRUNELVELI','CHENNAI'),(14,1002,'CHENNAI EGMORE EXPRESS','2023-12-26','05:00:00','2023-12-26','21:00:00','ONLINE',10,5,3,10,5,3,5,3,230.00,540.00,'CHENNAI','TIRUNELVELI');
/*!40000 ALTER TABLE `trip_entry` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-19 17:33:36
