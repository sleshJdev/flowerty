/*
SQLyog Community v12.08 (64 bit)
MySQL - 5.5.25 : Database - flowerty
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`flowerty` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TOWN` varchar(20) DEFAULT NULL,
  `STREET` varchar(20) DEFAULT NULL,
  `HOUSE` varchar(10) DEFAULT NULL,
  `FLAT` varchar(10) DEFAULT NULL,
  `CONTACT_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`,`CONTACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `SURNAME` varchar(20) DEFAULT NULL,
  `FATHERNAME` varchar(20) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS_ID` int(10) unsigned NOT NULL,
  `USER_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`,`NAME`,`ADDRESS_ID`),
  KEY `ADDRESS_ID` (`ADDRESS_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `flower` */

DROP TABLE IF EXISTS `flower`;

CREATE TABLE `flower` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `COST` double unsigned DEFAULT NULL,
  `REMAIN` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FLOWER_ID` int(10) unsigned DEFAULT NULL,
  `ORDER_ID` int(10) unsigned DEFAULT NULL,
  `QUANTITY` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FLOWER_ID` (`FLOWER_ID`),
  KEY `ORDER_ID` (`ORDER_ID`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`FLOWER_ID`) REFERENCES `flower` (`ID`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(10) unsigned DEFAULT NULL,
  `DESCRIPTION` text,
  `MANAGER_ID` int(10) unsigned DEFAULT NULL,
  `COST` double unsigned DEFAULT NULL,
  `STAFF_ID` int(10) unsigned DEFAULT NULL,
  `DELIVERY_MANAGER_ID` int(10) unsigned DEFAULT NULL,
  `RECEIVER_ID` int(10) unsigned DEFAULT NULL,
  `STATE_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  KEY `STAFF_ID` (`STAFF_ID`),
  KEY `DELIVERY_MANAGER_ID` (`DELIVERY_MANAGER_ID`),
  KEY `RECEIVER_ID` (`RECEIVER_ID`),
  KEY `MANAGER_ID` (`MANAGER_ID`),
  KEY `STATE_ID` (`STATE_ID`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `contact` (`ID`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`STAFF_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`DELIVERY_MANAGER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `order_ibfk_4` FOREIGN KEY (`RECEIVER_ID`) REFERENCES `contact` (`ID`),
  CONSTRAINT `order_ibfk_5` FOREIGN KEY (`MANAGER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `order_ibfk_6` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `order_altering` */

DROP TABLE IF EXISTS `order_altering`;

CREATE TABLE `order_altering` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `STATE_ID` int(10) unsigned DEFAULT NULL,
  `USER_ID` int(10) unsigned DEFAULT NULL,
  `COMMENT` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `STATE_ID` (`STATE_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `order_altering_ibfk_1` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`),
  CONSTRAINT `order_altering_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `phone` */

DROP TABLE IF EXISTS `phone`;

CREATE TABLE `phone` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `COUNTRY` varchar(5) DEFAULT NULL,
  `OPERATOR` varchar(5) DEFAULT NULL,
  `NUMBER` varchar(10) DEFAULT NULL,
  `TYPE` enum('HOME','CELL') DEFAULT NULL,
  `COMMENT` varchar(50) DEFAULT NULL,
  `CONTACT_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CONTACT_ID` (`CONTACT_ID`),
  CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`CONTACT_ID`) REFERENCES `contact` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `right` */

DROP TABLE IF EXISTS `right`;

CREATE TABLE `right` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` enum('CREATE_ORDER','CREATE_CONTACT','EDIT_CONTACT','SEARCH_CONTACT','VIEW_ORDERS','COMMENT_ORDER','SETTINGS','CREATE_USER','DELETE_USER','EDIT_USER','ASSIGN_ROLE') NOT NULL,
  PRIMARY KEY (`ID`,`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` enum('ORDERS_MANAGER','ORDERS_PROCESSOR','DELIVERY_MANAGER','SUPERVISOR','ADMIN') NOT NULL,
  PRIMARY KEY (`ID`,`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role_right` */

DROP TABLE IF EXISTS `role_right`;

CREATE TABLE `role_right` (
  `ROLE_ID` int(10) unsigned NOT NULL,
  `RIGHT_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RIGHT_ID`),
  KEY `RIGHT_ID` (`RIGHT_ID`),
  CONSTRAINT `role_right_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `role_right_ibfk_2` FOREIGN KEY (`RIGHT_ID`) REFERENCES `right` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DESCRYPTION` enum('NEW','ACCEPTED','PROCESSING','READY','DELIVERY','IMPOSSIBLE','CANCELED','CLOSED') DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `CONTACT_ID` int(10) unsigned NOT NULL,
  `ROLE_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`,`LOGIN`,`PASSWORD`,`CONTACT_ID`),
  KEY `CONTACT_ID` (`CONTACT_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`CONTACT_ID`) REFERENCES `contact` (`ID`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `USER_ID` int(10) unsigned NOT NULL,
  `ROLE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
