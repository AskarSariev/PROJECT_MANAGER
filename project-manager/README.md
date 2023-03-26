# PROJECT MANAGER

<br>
Project Manager is a REST application for project management.
<hr/>

### Tools

![Java 17](https://img.shields.io/badge/-Java17-blue?style=plastic&appveyor)
![Spring](https://img.shields.io/badge/-Spring_Web-success?style=plastic&appveyor)
![Spring](https://img.shields.io/badge/-Spring_Data_JPA-success?style=plastic&appveyor)
![Spring](https://img.shields.io/badge/-Spring_Security-success?style=plastic&appveyor)
![Spring](https://img.shields.io/badge/-H2_Database-9cf?style=plastic&appveyor)
![Thymeleaf](https://img.shields.io/badge/-Validation-red?style=plastic&appveyor)

### Getting started

1. Clone the repo:

       git clone https://github.com/AskarSariev/PROJECT_MANAGER.git

2. Application properties:

       spring.datasource.url=jdbc:h2:tcp://localhost/~/test
       spring.datasource.driverClassName=org.h2.Driver
       spring.datasource.username=sa
       spring.datasource.password=
       spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
       spring.h2.console.enabled=true

3. SQL scripts:

       CREATE TABLE Projects (
            id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(100) NOT NULL,
            parent_project_id INT DEFAULT NULL
       );

       CREATE TABLE Tasks (
            id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(250) NOT NULL,
            status VARCHAR(50) NOT NULL,
            create_date DATE NOT NULL,
            update_date DATE DEFAULT NULL,
            executor VARCHAR(50) DEFAULT NULL,
            project_id INT NOT NULL,
            FOREIGN KEY (project_id) REFERENCES Projects(id)
       );

       CREATE TABLE Users (
            id INT PRIMARY KEY AUTO_INCREMENT,
            username VARCHAR(100) NOT NULL UNIQUE,
            password VARCHAR(200) NOT NULL
       );

       INSERT INTO Projects (name) VALUES ('TestProject_1');
       INSERT INTO Projects (name, parent_project_id) VALUES ('TestProject_2', 1);
       INSERT INTO Projects (name) VALUES ('TestProject_3');

       INSERT INTO Tasks (name, status, create_date, update_date, executor, project_id) VALUES ('TestTask_1', 'NEW', '2023-03-22', NULL, 'MANAGER', 1);
       INSERT INTO Tasks (name, status, create_date, update_date, executor, project_id) VALUES ('TestTask_2', 'PROGRESS', '2023-03-23', '2023-03-24', 'TECH_SPECIALIST', 2);
       INSERT INTO Tasks (name, status, create_date, update_date, executor, project_id) VALUES ('TestTask_3', 'DONE', '2023-03-22', '2023-03-23', 'TECH_SPECIALIST', 3);

4. 
