CREATE TABLE `billing` (
  `bill_no` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `billing_date` date NOT NULL,
  `billing_time` time NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `bill_status` boolean DEFAULT (true),
  PRIMARY KEY (`bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `billing_details` (
  `billing_detail_id` int NOT NULL AUTO_INCREMENT,
  `bill_no` int DEFAULT NULL,
  `dish_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `billing_detail__status` boolean DEFAULT (true),
  PRIMARY KEY (`billing_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dish` (
  `dish_id` int NOT NULL AUTO_INCREMENT,
  `dish_name` varchar(255) NOT NULL,
  `dish_imagepath` varchar(255) DEFAULT NULL,
  `dish_price` float NOT NULL,
  `dish_status` tinyint(1) DEFAULT (true),
  PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `employee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `email_address` varchar(255) NOT NULL,
  `employee_password` varchar(255) NOT NULL,
  `employee_status` boolean DEFAULT (true),
  `employee_mobile` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `employee_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `email_address` (`email_address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;