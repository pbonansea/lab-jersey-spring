CREATE TABLE IF NOT EXISTS city
(
   id     bigint(5),
   name   varchar(100)
);

Alter table city modify id bigint(5) AUTO_INCREMENT PRIMARY KEY;