CREATE DATABASE `idus-hw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `member` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  UNIQUE KEY `UK_hh9kg6jti4n1eoiertn2k6qsc` (`nickname`),
  UNIQUE KEY `UK_6ithqvsvrcawbi9dtxu0ttsny` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `member_idx` int NOT NULL,
  `order_no` varchar(12) NOT NULL,
  `order_time` datetime NOT NULL,
  `product_name` varchar(100) NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FKlfhcj9p90v72yde567mr20psb` (`member_idx`),
  CONSTRAINT `FKlfhcj9p90v72yde567mr20psb` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
