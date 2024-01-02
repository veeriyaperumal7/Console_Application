CREATE DATABASE `role_hierarchy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL,
  `employee_name` varchar(250) DEFAULT NULL,
  `reporting_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `employee_status` enum('AVAILABLE','NOT AVAILABLE') DEFAULT 'AVAILABLE',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reporting` (
  `reporting_id` int NOT NULL AUTO_INCREMENT,
  `reporting_manager_role_id` int DEFAULT NULL,
  `reporting_role_id` int DEFAULT NULL,
  `report_status` enum('AVAILABLE','NOT AVAILABLE') DEFAULT 'AVAILABLE',
  PRIMARY KEY (`reporting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `role_table` (
  `role_id` int NOT NULL,
  `role_name` varchar(250) DEFAULT NULL,
  `role_status` enum('AVAILABLE','NOT AVAILABLE') DEFAULT 'AVAILABLE',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
