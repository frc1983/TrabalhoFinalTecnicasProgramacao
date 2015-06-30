CREATE TABLE Usuario (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IdTipoUsuario int not null,
Nome varchar(255) not null,
CpfCnpj varchar(255) unique not null,
Email varchar(255) not null
);

CREATE TABLE TipoUsuario (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Tipo varchar(255) not null
);

CREATE TABLE Leilao (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IdUsuario int not null,
IdLote int not null,
IdNatureza int not null,
IdFormaLance int not null,
DataInicio date not null,
DataTermino date not null,
HoraInicio time not null,
HoraTermino time not null,
FOREIGN KEY(IdUsuario) REFERENCES Usuario (Id)
);

CREATE TABLE Natureza (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Nome varchar(255) not null
);

CREATE TABLE FormaLance (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Forma varchar(255) not null
);

CREATE TABLE Lote (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Preco decimal(10,2) not null
);

CREATE TABLE CategoriaBem (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Categoria varchar(255) not null
);

CREATE TABLE Bem (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IdCategoriaBem int not null,
Descricao varchar(255) not null,
DescricaoCompleta long varchar not null,
FOREIGN KEY(IdCategoriaBem) REFERENCES CategoriaBem (Id)
);

CREATE TABLE Lance (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IdUsuario int not null,
IdLote int not null,
Data date not null,
Hora Time not null,
Valor decimal(10,2) not null,
FOREIGN KEY(IdUsuario) REFERENCES Usuario (Id),
FOREIGN KEY(IdLote) REFERENCES Lote (Id)
);

CREATE TABLE Lote_Bem (
IdLote int,
IdBem int,
FOREIGN KEY(IdLote) REFERENCES Lote (Id),
FOREIGN KEY(IdBem) REFERENCES Bem (Id)
);

ALTER TABLE Leilao ADD FOREIGN KEY(IdLote) REFERENCES Lote (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdNatureza) REFERENCES Natureza (Id);


ALTER TABLE Usuario ADD FOREIGN KEY(IdTipoUsuario) REFERENCES TipoUsuario (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdLote) REFERENCES Lote (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdNatureza) REFERENCES Natureza (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdFormaLance) REFERENCES FormaLance (Id);
ALTER TABLE Bem ADD FOREIGN KEY(IdCategoriaBem) REFERENCES CategoriaBem (Id);



--INSERTS
insert into tipousuario VALUES (default, 'COMPRADOR');
insert into tipousuario VALUES (default, 'VENDEDOR');

insert into Natureza VALUES (default, 'OFERTA');
insert into Natureza VALUES (default, 'DEMANDA');

insert into FormaLance VALUES (default, 'ABERTO');
insert into FormaLance VALUES (default, 'FECHADO');

insert into CategoriaBem VALUES (default, 'Mobiliário');
insert into CategoriaBem VALUES (default, 'Informática');
insert into CategoriaBem VALUES (default, 'Máquinas industriais');
insert into CategoriaBem VALUES (default, 'Máquinas de escritório');
insert into CategoriaBem VALUES (default, 'Equipamentos hospitalares');

insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Mobiliário'), 'Estante de madeira', 'Estante de madeira com 4 prateleiras em mogno escuro');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Mobiliário'), 'Estante de aço', 'Estante de aço escovado');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Informática'), 'Notebook 3d', 'Positivo 3d com óculos dos anos 1990.');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Equipamentos hospitalares'), 'Scanner cerebral', 'Scanner cerebral para ver se tem algo dentro.');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Informática'), 'Desktop DELL', 'PC Desktop DELL 4gb RAM i5.');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Máquinas de escritório'), 'Estante de aço', 'Estante de aço escovado.');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Máquinas de escritório'), 'Grampeador', 'Grampeador simples.');
insert into Bem VALUES (default, (select ID from CATEGORIABEM where CATEGORIA = 'Máquinas de escritório'), 'Máquina de escrever', 'Máquina de escrever elétrica.');

insert into USUARIO VALUES (default, (select ID from TIPOUSUARIO where TIPO = 'COMPRADOR'), 'Teste comprador', '00136985214', 'testescomprador@gmail.com');
insert into USUARIO VALUES (default, (select ID from TIPOUSUARIO where TIPO = 'VENDEDOR'), 'Teste vendedor', '12345678945678', 'testesvendedor@gmail.com');
insert into USUARIO VALUES (default, (select ID from TIPOUSUARIO where TIPO = 'COMPRADOR'), 'Teste comprador 1', '02347895945678', 'testescomprador1@gmail.com');
insert into USUARIO VALUES (default, (select ID from TIPOUSUARIO where TIPO = 'VENDEDOR'), 'Teste vendedor 1', '00125236958', 'testesvendedor1@gmail.com');

insert into Lote VALUES (default, 1111.45);
insert into Lote VALUES (default, 2222.95);

insert into Lote_bem VALUES ((SELECT ID FROM LOTE WHERE PRECO = 1111.45), (SELECT ID FROM BEM WHERE DESCRICAO = 'Estante de madeira'));
insert into Lote_bem VALUES ((SELECT ID FROM LOTE WHERE PRECO = 1111.45), (SELECT ID FROM BEM WHERE DESCRICAO = 'Estante de aço'));
insert into Lote_bem VALUES ((SELECT ID FROM LOTE WHERE PRECO = 2222.95), (SELECT ID FROM BEM WHERE DESCRICAO = 'Notebook 3d'));

insert into Leilao VALUES (default, 
	(SELECT id from USUARIO WHERE NOME = 'Teste comprador'), 
	(SELECT ID FROM LOTE WHERE PRECO = 1111.45),
	(SELECT ID FROM NATUREZA WHERE NOME = 'OFERTA'),
	(SELECT ID FROM FORMALANCE WHERE FORMA = 'FECHADO'),
	'2014-12-10',
	'2014-12-10',
	'14:00',
	'16:00'
);
	
insert into Leilao VALUES (default, 
	(SELECT id from USUARIO WHERE NOME = 'Teste vendedor'), 
	(SELECT ID FROM LOTE WHERE PRECO = 2222.95),
	(SELECT ID FROM NATUREZA WHERE NOME = 'DEMANDA'),
	(SELECT ID FROM FORMALANCE WHERE FORMA = 'ABERTO'),
	'2014-12-10',
	'2014-12-10',
	'14:00',
	'16:00'
);

insert into Lance VALUES (default, (SELECT id from USUARIO WHERE NOME = 'Teste comprador'), (SELECT ID FROM LOTE WHERE PRECO = 1111.45), '2014-12-10', '14:15',	1110.00);
insert into Lance VALUES (default, (SELECT id from USUARIO WHERE NOME = 'Teste comprador'), (SELECT ID FROM LOTE WHERE PRECO = 1111.45), '2014-12-10', '14:05', 1111.00);

insert into Lance VALUES (default, (SELECT id from USUARIO WHERE NOME = 'Teste vendedor'), (SELECT ID FROM LOTE WHERE PRECO = 2222.95), '2014-12-10', '14:15', 2220.95);
insert into Lance VALUES (default, (SELECT id from USUARIO WHERE NOME = 'Teste comprador'), (SELECT ID FROM LOTE WHERE PRECO = 2222.95), '2014-12-10', '14:45', 2223.95);
