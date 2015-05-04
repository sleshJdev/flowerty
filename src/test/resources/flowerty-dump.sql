-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: flowerty
-- ------------------------------------------------------
-- Server version	5.5.38-0+wheezy1

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
-- Table structure for table `access`
--

DROP TABLE IF EXISTS `access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` enum('create_order','contact','comment_order','settings','user','assign_role','orders_ready','orders_accepted','orders_all') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access`
--

LOCK TABLES `access` WRITE;
/*!40000 ALTER TABLE `access` DISABLE KEYS */;
INSERT INTO `access` VALUES (1,'create_order'),(2,'contact'),(3,'comment_order'),(4,'settings'),(5,'user'),(6,'assign_role'),(7,'orders_ready'),(8,'orders_accepted'),(9,'orders_all');
/*!40000 ALTER TABLE `access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TOWN` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STREET` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HOUSE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FLAT` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COUNTRY` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Minsk','Kirova','10','11','Belarus'),(2,'Moscow','Lermontova','1','2','Russia'),(3,'London','Orange','12','34','GB'),(4,'Minsk','Pulikhova','90','19','Belarus'),(5,'Moscow','Pushkina','10','20','Russia'),(6,'London','Green','11','33','GB'),(7,'Minsk','Independence','11','111','Belarus'),(8,'Moscow','Lermontova','6','2','Russia'),(9,'London','Queen','12','34','GB'),(10,'Minsk','Kirova','80','119','Belarus'),(11,'Moscow','Lermontova','10','20','Russia'),(12,'London','Charles','12','34','GB'),(13,'London','Orange','112','314','GB');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WEBSITE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'FandJ','www.FandJ.com'),(2,'FlowersForYou','www.FlowersForYou.com'),(3,'GoodPresents','www.GoodPresents.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SURNAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `FATHERNAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS_ID` int(11) NOT NULL,
  `COMPANY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `contact_address_id` (`ADDRESS_ID`),
  KEY `contact_company_id` (`COMPANY_ID`),
  CONSTRAINT `contact_company_id` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `contact_address_id` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'Anton','Antonov','Antonovich','1990-12-03','anton@mail.com',1,1),(2,'Ivan','Ivanov','Ivanovich','1990-10-04','ivanov@mail.com',2,1),(3,'Sergey','Sergeew','Sergeewitch',NULL,NULL,3,1),(4,'Helen','Ivanova','Ivanovna','1990-12-03',NULL,4,2),(5,'John','Brown',NULL,'1990-12-03','john@mail.com',5,3),(6,'Chuck','Brown',NULL,'1990-12-03','chuck@mail.com',6,1),(7,'Alex','Sidorov','Alexandrovich','1990-12-03','alex@mail.com',7,2),(8,'Boris','Borisov','Borisovich',NULL,NULL,8,3),(9,'Jack','Black',NULL,'1989-01-03','black@mail.com',9,1),(10,'Sarah','Brown',NULL,'1990-12-03','sarahn@mail.com',10,2),(11,'Olga','Sergeeva','Sergeevna','1980-10-06','sergeeva80@mail.com',11,3),(12,'Olga','Antonova','Antonovna','1992-11-04','olga@mail.com',1,1);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('changelog-1.0-1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:46',1,'EXECUTED','7:732519777f6e5cc98cc1d7088d9f3645','createTable','',NULL,'3.3.2'),('changelog-1.0-2','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:46',2,'EXECUTED','7:c41327dbefaa66e9cb90df31b387dbc2','createTable','',NULL,'3.3.2'),('changelog-1.0-3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:46',3,'EXECUTED','7:3de7992fa705c6527a4159261dcd3413','createTable','',NULL,'3.3.2'),('changelog-1.0-4','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:46',4,'EXECUTED','7:3b0201e90715201e0479ade196d90ac8','createTable','',NULL,'3.3.2'),('changelog-1.0-5','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',5,'EXECUTED','7:a672db72d5dcf7bbdbdb48501fe282ab','createTable','',NULL,'3.3.2'),('changelog-1.0-6','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',6,'EXECUTED','7:1694d2b878364acaab3d0c618bc20acf','createTable','',NULL,'3.3.2'),('changelog-1.0-7','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',7,'EXECUTED','7:f91eb989f29045dd973cd14400e470e4','createTable','',NULL,'3.3.2'),('changelog-1.0-8','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',8,'EXECUTED','7:dcb1120fda7f39f2af09815169e55e45','createTable','',NULL,'3.3.2'),('changelog-1.0-9','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',9,'EXECUTED','7:315fc1c57f0424dfb7de097413442da8','createTable','',NULL,'3.3.2'),('changelog-1.0-10','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',10,'EXECUTED','7:711ff1bdc3f6b8fb3f910e468ae38579','createTable','',NULL,'3.3.2'),('changelog-1.0-11','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:47',11,'EXECUTED','7:b5611cf8d12b45052e3cc8cf0689e592','createTable','',NULL,'3.3.2'),('changelog-1.0-12','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:48',12,'EXECUTED','7:5c34730e0ac5f788134cc59bd4ddba0a','createTable','',NULL,'3.3.2'),('changelog-1.0-13','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:48',13,'EXECUTED','7:2c6a0104eafd59dd450d6176e67341d6','createTable','',NULL,'3.3.2'),('changelog-1.0-14','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:48',14,'EXECUTED','7:912c1be4d2dab91bb9d8ab142ddcd035','createTable','',NULL,'3.3.2'),('changelog-1.0-15','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:48',15,'EXECUTED','7:72fdf85263f908b8914e85d47ab5cf77','addPrimaryKey','',NULL,'3.3.2'),('changelog-1.0-16','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:49',16,'EXECUTED','7:75dd3bb1a92ef88b5ac876de12a6c144','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-17','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:49',17,'EXECUTED','7:df35caa1736b7b20e7190aad1a341509','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-18','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:49',18,'EXECUTED','7:95e7295b1747d962ae3dae224b50dbf1','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-19','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:49',19,'EXECUTED','7:0aa9ef73627c98226f7e9da86f957f7c','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-20','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:50',20,'EXECUTED','7:d38b2ce1f6f92c1c50e18465c7c6648a','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-21','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:50',21,'EXECUTED','7:7c622976249ca83352e5e3f6ae588ec6','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-22','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:50',22,'EXECUTED','7:fec703743d0b5e09b25d8a7dc2a4c47c','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-23','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:51',23,'EXECUTED','7:3998092fcb12f61ea322c072133973c8','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-24','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:51',24,'EXECUTED','7:77674f6c3cd46ee019bcd3ecfb0ed448','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-25','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:51',25,'EXECUTED','7:48bb088e5495950402324bddca07bf0c','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-26','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:52',26,'EXECUTED','7:89321e6ca4c83e442857a61506c2d7d6','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-27','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:52',27,'EXECUTED','7:a35a3a5dc2b0858fd8d89e6df8ea6e14','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-28','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:52',28,'EXECUTED','7:43315fee545f5c42983cbd5ccba48eb3','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-29','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:52',29,'EXECUTED','7:83d4be98137cce73c727b76dd4627387','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-30','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:53',30,'EXECUTED','7:a0c6cf6b05042964d967c4bd866fa769','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-31','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:53',31,'EXECUTED','7:13d6d0cb2cd12ef862428df48edb9d8e','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-32','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:53',32,'EXECUTED','7:b2e3bf484e62ffa96e79427c8f62b239','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-33','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:54',33,'EXECUTED','7:175250a1b0a214b14e50c0a00d080a5f','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-34','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:54',34,'EXECUTED','7:ca8eb932b98fc5c11cd9b119cf4d6a2c','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-1.0-35','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x/changelog-1.0.xml','2015-05-04 23:07:55',35,'EXECUTED','7:5056add63461c339671954c3c05b53b1','addForeignKeyConstraint','',NULL,'3.3.2'),('testdata-1.0-1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',36,'EXECUTED','7:7210a0e4ca3ca772eb5fe7f60335dbdd','insert (x3)','',NULL,'3.3.2'),('testdata-1.0-2','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',37,'EXECUTED','7:606a8b3b18ec175b120a4ffc881c3b69','insert (x3)','',NULL,'3.3.2'),('testdata-1.0-3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',38,'EXECUTED','7:603476f8d5b7264a53ac3b50d3a2dde0','insert (x6)','',NULL,'3.3.2'),('testdata-1.0-4','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',39,'EXECUTED','7:68e6b2dc63c747275b7708ec1e8ec569','insert (x3)','',NULL,'3.3.2'),('testdata-1.0-6','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',40,'EXECUTED','7:dde9d0f467477023f13fec0f43c630ce','insert (x5)','',NULL,'3.3.2'),('testdata-1.0-7','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',41,'EXECUTED','7:d41d8cd98f00b204e9800998ecf8427e','Empty','',NULL,'3.3.2'),('testdata-1.0-8','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:55',42,'EXECUTED','7:774735f68d3ecf7814154855375f6ff9','insert (x8)','',NULL,'3.3.2'),('testdata-1.0-9','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:56',43,'EXECUTED','7:32f7f383fbb259c3e902d1a34cce171a','insert (x2)','',NULL,'3.3.2'),('testdata-1.0-10','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/1.x.data/testdata-1.0.xml','2015-05-04 23:07:56',44,'EXECUTED','7:363474d864075eafd02418b32d0eaa3a','insert (x2)','',NULL,'3.3.2'),('changelog-2.0-1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:56',45,'EXECUTED','7:83d512a2b3294fff17e9812de59dfc8c','dropForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-2','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:56',46,'EXECUTED','7:7d00004957388f5185a86ed268e46c4d','dropForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:57',47,'EXECUTED','7:c375c6d231e50e770ffd818382e30635','dropForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-4','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:57',48,'EXECUTED','7:c30f68fa69fefc0afc357cbe0d9b9536','dropForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-5','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:57',49,'EXECUTED','7:a46c4a32c46900cc43edf5c420604502','dropForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-7','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:57',50,'EXECUTED','7:fbdc8b85dcb15b27b69b227f9652ecc8','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-8','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:58',51,'EXECUTED','7:f9874f53030eed27b5ef2a847c1e4e15','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-9','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:58',52,'EXECUTED','7:f6e405234f2bc3d1fe1e0996bfc9a922','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-10','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:58',53,'EXECUTED','7:92f72eba9d2e7226a00aebb58f1afc95','addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-2.0-11','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x/changelog-2.0.xml','2015-05-04 23:07:58',54,'EXECUTED','7:256f1ccd5f69293cea24fb0ae36da105','addForeignKeyConstraint','',NULL,'3.3.2'),('testdata-2.0.1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x.data/testdata-2.0.xml','2015-05-04 23:07:59',55,'EXECUTED','7:17c80433b792f65a0cd8713257e84f99','insert (x10)','',NULL,'3.3.2'),('testdata-2.0-2','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x.data/testdata-2.0.xml','2015-05-04 23:07:59',56,'EXECUTED','7:1f154d57df50f9afc518eca979651ce5','insert (x10)','',NULL,'3.3.2'),('testdata-2.0-3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x.data/testdata-2.0.xml','2015-05-04 23:07:59',57,'EXECUTED','7:f84b8981d274914c7a3fef681bcc5d4f','insert (x6)','',NULL,'3.3.2'),('testdata-2.0-4','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/2.x.data/testdata-2.0.xml','2015-05-04 23:07:59',58,'EXECUTED','7:f76c685244cd730c5e49af4f42543e86','insert (x5)','',NULL,'3.3.2'),('changelog-3.0-1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/3.x/changelog-3.0.xml','2015-05-04 23:07:59',59,'EXECUTED','7:66e9790a59d4e3083c611acd648427fa','addColumn','',NULL,'3.3.2'),('changelog-3.0-2','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/3.x/changelog-3.0.xml','2015-05-04 23:07:59',60,'EXECUTED','7:7800a5d85b3f131d95bb26f203de9a4b','delete','',NULL,'3.3.2'),('changelog-3.0-3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/3.x/changelog-3.0.xml','2015-05-04 23:07:59',61,'EXECUTED','7:2cf8581594d27d6dd5bbc5e298300b5d','insert (x9)','',NULL,'3.3.2'),('changelog-3.0-4','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/3.x/changelog-3.0.xml','2015-05-04 23:07:59',62,'EXECUTED','7:3f4f92855705f437f593d8f38b30e3c3','delete, insert (x10)','',NULL,'3.3.2'),('changelog-4.0-1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/4.x/changelog-4.0.xml','2015-05-04 23:08:00',63,'EXECUTED','7:beb65c13c013fb9d3216fa590a9f9032','addColumn','',NULL,'3.3.2'),('testdata-4.0.1','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/4.x.data/testdata-4.0.xml','2015-05-04 23:08:00',64,'EXECUTED','7:bdc0950b0c313afce8900fe4ac4cd19f','insert (x8)','',NULL,'3.3.2'),('testdata-4.0.2','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/4.x.data/testdata-4.0.xml','2015-05-04 23:08:00',65,'EXECUTED','7:dfff3ec01035eb6bf1853b13ac6b3383','insert (x4)','',NULL,'3.3.2'),('testdata-4.0.3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/4.x.data/testdata-4.0.xml','2015-05-04 23:08:00',66,'EXECUTED','7:fc329cdfbe7bbd57d17d0cd0adcf4d90','insert (x10)','',NULL,'3.3.2'),('changelog-5.0-3','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/5.x/changelog-5.0.xml','2015-05-04 23:08:01',67,'EXECUTED','7:0e2bd3af91bd077cc3d13ea7856de178','dropForeignKeyConstraint, renameColumn, addForeignKeyConstraint, dropForeignKeyConstraint, addForeignKeyConstraint','',NULL,'3.3.2'),('changelog-5.0-4','Maria','/home/slesh/workspace/Eclipse/iTechArt/flowerty/src/main/liquibase/master/5.x/changelog-5.0.xml','2015-05-04 23:08:02',68,'EXECUTED','7:13805f36898bfc08d2b881506aa0f5c5','addColumn, addForeignKeyConstraint','',NULL,'3.3.2');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flower`
--

DROP TABLE IF EXISTS `flower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flower` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flower`
--

LOCK TABLES `flower` WRITE;
/*!40000 ALTER TABLE `flower` DISABLE KEYS */;
INSERT INTO `flower` VALUES (1,'Red Rose'),(2,'White Rose'),(3,'Yellow Tulip'),(4,'Camomile'),(5,'Iris'),(6,'Violet');
/*!40000 ALTER TABLE `flower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COST` float DEFAULT NULL,
  `FLOWER_ID` int(11) DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `REMAIN` int(11) DEFAULT NULL,
  `IMAGE_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `goods_company_id` (`COMPANY_ID`),
  KEY `goods_flower_id` (`FLOWER_ID`),
  CONSTRAINT `goods_company_id` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `goods_flower_id` FOREIGN KEY (`FLOWER_ID`) REFERENCES `flower` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,12.12,1,1,11,NULL),(2,132.12,2,1,110,NULL),(3,12.12,3,2,150,NULL),(4,12.2,1,2,15,'bush-rose.jpg'),(5,19,4,1,15,'flower-iris.jpg'),(6,30,5,2,150,'orchid_rose.jpg'),(7,3,6,1,130,'violet_pion.jpg');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GOODS_ID` int(11) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `item_goods_id` (`GOODS_ID`),
  KEY `item_order_id` (`ORDER_ID`),
  CONSTRAINT `item_order_id` FOREIGN KEY (`ORDER_ID`) REFERENCES `purchase` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_goods_id` FOREIGN KEY (`GOODS_ID`) REFERENCES `goods` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,8,1),(2,6,8,6),(3,3,7,9),(4,5,6,2),(5,2,6,3),(6,6,5,1),(7,1,4,1),(8,4,3,7),(9,3,2,5),(10,4,1,15);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_altering`
--

DROP TABLE IF EXISTS `order_altering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_altering` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` timestamp NULL DEFAULT NULL,
  `STATE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `COMMENT` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `order_altering_order_id` (`ORDER_ID`),
  KEY `order_altering_state_id` (`STATE_ID`),
  KEY `order_altering_user_id` (`USER_ID`),
  CONSTRAINT `order_altering_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_altering_order_id` FOREIGN KEY (`ORDER_ID`) REFERENCES `purchase` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_altering_state_id` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_altering`
--

LOCK TABLES `order_altering` WRITE;
/*!40000 ALTER TABLE `order_altering` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_altering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OPERATOR` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NUMBER` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TYPE` enum('HOME','CELL') COLLATE utf8_unicode_ci DEFAULT NULL,
  `COMMENT` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONTACT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `phone_contact_id` (`CONTACT_ID`),
  CONSTRAINT `phone_contact_id` FOREIGN KEY (`CONTACT_ID`) REFERENCES `contact` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,'12','34','56789','CELL','Comment1',1),(2,'67','89','01234','HOME','Comment2',1),(3,'56','78','90123','CELL','Comment3',2),(4,'34','56','78901','HOME','Comment4',2),(5,'56','78','90123','CELL','Comment5',1),(6,'34','56','78901','HOME','Comment6',2);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MANAGER_ID` int(11) DEFAULT NULL,
  `COST` float DEFAULT NULL,
  `STAFF_ID` int(11) DEFAULT NULL,
  `DELIVERY_MANAGER_ID` int(11) DEFAULT NULL,
  `RECEIVER_ID` int(11) DEFAULT NULL,
  `STATE_ID` int(11) DEFAULT NULL,
  `DELIVERY_DATE` date DEFAULT NULL,
  `ADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `order_state_id` (`STATE_ID`),
  KEY `order_customer_id` (`CUSTOMER_ID`),
  KEY `order_delivery_manager_id` (`DELIVERY_MANAGER_ID`),
  KEY `order_manager_id` (`MANAGER_ID`),
  KEY `order_receiver_id` (`RECEIVER_ID`),
  KEY `order_staff_id` (`STAFF_ID`),
  KEY `purchase_address_id` (`ADDRESS_ID`),
  CONSTRAINT `purchase_address_id` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `order_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `contact` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_delivery_manager_id` FOREIGN KEY (`DELIVERY_MANAGER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_manager_id` FOREIGN KEY (`MANAGER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_receiver_id` FOREIGN KEY (`RECEIVER_ID`) REFERENCES `contact` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_staff_id` FOREIGN KEY (`STAFF_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `order_state_id` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,1,'15 Red roses bouquet',5,12,6,4,2,1,'2015-05-01',NULL),(2,3,'5 yellow tulips',5,19.1,6,4,4,2,'2015-05-02',NULL),(3,5,'orchids bouquet',5,50,6,4,6,3,'2015-08-08',NULL),(4,7,'1 red rose',5,5,6,4,8,4,'2015-05-01',NULL),(5,9,'big pink bouquet',5,50,6,4,10,5,'2015-10-10',NULL),(6,11,'multicolor tulips bouquet',5,30,6,4,12,6,'2015-03-02',NULL),(7,1,'9 tea roses',5,32,6,4,2,7,'2015-05-01',NULL),(8,3,'1 white and 6 red roses',5,7,6,4,4,8,'2015-05-02',NULL);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` enum('ORDERS_MANAGER','ORDERS_PROCESSOR','DELIVERY_MANAGER','SUPERVISOR','ADMIN') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'DELIVERY_MANAGER'),(3,'ORDERS_MANAGER'),(4,'ORDERS_PROCESSOR'),(5,'SUPERVISOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_right`
--

DROP TABLE IF EXISTS `role_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_right` (
  `ROLE_ID` int(11) NOT NULL,
  `RIGHT_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RIGHT_ID`),
  KEY `role_right_right_id` (`RIGHT_ID`),
  CONSTRAINT `role_right_role_id` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `role_right_right_id` FOREIGN KEY (`RIGHT_ID`) REFERENCES `access` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_right`
--

LOCK TABLES `role_right` WRITE;
/*!40000 ALTER TABLE `role_right` DISABLE KEYS */;
INSERT INTO `role_right` VALUES (3,1),(3,2),(5,2),(5,3),(1,4),(1,5),(2,5),(1,6),(4,8),(5,9);
/*!40000 ALTER TABLE `role_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` enum('NEW','ACCEPTED','PROCESSING','READY','DELIVERY','IMPOSSIBLE','CANCELED','CLOSED') COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'ACCEPTED'),(2,'CANCELED'),(3,'CLOSED'),(4,'DELIVERY'),(5,'IMPOSSIBLE'),(6,'NEW'),(7,'PROCESSING'),(8,'READY');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CONTACT_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `user_contact_id` (`CONTACT_ID`),
  KEY `user_role_id` (`ROLE_ID`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user_contact_id` FOREIGN KEY (`CONTACT_ID`) REFERENCES `contact` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','test',2,2),(2,'sergeM','sergeM',1,3),(3,'testAdmin','testAdmin',4,1),(4,'delivery_manager','delivery_manager',5,2),(5,'orders_manager','orders_manager',6,3),(6,'orders_processor','orders_processor',7,4),(7,'supervisor','supervisor',8,5);
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

-- Dump completed on 2015-05-04 23:11:42
