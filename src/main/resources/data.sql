DROP table if EXISTS users;
DROP table if EXISTS projects;
DROP table if EXISTS tasks;

CREATE TABLE users (
                       id int PRIMARY KEY AUTO_INCREMENT,
                       username varchar(255),
                       password varchar(255)
);

CREATE TABLE projects (
                       id int PRIMARY KEY AUTO_INCREMENT,
                       title varchar(255),
                       description varchar(255),
                       author varchar(255),
                       parent_id int
);

CREATE TABLE tasks (
                          id int PRIMARY KEY AUTO_INCREMENT,
                          title varchar(255),
                          task_type enum('TECH','MANAGER'),
                          status enum('NEW','PROGRESS','DONE'),
                          author varchar(255),
                          date_created date,
                          date_updated date,
                          description varchar(255),
                          project_id int
);

INSERT INTO users (username, password) VALUES ('user', 'password');
INSERT INTO users (username, password) VALUES ('admin', 'password');

INSERT INTO projects (title, description, author, parent_id)
VALUES ('Create website','Website for new company' ,'admin', 0);

INSERT INTO projects (title, description, author, parent_id)
VALUES ('Create frontend','Angular' ,'admin', 1);

INSERT INTO projects (title, description, author, parent_id)
VALUES ('Create backend','Spring Boot' ,'admin', 1);

INSERT INTO tasks (title, task_type, status, author, date_created, date_updated, description, project_id)
VALUES ('Get requirements from client', 'MANAGER','NEW','admin',CURRENT_DATE() ,CURRENT_DATE() ,'Payments, docs, etc',1);

INSERT INTO tasks (title, task_type, status, author, date_created, date_updated, description, project_id)
VALUES ('Development', 'TECH','NEW','admin',CURRENT_DATE() ,CURRENT_DATE() ,'New task for tech team',1);