-- bida_management.DATABASECHANGELOG definition

CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.DATABASECHANGELOGLOCK definition

CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_authority definition

CREATE TABLE `tbl_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_billiards definition

CREATE TABLE `tbl_billiards` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `price` double NOT NULL,
  `state` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_customer definition

CREATE TABLE `tbl_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `phone_number` varchar(50) NOT NULL,
  `date_of_birth` timestamp NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_customer_phone_number_uindex` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_employee definition

CREATE TABLE `tbl_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `identity_card_number` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_product definition

CREATE TABLE `tbl_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_provider definition

CREATE TABLE `tbl_provider` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_employee_authority definition

CREATE TABLE `tbl_employee_authority` (
  `user_id` bigint NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `tbl_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_history_billiards definition

CREATE TABLE `tbl_history_billiards` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `start_time_active` timestamp NOT NULL,
  `end_time_active` timestamp NULL DEFAULT NULL,
  `billiards_id` bigint NOT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tbl_history_billibards_tbl_billiards_id_fk` (`billiards_id`),
  KEY `tbl_history_billibards_tbl_employee_id_fk` (`employee_id`),
  CONSTRAINT `tbl_history_billibards_tbl_billiards_id_fk` FOREIGN KEY (`billiards_id`) REFERENCES `tbl_billiards` (`id`),
  CONSTRAINT `tbl_history_billibards_tbl_employee_id_fk` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_history_product definition

CREATE TABLE `tbl_history_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` double NOT NULL,
  `receive_date` datetime NOT NULL,
  `employee_id` bigint NOT NULL,
  `provider_id` bigint DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `tbl_history_product_tbl_employee_id_fk` (`employee_id`),
  KEY `tbl_history_product_tbl_provider_id_fk` (`provider_id`),
  CONSTRAINT `tbl_history_product_tbl_employee_id_fk` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`),
  CONSTRAINT `tbl_history_product_tbl_provider_id_fk` FOREIGN KEY (`provider_id`) REFERENCES `tbl_provider` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- bida_management.tbl_bill definition

CREATE TABLE `tbl_bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) DEFAULT NULL,
  `customer_phone_number` varchar(50) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `total_price` double NOT NULL,
  `history_billiards_id` bigint NOT NULL,
  `employee_id` bigint NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tbl_bill_tbl_customer_id_fk` (`customer_id`),
  KEY `tbl_bill_tbl_employee_id_fk` (`employee_id`),
  KEY `tbl_bill_tbl_history_billiards_id_fk` (`history_billiards_id`),
  KEY `tbl_bill_tbl_product_id_fk` (`product_id`),
  CONSTRAINT `tbl_bill_tbl_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`id`),
  CONSTRAINT `tbl_bill_tbl_employee_id_fk` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`),
  CONSTRAINT `tbl_bill_tbl_history_billiards_id_fk` FOREIGN KEY (`history_billiards_id`) REFERENCES `tbl_history_billiards` (`id`),
  CONSTRAINT `tbl_bill_tbl_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- insert data initial

INSERT INTO bida_management.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, EXECTYPE, MD5SUM, DESCRIPTION, COMMENTS, TAG, LIQUIBASE, CONTEXTS, LABELS, DEPLOYMENT_ID)
VALUES ('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2022-01-15 16:23:58', 1, 'EXECUTED', '8:46d889d3454217aee205917d68e060b9', 'createTable tableName=tbl_employee; createTable tableName=tbl_authority; createTable tableName=tbl_employee_authority; addPrimaryKey tableName=tbl_employee_authority; addForeignKeyConstraint baseTableName=tbl_employee_authority, constraintName=fk_...', '', null, '4.5.0', null, null, '2238638076');

INSERT INTO bida_management.tbl_employee (id, login, password_hash, first_name, last_name, phone_number, identity_card_number, address, salary, position, start_date, end_date, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) VALUES (1, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', '0763554653', '0637374674833', 'Xom1', null, '', null, null, 'admin@localhost', '', true, 'vi', null, null, 'system', null, null, 'admin', '2022-01-23 03:38:03');
INSERT INTO bida_management.tbl_employee (id, login, password_hash, first_name, last_name, phone_number, identity_card_number, address, salary, position, start_date, end_date, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date) VALUES (2, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', '0763554653', '0637374673412', 'Xom1', null, '', null, null, 'user@localhost', '', true, 'en', null, null, 'system', null, null, 'system', null);

INSERT INTO bida_management.tbl_authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO bida_management.tbl_authority (name) VALUES ('ROLE_USER');

INSERT INTO bida_management.tbl_employee_authority (user_id, authority_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO bida_management.tbl_employee_authority (user_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO bida_management.tbl_employee_authority (user_id, authority_name) VALUES (2, 'ROLE_USER');
