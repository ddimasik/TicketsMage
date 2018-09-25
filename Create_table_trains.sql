drop table if exists trains;

create table trains
(
  id int auto_increment
    primary key,
  capacity int null,
  name varchar(255) null,
  startDateTime tinyblob null,
  startSt int null
);
