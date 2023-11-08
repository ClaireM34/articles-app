create table voyages (
  id int not null auto_increment,
  nom VARCHAR(100) not null,
  primary key (id)
);
insert into voyages(nom) values ('Tokyo');
insert into voyages(nom) values ('Paris');
insert into voyages(nom) values ('Londres');
insert into voyages(nom) values ('Casablanca');
