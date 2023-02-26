
create TABLE person (
    id_person INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    firm VARCHAR(150),
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ROLE_ADMIN', 'ROLE_MENTOR', 'ROLE_STUDENT') NOT NULL,
    assigned_team_id INT,
    PRIMARY KEY (id_person));

create TABLE team (
    id_team INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    choices TEXT,
    create_at DATETIME NOT NULL,
    assigned_mentor_id INT,
    PRIMARY KEY (id_team),
    CONSTRAINT team_person_fk
        FOREIGN KEY (assigned_mentor_id)
        REFERENCES team.person (id_person));

create TABLE project (
    id_project INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    id_owner INT NOT NULL,
    PRIMARY KEY (id_project),
    CONSTRAINT project_person_fk
        FOREIGN KEY (id_owner)
        REFERENCES team.person (id_person));

ALTER TABLE person ADD CONSTRAINT person_team_fk FOREIGN KEY (assigned_team_id) REFERENCES team.team (id_team);