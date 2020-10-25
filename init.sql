CREATE DATABASE country;
CREATE DATABASE `country-test`;
CREATE USER 'demo'@'%' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON country.* TO 'demo'@'%';
GRANT ALL PRIVILEGES ON `country-test`.* TO 'demo'@'%';
FLUSH PRIVILEGES;
USE `country`;
CREATE TABLE `countries` (
                             `id` SMALLINT NOT NULL AUTO_INCREMENT,
                             `create_by` varchar(255) NOT NULL,
                             `created_date` datetime NOT NULL,
                             `last_modified_by` varchar(255) DEFAULT NULL,
                             `last_modified_date` datetime NOT NULL,
                             `name` varchar(255) NOT NULL,
                             `population` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UK_1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
USE `country`;
BEGIN;
INSERT INTO `countries` (`id`, `create_by`, `created_date`, `last_modified_by`, `last_modified_date`, `name`, `population`) VALUES
(1, 'test', '2018-11-17 18:50:06', 'test', '2018-11-17 18:50:10', 'Mexico', 130497248),
(2, 'test', '2018-11-17 18:50:06', 'test', '2018-11-17 18:50:10', 'Spain', 49067981),
(3, 'test', '2018-11-17 18:50:06', 'test', '2018-11-17 18:50:10', 'Colombia', 46070146);

COMMIT;

