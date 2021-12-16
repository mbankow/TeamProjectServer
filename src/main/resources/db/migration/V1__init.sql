create TABLE mentor (
    id_mentor INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    firm VARCHAR(100) NOT NULL,
    email VARCHAR(100) NULL,
    password VARCHAR(100) NOT NULL,
PRIMARY KEY (id_mentor));

create TABLE project (
    id_project INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
PRIMARY KEY (id_project));

create TABLE student (
    id_student INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
PRIMARY KEY (id_student));

create TABLE team (
    id_team INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
PRIMARY KEY (id_team));