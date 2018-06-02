CREATE DATABASE fridge;
USE fridge;

DROP USER 'fridge'@'localhost'; 
CREATE USER 'fridge'@'localhost' IDENTIFIED BY 'fridge'; 
GRANT ALL ON fridge.* TO 'fridge'@'localhost';

