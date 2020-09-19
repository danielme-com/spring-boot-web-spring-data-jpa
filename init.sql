CREATE DATABASE country;
CREATE DATABASE `country-test`;
CREATE USER 'demo'@'%' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON country.* TO 'demo'@'%';
GRANT ALL PRIVILEGES ON `country-test`.* TO 'demo'@'%';
FLUSH PRIVILEGES;
