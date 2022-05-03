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

create TABLE team (
    id_team INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
PRIMARY KEY (id_team));

create TABLE person (
    id_person INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    firm VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ROLE_ADMIN', 'ROLE_MENTOR', 'ROLE_STUDENT') NOT NULL,
    PRIMARY KEY (id_person));
)