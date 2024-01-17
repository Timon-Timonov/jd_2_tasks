CREATE DATABASE People;
USE People;

CREATE TABLE Person
(
    id             int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    age            int,
    salary         dec,
    passport       char(10),
    address        varchar(200),
    dateOfBirthday date,
    dateTimeCreate timestamp DEFAULT CURRENT_TIMESTAMP,
    timeToLunch    time,
    letter         text
);

INSERT INTO Person (age, salary, passport, address, dateOfBirthday, timeToLunch, letter)
VALUES (12, 0.1, NULL, 'Jodino, Stroitelei str. 4-15', '2011-05-15', '12:00:00',
        'It is a littel chaild'),
       (75, 3345.8, 'MC98756895', 'Minsk, Nezavisimosti ave. 44-155', '1948-10-13', '13:30:00',
        'It is an old man'),
       (18, 500.5, 'MP78541268', 'San-Francisco, Liberti str. 15', '2005-09-19', '14:20:00',
        'It is a student'),
       (22, 875.9, 'DD85219201', 'London, Allenclusive distr., 334-2', '2003-02-08', '13:40:00',
        'It is a ypung girl'),
       (19, 600, 'MK55998741', 'Minsk, Surganova str., 56-79', '2004-07-29', '12:45:00',
        'It is a teenager'),
       (31, 1800.45, 'DP54100120', 'Brest, Druzjbi ave., 67-A-55', '1992-11-01', '13:00:00',
        'It is a strong man');

SELECT *
FROM Person
WHERE age > 21
ORDER BY dateTimeCreate;