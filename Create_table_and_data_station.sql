DROP TABLE IF EXISTS station;
CREATE TABLE station (  id int auto_increment primary key, name varchar(255) null);
INSERT INTO station (id, name)
VALUES (1, 'Astapor'), (2, 'Meereen'), (3, 'Haigarden'), (4, 'Kings Landing'), (5, 'Winterfell'),
       (6, 'Qarth'), (7, 'Bravoos'), (8, 'Volantis'), (9, 'Old Valyria'), (10, 'Yunkai'), (11, 'White Harbor');