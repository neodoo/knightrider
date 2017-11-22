-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: knightrider
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('alex@something.com','alejandro','hernandez','1991-10-02',610264916,'73014985j','2018-10-10'),('demo','javier','sanchez','1992-10-02',666666656,'43543643k','2018-10-10'),('test','are','test','1992-10-02',666666666,'43543543k','2020-11-11');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer_card`
--

LOCK TABLES `customer_card` WRITE;
/*!40000 ALTER TABLE `customer_card` DISABLE KEYS */;
INSERT INTO `customer_card` VALUES (2,'alex@something.com','1234567898765432','alejandro hernandez pardos',0,'2020-10-10',1);
/*!40000 ALTER TABLE `customer_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES ('customer');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('alex@something.com','password'),('demo','.demo8$'),('test','test');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_rol`
--

LOCK TABLES `user_rol` WRITE;
/*!40000 ALTER TABLE `user_rol` DISABLE KEYS */;
INSERT INTO `user_rol` VALUES ('alex@something.com','customer'),('demo','customer'),('test','customer');
/*!40000 ALTER TABLE `user_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'1010DKR','S','red','online','unblocked',40,99,41.6563497,-0.876566,23.50,22.45,1),(2,'2020RRS','S','green','online','unblocked',75,310,41.662,-0.8713,22.22,23.23,0),(3,'1818GGJ','X','blue','online','unblocked',90,454,41.65021,-0.8799,23.13,30.05,1),(4,'2323CVB','X','red','online','unblocked',25,41.659,41.659,-0.899,33.33,35.50,1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle_blocked`
--

LOCK TABLES `vehicle_blocked` WRITE;
/*!40000 ALTER TABLE `vehicle_blocked` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_blocked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle_travel`
--

LOCK TABLES `vehicle_travel` WRITE;
/*!40000 ALTER TABLE `vehicle_travel` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_travel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle_traveling`
--

LOCK TABLES `vehicle_traveling` WRITE;
/*!40000 ALTER TABLE `vehicle_traveling` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_traveling` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-22 13:30:03
