DROP TABLE IF EXISTS `access`;
CREATE TABLE `access` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` enum('create_order','contact','comment_order','settings','user','assign_role','orders_ready','orders_accepted','orders_all') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `access` VALUES (1,'create_order'),(2,'contact'),(3,'comment_order'),(4,'settings'),(5,'user'),(6,'assign_role'),(7,'orders_ready'),(8,'orders_accepted'),(9,'orders_all');


DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TOWN` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STREET` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HOUSE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FLAT` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COUNTRY` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `address` VALUES (1,'Minsk','Kirova','10','11','Belarus'),(2,'Moscow','Lermontova','1','2','Russia'),(3,'London','Orange','12','34','GB'),(4,'Minsk','Pulikhova','90','19','Belarus'),(5,'Moscow','Pushkina','10','20','Russia'),(6,'London','Green','11','33','GB'),(7,'Minsk','Independence','11','111','Belarus'),(8,'Moscow','Lermontova','6','2','Russia'),(9,'London','Queen','12','34','GB'),(10,'Minsk','Kirova','80','119','Belarus'),(11,'Moscow','Lermontova','10','20','Russia'),(12,'London','Charles','12','34','GB'),(13,'London','Orange','112','314','GB');

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WEBSITE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `company` VALUES (1,'FandJ','www.FandJ.com'),(2,'FlowersForYou','www.FlowersForYou.com'),(3,'GoodPresents','www.GoodPresents.com');

DROP TABLE IF EXISTS `contact`;
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

INSERT INTO `contact` VALUES (1,'Anton','Antonov','Antonovich','1990-12-03','anton@mail.com',1,1),(2,'Ivan','Ivanov','Ivanovich','1990-10-04','ivanov@mail.com',2,1),(3,'Sergey','Sergeew','Sergeewitch',NULL,NULL,3,1),(4,'Helen','Ivanova','Ivanovna','1990-12-03',NULL,4,2),(5,'John','Brown',NULL,'1990-12-03','john@mail.com',5,3),(6,'Chuck','Brown',NULL,'1990-12-03','chuck@mail.com',6,1),(7,'Alex','Sidorov','Alexandrovich','1990-12-03','alex@mail.com',7,2),(8,'Boris','Borisov','Borisovich',NULL,NULL,8,3),(9,'Jack','Black',NULL,'1989-01-03','black@mail.com',9,1),(10,'Sarah','Brown',NULL,'1990-12-03','sarahn@mail.com',10,2),(11,'Olga','Sergeeva','Sergeevna','1980-10-06','sergeeva80@mail.com',11,3),(12,'Olga','Antonova','Antonovna','1992-11-04','olga@mail.com',1,1);
