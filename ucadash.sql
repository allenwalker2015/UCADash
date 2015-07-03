DROP DATABASE IF EXISTS ucadash;

create database ucadash;

use ucadash;

create table usuario(
NomUsuario varchar(25), 
PassUsuario varchar (25),
Score1 int,
Score2 int,
Score3 int,
Score4 int,
 PRIMARY KEY (NomUsuario)
);

INSERT INTO usuario(NomUsuario, PassUsuario, Score1, Score2, Score3, Score4)
		    values ("uca","uca",1,2,3,4),
                           ("fuck","fuck",4,3,2,1),
                           ("booby","bobby",3,6,4,3),
                           ("hot","hot",3,4,6,5),
                           ("winniepofe","pofepoo",2,4,7,2);

