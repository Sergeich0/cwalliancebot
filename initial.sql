CREATE DATABASE alliance_bot;
USE alliance_bot;

CREATE TABLE `alliance_point_of_interest` (
                                              `active` bit(1) NOT NULL,
                                              `level` int DEFAULT NULL,
                                              `id` bigint NOT NULL AUTO_INCREMENT,
                                              `code` varchar(255) NOT NULL,
                                              `name` varchar(255) NOT NULL,
                                              `type` varchar(255) NOT NULL,
                                              PRIMARY KEY (`id`),
                                              UNIQUE KEY `UK_sx94lxsft60we8w22f7dgknlh` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `username` varchar(50) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         `enabled` tinyint NOT NULL DEFAULT '1',
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `authorities` (
                               `username` varchar(50) NOT NULL,
                               `authority` varchar(50) NOT NULL,
                               UNIQUE KEY `ix_auth_username` (`username`,`authority`),
                               CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
