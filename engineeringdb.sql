create database if not exists  engineeringdb;

use engineeringdb;

drop table if exists engineers;
drop table if exists city;
drop table if exists engineerings;
drop table if exists services;
drop table if exists orders;
drop table if exists partner;

CREATE TABLE engineers (engineerId INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                    firstName VARCHAR(12),
                    lastNname VARCHAR(12),
                    phone VARCHAR(14),
                    email VARCHAR(15),
                    zipcode INT(11) REFERENCES city(zipcode) ON UPDATE CASCADE,
                    street VARCHAR(15),
                    houseNumber INT(11));
                    
CREATE TABLE city (zipcode INT(11) PRIMARY KEY,
                       oneCity VARCHAR(15));
                       
CREATE TABLE engineerings (engineeringId INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                  engineerId INT(11) REFERENCES engineers(engineerId),
                  serviceId INT(11) REFERENCES services(serviceId));

CREATE TABLE services (serviceId INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          type VARCHAR(20),
                          price INT(11),
                          description VARCHAR(50));

CREATE TABLE orders (orderId INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         serviceId INT(11) REFERENCES services(serviceId),
                         taxNumber VARCHAR(15) REFERENCES partner(taxNumber),
                         status ENUM('arajanlat','megrendelve','lezarva','szamlazva'),
                         startDate DATE,
                         endDate DATE);   

CREATE TABLE partner (taxNumber VARCHAR(15) NOT NULL PRIMARY KEY,
                      name VARCHAR(20),
                      address VARCHAR(60),
                      accountNumber VARCHAR(30),
                      phone VARCHAR(24),
                      email VARCHAR(25));
					  


INSERT INTO city VALUES(5900,'Orosháza');
INSERT INTO engineers VALUES(1, 'Kiss','József','06302558965','jkiss@valami.hu',9500,'Tass utca', 28);
INSERT INTO engineerings VALUES(1,1,1);
INSERT INTO services VALUES (1,'Tervezés',58000,'Irányítás technikai rendster megtervezése');
INSERT INTO orders VALUES (1,1,'55685-4578999','arajanlat','2016-01-28', '2016-05-22');
INSERT INTO partner VALUES ('55685-4578999','Kazszer KFT.','Orosáza Vásárhelyi út 88.','HU54854-58332455','0668474958','kazsszer@gmail.com');

