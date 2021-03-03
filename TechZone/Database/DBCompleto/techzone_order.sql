CREATE DATABASE  IF NOT EXISTS `techzone` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `techzone`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: techzone
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `numeroOrdine` bigint NOT NULL AUTO_INCREMENT,
  `dataInvio` timestamp NULL DEFAULT NULL,
  `dataArrivo` timestamp NULL DEFAULT NULL,
  `totale` float DEFAULT NULL,
  `stato` varchar(45) DEFAULT NULL,
  `idUtente` bigint DEFAULT NULL,
  PRIMARY KEY (`numeroOrdine`),
  KEY `ordineprodotto_idx` (`idUtente`),
  CONSTRAINT `ordineutente` FOREIGN KEY (`idUtente`) REFERENCES `utenteregistrato` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2021-02-11 23:34:43',NULL,398.99,'Pacco smarrito',1),(2,'2021-02-12 00:34:43',NULL,1285.99,'Spedito',1),(9,'2021-03-01 17:28:41',NULL,1629.98,'In preparazione',9),(10,'2021-03-01 17:28:56',NULL,998.99,'In preparazione',9),(11,'2021-03-01 17:29:31',NULL,5667,'In preparazione',10),(12,'2021-03-01 17:30:08',NULL,3429,'In preparazione',11),(13,'2021-03-01 17:30:48',NULL,339.99,'In preparazione',12),(14,'2021-03-01 17:30:55',NULL,559,'In preparazione',12),(15,'2021-03-01 17:31:18',NULL,676.99,'In preparazione',13),(16,'2021-03-01 17:31:52',NULL,764.35,'In preparazione',14);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-01 19:36:17
