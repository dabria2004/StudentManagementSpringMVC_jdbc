-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: student_mvc
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `class_id` varchar(10) NOT NULL,
  `class_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('COU001','Java'),('COU002','Python'),('COU003','PHP'),('COU004','Java EE'),('COU005','C'),('COU006','C++'),('COU007','Maven'),('COU008','English'),('COU009','Vue'),('COU010','React'),('COU011','Spring'),('COU012','Linux');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selected_courses`
--

DROP TABLE IF EXISTS `selected_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selected_courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(100) DEFAULT NULL,
  `class_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selected_courses`
--

LOCK TABLES `selected_courses` WRITE;
/*!40000 ALTER TABLE `selected_courses` DISABLE KEYS */;
INSERT INTO `selected_courses` VALUES (14,'STU003','COU001'),(15,'STU003','COU003'),(16,'STU004','COU002'),(17,'STU004','COU007'),(18,'STU005','COU005'),(19,'STU005','COU008'),(20,'STU005','COU009'),(23,'STU007','COU005'),(24,'STU007','COU010'),(25,'STU007','COU011'),(26,'STU002','COU001'),(27,'STU002','COU006'),(28,'STU002','COU011'),(29,'STU008','COU011'),(30,'STU009','COU011'),(31,'STU009','COU012');
/*!40000 ALTER TABLE `selected_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(10) NOT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `education` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('STU002','Stella','2022-06-06','Female','+955436457568','Diploma in IT'),('STU003','Phyu Thin','2022-06-16','Female','+955436457568','Bachelor of Information Technology'),('STU004','Johnny ','2022-06-08','Male','+95123456789','Bachelor of Computer Science'),('STU005','Dabria','2022-06-10','Female','+95123456789','Diploma in IT'),('STU007','I\'m gay','2022-06-15','Male','+955436457568','Diploma in IT'),('STU008','Testing','2022-06-23','Female','+955436457568','Bachelor of Computer Science'),('STU009','Harry Potter','2022-06-09','Male','+955436457568','Bachelor of Computer Science');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  `user_conpassword` varchar(45) DEFAULT NULL,
  `user_role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('USR001','Phyu Phyu Thin','phyuthin2004@gmail.com','aa','aa','Admin'),('USR002','Dabria Jame','dabria2004@gmail.com','dd','dd','Admin'),('USR003','Dash gg','dash2004@gmail.com','dd','dd','User'),('USR004','Maria','maria2004@gmail.com','aa','aa','Admin'),('USR006','Lay','lay2004@gmail.com','lay','lay','Admin'),('USR008','Hey','hey@gmail.com','ff','ff','User'),('USR009','Tester','testing@gmail.com','ss','ss','Admin'),('USR012','Htoo Htoo','htoo@gmail.com','gg','gg','User');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-26 22:47:54
