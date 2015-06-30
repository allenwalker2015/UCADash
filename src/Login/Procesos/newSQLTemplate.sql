DROP DATABASE IF EXISTS ucadash;

create database ucadash;

use ucadash;

create table USUARIO
(IdUsuario int not null AUTO_INCREMENT, 
NomUsuario varchar(25), 
PassUsuario varchar (25),
Score int,
Dificultad int,
IdNivel int,
 PRIMARY KEY (IdUsuario)
);

create table NIVEL
(IdNivel int not null,
NumNIvel int not null,
PRIMARY KEY (IdNivel)
);

ALTER TABLE USUARIO ADD
CONSTRAINT FK_USUARIO_NIVEL
FOREIGN KEY (IdNivel)
REFERENCES NIVEL (IdNivel);


INSERT INTO NIVEL (IdNivel, NumNIvel)
values (1,1), (2,2),(3,3),(4,4);

INSERT INTO USUARIO(NomUsuario, PassUsuario, Score, Dificultad, IdNivel)
values ("uca","uca",2,1,1),("fuck", "fuck", 1,1,2);


