<<<<<<< HEAD
DROP DATABASE IF EXISTS `flowerty`;

CREATE DATABASE IF NOT EXISTS `flowerty`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `flowerty`;
    
DROP TABLE IF EXISTS `flowerty`.`address`;

CREATE TABLE `flowerty`.`address` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TOWN` VARCHAR(20) DEFAULT NULL,
  `STREET` VARCHAR(20) DEFAULT NULL,
  `HOUSE` VARCHAR(10) DEFAULT NULL,
  `FLAT` VARCHAR(10) DEFAULT NULL,
  `COUNTRY` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
=======
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
  `COUNTRY` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`,`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`ID`,`TOWN`,`STREET`,`HOUSE`,`FLAT`,`CONTACT_ID`,`COUNTRY`) values (1,'Minsk',NULL,'10',NULL,1,NULL);
insert  into `address`(`ID`,`TOWN`,`STREET`,`HOUSE`,`FLAT`,`CONTACT_ID`,`COUNTRY`) values (2,'Minsk','Kirova','11','12',2,'Belarus');
insert  into `address`(`ID`,`TOWN`,`STREET`,`HOUSE`,`FLAT`,`CONTACT_ID`,`COUNTRY`) values (3,'Moscow','Lermontov','1','2',3,'Russia');

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `WEBSITE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `company` */

insert  into `company`(`ID`,`NAME`,`WEBSITE`) values (1,'f&j','www.fj.com');
insert  into `company`(`ID`,`NAME`,`WEBSITE`) values (2,'your flowers','www.yourflowers.com');

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
  `COMPANY_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`,`NAME`,`ADDRESS_ID`),
  KEY `ADDRESS_ID` (`ADDRESS_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `COMPANY_ID` (`COMPANY_ID`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `contact` */

insert  into `contact`(`ID`,`NAME`,`SURNAME`,`FATHERNAME`,`BIRTHDAY`,`EMAIL`,`ADDRESS_ID`,`USER_ID`,`COMPANY_ID`) values (1,'TestName','TestSurname',NULL,NULL,NULL,1,NULL,1);
insert  into `contact`(`ID`,`NAME`,`SURNAME`,`FATHERNAME`,`BIRTHDAY`,`EMAIL`,`ADDRESS_ID`,`USER_ID`,`COMPANY_ID`) values (2,'Sergey','Sergeev','Sergeevich','1974-06-12','sergey@mail.com',2,NULL,1);

/*Table structure for table `flower` */

DROP TABLE IF EXISTS `flower`;

CREATE TABLE `flower` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `flower` */

insert  into `flower`(`ID`,`NAME`) values (1,'Red Rose');
insert  into `flower`(`ID`,`NAME`) values (2,'White Rose');
insert  into `flower`(`ID`,`NAME`) values (3,'Tea Rose');
insert  into `flower`(`ID`,`NAME`) values (4,'Yellow Tulip');
insert  into `flower`(`ID`,`NAME`) values (5,'Red Tulip');
insert  into `flower`(`ID`,`NAME`) values (6,'Camomile');
insert  into `flower`(`ID`,`NAME`) values (7,'Violet');
insert  into `flower`(`ID`,`NAME`) values (8,'Iris');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PRICE` double unsigned DEFAULT NULL,
  `FLOWER_ID` int(10) unsigned DEFAULT NULL,
  `COMPANY_ID` int(10) unsigned DEFAULT NULL,
  `REMAIN` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FLOWER_ID` (`FLOWER_ID`),
  KEY `COMPANY_ID` (`COMPANY_ID`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`FLOWER_ID`) REFERENCES `flower` (`ID`),
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`ID`,`PRICE`,`FLOWER_ID`,`COMPANY_ID`,`REMAIN`) values (1,10.2,1,1,100);
insert  into `goods`(`ID`,`PRICE`,`FLOWER_ID`,`COMPANY_ID`,`REMAIN`) values (2,12,2,1,50);
insert  into `goods`(`ID`,`PRICE`,`FLOWER_ID`,`COMPANY_ID`,`REMAIN`) values (3,15,3,2,100);
>>>>>>> 804361e4cbbb781065a4f76c633f699035a1edf3

DROP TABLE IF EXISTS `flowerty`.`company`;

<<<<<<< HEAD
CREATE TABLE `flowerty`.`company` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(20) DEFAULT NULL,
  `WEBSITE` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

DROP TABLE IF EXISTS `flowerty`.`contact`;

CREATE TABLE `flowerty`.`contact` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(20) NOT NULL,
  `SURNAME` VARCHAR(20) NOT NULL,
  `FATHERNAME` VARCHAR(20) DEFAULT NULL,
  `BIRTHDAY` DATE DEFAULT NULL,
  `EMAIL` VARCHAR(50) DEFAULT NULL,
  `ADDRESS_ID` INT(10) UNSIGNED NOT NULL,
  `COMPANY_ID` INT(10) UNSIGNED DEFAULT NULL,
  CONSTRAINT `contact_address_id`
  FOREIGN KEY (`ADDRESS_ID` )
  REFERENCES `flowerty`.`address` (`ID` )
  ON DELETE CASCADE
  ON UPDATE RESTRICT,
  CONSTRAINT `contact_company_id`
  FOREIGN KEY (`COMPANY_ID` )
  REFERENCES `flowerty`.`company` (`ID` )
  ON DELETE CASCADE
  ON UPDATE RESTRICT,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;
=======
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

/*Data for the table `item` */

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

/*Data for the table `order` */

/*Table structure for table `order_altering` */

DROP TABLE IF EXISTS `order_altering`;

CREATE TABLE `order_altering` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DATE` timestamp NULL DEFAULT NULL,
  `STATE_ID` int(10) unsigned DEFAULT NULL,
  `USER_ID` int(10) unsigned DEFAULT NULL,
  `COMMENT` varchar(100) DEFAULT NULL,
  `ORDER_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `STATE_ID` (`STATE_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `ORDER_ID` (`ORDER_ID`),
  CONSTRAINT `order_altering_ibfk_1` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`),
  CONSTRAINT `order_altering_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `order_altering_ibfk_3` FOREIGN KEY (`ORDER_ID`) REFERENCES `order` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_altering` */

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

/*Data for the table `phone` */

/*Table structure for table `right` */

DROP TABLE IF EXISTS `right`;

CREATE TABLE `right` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` enum('CREATE_ORDER','CREATE_CONTACT','EDIT_CONTACT','SEARCH_CONTACT','COMMENT_ORDER','SETTINGS','CREATE_USER','DELETE_USER','EDIT_USER','ASSIGN_ROLE','VIEW_ORDERS_READY','VIEW_ORDERS_ACCEPTED','VIEW_ORDERS_ALL') NOT NULL,
  PRIMARY KEY (`ID`,`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `right` */

insert  into `right`(`ID`,`NAME`) values (1,'ASSIGN_ROLE');
insert  into `right`(`ID`,`NAME`) values (2,'COMMENT_ORDER');
insert  into `right`(`ID`,`NAME`) values (3,'CREATE_CONTACT');
insert  into `right`(`ID`,`NAME`) values (4,'CREATE_ORDER');
insert  into `right`(`ID`,`NAME`) values (5,'CREATE_USER');
insert  into `right`(`ID`,`NAME`) values (6,'DELETE_USER');
insert  into `right`(`ID`,`NAME`) values (7,'EDIT_CONTACT');
insert  into `right`(`ID`,`NAME`) values (8,'EDIT_USER');
insert  into `right`(`ID`,`NAME`) values (9,'SEARCH_CONTACT');
insert  into `right`(`ID`,`NAME`) values (10,'SETTINGS');
insert  into `right`(`ID`,`NAME`) values (11,'VIEW_ORDERS_ACCEPTED');
insert  into `right`(`ID`,`NAME`) values (12,'VIEW_ORDERS_READY');
insert  into `right`(`ID`,`NAME`) values (13,'VIEW_ORDERS_ALL');
>>>>>>> 804361e4cbbb781065a4f76c633f699035a1edf3


DROP TABLE IF EXISTS `flowerty`.`flower`;
CREATE TABLE `flowerty`.`flower` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

DROP TABLE IF EXISTS `flowerty`.`goods`;

CREATE TABLE `flowerty`.`goods` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PRICE` DOUBLE UNSIGNED DEFAULT NULL,
  `FLOWER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `COMPANY_ID` INT(10) UNSIGNED DEFAULT NULL,
  `REMAIN` INT(10) UNSIGNED DEFAULT NULL,
   CONSTRAINT `goods_flower_id` FOREIGN KEY (`FLOWER_ID`) REFERENCES `flowerty`.`flower` (`ID`),
  CONSTRAINT `goods_company_id` FOREIGN KEY (`COMPANY_ID`) REFERENCES `flowerty`.`company` (`ID`),
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

<<<<<<< HEAD
=======
CREATE TABLE `role` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` enum('ORDERS_MANAGER','ORDERS_PROCESSOR','DELIVERY_MANAGER','SUPERVISOR','ADMIN') NOT NULL,
  PRIMARY KEY (`ID`,`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`ID`,`NAME`) values (1,'ADMIN');
insert  into `role`(`ID`,`NAME`) values (2,'DELIVERY_MANAGER');
insert  into `role`(`ID`,`NAME`) values (3,'ORDERS_MANAGER');
insert  into `role`(`ID`,`NAME`) values (4,'ORDERS_PROCESSOR');
insert  into `role`(`ID`,`NAME`) values (5,'SUPERVISOR');
>>>>>>> 804361e4cbbb781065a4f76c633f699035a1edf3

DROP TABLE IF EXISTS `flowerty`.`state`;
CREATE TABLE `flowerty`.`state` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DESCRYPTION` ENUM('NEW','ACCEPTED','PROCESSING','READY','DELIVERY','IMPOSSIBLE','CANCELED','CLOSED') DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

DROP TABLE IF EXISTS `flowerty`.`right`;
 CREATE TABLE `flowerty`.`right` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` ENUM('CREATE_ORDER','CREATE_CONTACT','EDIT_CONTACT','SEARCH_CONTACT','COMMENT_ORDER','SETTINGS','CREATE_USER','DELETE_USER','EDIT_USER','ASSIGN_ROLE','VIEW_ORDERS_READY','VIEW_ORDERS_ACCEPTED','VIEW_ORDERS_ALL') NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;
 DROP TABLE IF EXISTS `flowerty`.`role`;
CREATE TABLE `flowerty`.`role` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` ENUM('ORDERS_MANAGER','ORDERS_PROCESSOR','DELIVERY_MANAGER','SUPERVISOR','ADMIN') NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

DROP TABLE IF EXISTS `flowerty`.`role_right`;
CREATE TABLE `flowerty`.`role_right` (
  `ROLE_ID` INT(10) UNSIGNED NOT NULL,
  `RIGHT_ID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RIGHT_ID`),
<<<<<<< HEAD
  CONSTRAINT `role_right_role_id`
    FOREIGN KEY (`ROLE_ID` )
    REFERENCES  `flowerty`.`role` (`ID` )
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `role_right_right_id`
    FOREIGN KEY (`RIGHT_ID`)
    REFERENCES  `flowerty`.`right` (`ID` )
    ON DELETE CASCADE
    ON UPDATE RESTRICT
) ENGINE=INNODB;



DROP TABLE IF EXISTS `flowerty`.`user`;
CREATE TABLE `flowerty`.`user` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `LOGIN` VARCHAR(20) NOT NULL,
  `PASSWORD` VARCHAR(20) NOT NULL,
  `CONTACT_ID` INT(10) UNSIGNED NOT NULL,
  `ROLE_ID` INT(10) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `user_contact_id` FOREIGN KEY (`CONTACT_ID`) REFERENCES `flowerty`.`contact` (`ID`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`ROLE_ID`) REFERENCES `flowerty`.`role` (`ID`)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
) ENGINE=INNODB;

  


DROP TABLE IF EXISTS `flowerty`.`order`;
CREATE TABLE `flowerty`.`order` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `DESCRIPTION` TEXT,
  `MANAGER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `COST` DOUBLE UNSIGNED DEFAULT NULL,
  `STAFF_ID` INT(10) UNSIGNED DEFAULT NULL,
  `DELIVERY_MANAGER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `RECEIVER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `STATE_ID` INT(10) UNSIGNED DEFAULT NULL,
  CONSTRAINT `order_customer_id` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `flowerty`.`contact` (`ID`),
  CONSTRAINT `order_staff_id` FOREIGN KEY (`STAFF_ID`) REFERENCES `flowerty`.`user` (`ID`),
  CONSTRAINT `order_delivery_manager_id` FOREIGN KEY (`DELIVERY_MANAGER_ID`) REFERENCES `flowerty`.`user` (`ID`),
  CONSTRAINT `order_receiver_id` FOREIGN KEY (`RECEIVER_ID`) REFERENCES `flowerty`.`contact` (`ID`),
  CONSTRAINT `order_manager_id` FOREIGN KEY (`MANAGER_ID`) REFERENCES `flowerty`.`user` (`ID`),
  CONSTRAINT `order_state_id` FOREIGN KEY (`STATE_ID`) REFERENCES `flowerty`.`state` (`ID`),
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

DROP TABLE IF EXISTS `flowerty`.`item`;
CREATE TABLE `flowerty`.`item` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `FLOWER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `ORDER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `QUANTITY` INT(10) UNSIGNED DEFAULT NULL,
   CONSTRAINT `item_flower_id` FOREIGN KEY (`FLOWER_ID`) REFERENCES `flowerty`.`flower` (`ID`),
  CONSTRAINT `item_order_id` FOREIGN KEY (`ORDER_ID`) REFERENCES `flowerty`.`order` (`ID`),
  PRIMARY KEY (`ID`)
 ) ENGINE=INNODB;

=======
  KEY `RIGHT_ID` (`RIGHT_ID`),
  CONSTRAINT `role_right_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `role_right_ibfk_2` FOREIGN KEY (`RIGHT_ID`) REFERENCES `right` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `role_right` */

insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (1,1);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (5,2);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (3,3);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (5,3);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (3,4);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (1,5);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (1,6);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (3,7);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (5,7);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (1,8);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (3,9);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (5,9);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (1,10);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (4,11);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (2,12);
insert  into `role_right`(`ROLE_ID`,`RIGHT_ID`) values (5,13);

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DESCRYPTION` enum('NEW','ACCEPTED','PROCESSING','READY','DELIVERY','IMPOSSIBLE','CANCELED','CLOSED') DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `state` */

insert  into `state`(`ID`,`DESCRYPTION`) values (1,'ACCEPTED');
insert  into `state`(`ID`,`DESCRYPTION`) values (2,'CANCELED');
insert  into `state`(`ID`,`DESCRYPTION`) values (3,'CLOSED');
insert  into `state`(`ID`,`DESCRYPTION`) values (4,'DELIVERY');
insert  into `state`(`ID`,`DESCRYPTION`) values (5,'IMPOSSIBLE');
insert  into `state`(`ID`,`DESCRYPTION`) values (6,'NEW');
insert  into `state`(`ID`,`DESCRYPTION`) values (7,'PROCESSING');
insert  into `state`(`ID`,`DESCRYPTION`) values (8,'READY');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`LOGIN`,`PASSWORD`,`CONTACT_ID`,`ROLE_ID`) values (1,'sergeM','sergeM',2,2);
insert  into `user`(`ID`,`LOGIN`,`PASSWORD`,`CONTACT_ID`,`ROLE_ID`) values (2,'test','test',1,3);
>>>>>>> 804361e4cbbb781065a4f76c633f699035a1edf3


DROP TABLE IF EXISTS `flowerty`.`order_altering`;

CREATE TABLE `flowerty`.`order_altering` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DATE` TIMESTAMP NULL DEFAULT NULL,
  `STATE_ID` INT(10) UNSIGNED DEFAULT NULL,
  `USER_ID` INT(10) UNSIGNED DEFAULT NULL,
  `COMMENT` VARCHAR(100) DEFAULT NULL,
  `ORDER_ID` INT(10) UNSIGNED NOT NULL,
  CONSTRAINT `order_altering_state_id` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`ID`),
  CONSTRAINT `order_altering_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `order_altering_order_id` FOREIGN KEY (`ORDER_ID`) REFERENCES `order` (`ID`) ON DELETE CASCADE,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB;

DROP TABLE IF EXISTS `flowerty`.`phone`;

CREATE TABLE `flowerty`.`phone` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `COUNTRY` VARCHAR(5) DEFAULT NULL,
  `OPERATOR` VARCHAR(5) DEFAULT NULL,
  `NUMBER` VARCHAR(10) DEFAULT NULL,
  `TYPE` ENUM('HOME','CELL') DEFAULT NULL,
  `COMMENT` VARCHAR(50) DEFAULT NULL,
  `CONTACT_ID` INT(10) UNSIGNED DEFAULT NULL,
  CONSTRAINT `phone_contact_id` FOREIGN KEY (`CONTACT_ID`) REFERENCES `flowerty`.`contact` (`ID`) ON DELETE CASCADE,
  PRIMARY KEY (`ID`)
 ) ENGINE=INNODB;
 
 
DROP TABLE IF EXISTS `flowerty`.`user_role`;

CREATE TABLE `flowerty`.`user_role` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `ROLE_ID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
<<<<<<< HEAD
  CONSTRAINT `user_role_role_id` FOREIGN KEY (`ROLE_ID`) REFERENCES `flowerty`.`role` (`ID`),
  CONSTRAINT `user_role_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `flowerty`.`user` (`ID`)
) ENGINE=INNODB;

INSERT  INTO `company`(`ID`,`NAME`,`WEBSITE`) VALUES (1,'f&j','www.fj.com');
INSERT  INTO `company`(`ID`,`NAME`,`WEBSITE`) VALUES (2,'your flowers','www.yourflowers.com');



INSERT  INTO `address`(`ID`,`TOWN`,`STREET`,`HOUSE`,`FLAT`,`COUNTRY`) VALUES (1,'Minsk',NULL,'10',NULL, NULL);
INSERT  INTO `address`(`ID`,`TOWN`,`STREET`,`HOUSE`,`FLAT`,`COUNTRY`) VALUES (2,'Minsk','Kirova','11','12','Belarus');
INSERT  INTO `address`(`ID`,`TOWN`,`STREET`,`HOUSE`,`FLAT`,`COUNTRY`) VALUES (3,'Moscow','Lermontov','1','2','Russia');


INSERT  INTO `flower`(`ID`,`NAME`) VALUES (1,'Red Rose');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (2,'White Rose');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (3,'Tea Rose');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (4,'Yellow Tulip');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (5,'Red Tulip');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (6,'Camomile');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (7,'Violet');
INSERT  INTO `flower`(`ID`,`NAME`) VALUES (8,'Iris');


INSERT  INTO `goods`(`ID`,`PRICE`,`FLOWER_ID`,`COMPANY_ID`,`REMAIN`) VALUES (1,10.2,1,1,100);
INSERT  INTO `goods`(`ID`,`PRICE`,`FLOWER_ID`,`COMPANY_ID`,`REMAIN`) VALUES (2,12,2,1,50);
INSERT  INTO `goods`(`ID`,`PRICE`,`FLOWER_ID`,`COMPANY_ID`,`REMAIN`) VALUES (3,15,3,2,100);

INSERT  INTO `right`(`ID`,`NAME`) VALUES (1,'ASSIGN_ROLE');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (2,'COMMENT_ORDER');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (3,'CREATE_CONTACT');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (4,'CREATE_ORDER');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (5,'CREATE_USER');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (6,'DELETE_USER');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (7,'EDIT_CONTACT');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (8,'EDIT_USER');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (9,'SEARCH_CONTACT');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (10,'SETTINGS');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (11,'VIEW_ORDERS_ACCEPTED');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (12,'VIEW_ORDERS_READY');
INSERT  INTO `right`(`ID`,`NAME`) VALUES (13,'VIEW_ORDERS_ALL');


INSERT  INTO `role`(`ID`,`NAME`) VALUES (1,'ADMIN');
INSERT  INTO `role`(`ID`,`NAME`) VALUES (2,'DELIVERY_MANAGER');
INSERT  INTO `role`(`ID`,`NAME`) VALUES (3,'ORDERS_MANAGER');
INSERT  INTO `role`(`ID`,`NAME`) VALUES (4,'ORDERS_PROCESSOR');
INSERT  INTO `role`(`ID`,`NAME`) VALUES (5,'SUPERVISOR');

INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (1,1);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (5,2);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (3,3);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (5,3);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (3,4);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (1,5);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (1,6);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (3,7);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (5,7);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (1,8);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (3,9);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (5,9);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (1,10);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (4,11);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (2,12);
INSERT  INTO `role_right`(`ROLE_ID`,`RIGHT_ID`) VALUES (5,13);

INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (1,'ACCEPTED');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (2,'CANCELED');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (3,'CLOSED');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (4,'DELIVERY');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (5,'IMPOSSIBLE');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (6,'NEW');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (7,'PROCESSING');
INSERT  INTO `state`(`ID`,`DESCRYPTION`) VALUES (8,'READY');

INSERT  INTO `contact`(`ID`,`NAME`,`SURNAME`,`FATHERNAME`,`BIRTHDAY`,`EMAIL`,`ADDRESS_ID`,`COMPANY_ID`) VALUES (1,'TestName','TestSurname',NULL,NULL,NULL,1,1);
INSERT  INTO `contact`(`ID`,`NAME`,`SURNAME`,`FATHERNAME`,`BIRTHDAY`,`EMAIL`,`ADDRESS_ID`,`COMPANY_ID`) VALUES (2,'Sergey','Sergeev','Sergeevich','1974-06-12','sergey@mail.com',2,1);


INSERT  INTO `user`(`ID`,`LOGIN`,`PASSWORD`,`CONTACT_ID`,`ROLE_ID`) VALUES (1,'sergeM','sergeM',2,2);
INSERT  INTO `user`(`ID`,`LOGIN`,`PASSWORD`,`CONTACT_ID`,`ROLE_ID`) VALUES (2,'test','test',1,3);
=======
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
>>>>>>> 804361e4cbbb781065a4f76c633f699035a1edf3
