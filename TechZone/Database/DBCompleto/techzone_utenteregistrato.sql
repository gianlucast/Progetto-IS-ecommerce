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
-- Table structure for table `utenteregistrato`
--

DROP TABLE IF EXISTS `utenteregistrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenteregistrato` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(64) DEFAULT NULL,
  `cognome` varchar(64) DEFAULT NULL,
  `telefono` bigint DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `metodoPagamento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenteregistrato`
--

LOCK TABLES `utenteregistrato` WRITE;
/*!40000 ALTER TABLE `utenteregistrato` DISABLE KEYS */;
INSERT INTO `utenteregistrato` VALUES (1,'Giovanni','Simonini',3270876971,'a4abe91512306f1e4f1f8e06d0b6684ac4cd5b66','g.simonini@gmail.com','Italia, Avellino, 83100, Viale Italia 12','Mastercard, 1111222233334444, 04/2026, 777'),(9,'Ireneo','Rizzo',3572703765,'b0cfb84b73c4244e1d6e27d7a8b571f80db6e963','Ireneor@gmaail.com','Italy, Ottaviano, 80040, Via dei Fiorentini 38','null, 5348782265888316, 2/2022, 123'),(10,'Alma','Toscani',3554124535,'efa7e7f84dcb9755913133ee611e3110c82cca9d','Almat@gmail.com','Italy, Muhlbach, 39037, Via di Santa Melania 130','null, 5154274835772835, 5/2021, 456'),(11,'Annabella','Pisani',3256259137,'fc20da41c52250c173ceadac022c761af8b89fc6','annabellap@gmail.com','Italy, Segromigno, 55010, Via Melisurgo 65','null, 4716076613335295, 2/2024, 789'),(12,'Terzo','Cattaneo',3665693742,'b33011931595af84e9068e43880c805182e3105b','terzoc@gmail.com','Italy, Tel, 39020, Via Nazionale 10','null, 4485352942205924, 2/2024, 415'),(13,'Fabiano','Beneventi',3347462753,'f01f55035185de11de4e1129230a4b751109fccf','fabianob@gmail.com','Italy, Trequanda, 53020, Via Santa Teresa 117','null, 4916322894595670, 3/2024, 444'),(14,'Antonia','Malgioglio',3481630030,'4d68f379e58f0a4d25172e813ccb63062ace7da5','antoniadl@gmail.com','Italy, Polverina, 62037, Piazza Guglielmo Pepe 100','null, 4532960118463862, 5/2024, 784'),(15,'Pio','Rossi',3151512829,'42d71bde0d1d4e91ea36b8f94f653cb4d6ab2ffe','pior@gmail.com','Italy, Valsinni, 75029, Via Pasquale Scura 2','null, 5392496615049017, 3/2022, 592');
/*!40000 ALTER TABLE `utenteregistrato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-01 19:36:18
