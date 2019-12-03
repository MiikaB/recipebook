CREATE TABLE recipe(
id int auto_increment not null,
name varchar(30),
ingredients varchar(300),
guide varchar(1000),
primary key(id)
)engine=InnoDB;