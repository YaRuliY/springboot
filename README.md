# springboot
Spring Boot + JPA + Security<br>
CREATE TABLE maindb.user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fio VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE maindb.record
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    email VARCHAR(255),
    hometel VARCHAR(255),
    name VARCHAR(255),
    patronymic VARCHAR(255),
    surname VARCHAR(255),
    telephone VARCHAR(20),
    user_id INT NOT NULL
);
