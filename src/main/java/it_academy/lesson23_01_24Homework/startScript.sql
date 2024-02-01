CREATE DATABASE building;
USE building;

CREATE TABLE building.door
(
    id   int auto_increment primary key,
    size dec(10, 3),
    type varchar(100)
);

CREATE TABLE building.house
(
    id   int auto_increment primary key,
    size dec(10, 3),
    color varchar(100)
);

INSERT INTO building.door (size, type) VALUE (10.35,'wood');
INSERT INTO building.door (size, type) VALUE (15.15,'steel');
INSERT INTO building.door (size, type) VALUE (587.2,'iron');

INSERT INTO building.house(size, color) VALUE (255.5,'green');
INSERT INTO building.house(size, color) VALUE (500.54,'red');
INSERT INTO building.house(size, color) VALUE (238.65,'grey');