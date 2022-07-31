CREATE DATABASE country;
CREATE DATABASE `country-test`;
CREATE USER 'demo'@'%' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON country.* TO 'demo'@'%';
GRANT ALL PRIVILEGES ON `country-test`.* TO 'demo'@'%';
FLUSH PRIVILEGES;
USE `country`;
CREATE TABLE `countries` (
                             `id` SMALLINT NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) NOT NULL,
                             `population` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UK_1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
USE `country`;
BEGIN;
INSERT INTO `countries` (`id`, `name`, `population`) VALUES
(1, 'Mexico', 130497248),
(2, 'Spain', 49067981),
(3, 'Colombia', 46070146);

COMMIT;

