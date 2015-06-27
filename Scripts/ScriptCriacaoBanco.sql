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

insert into USUARIO VALUES (default, (select ID from TIPOUSUARIO where TIPO = 'COMPRADOR'), 'Teste comprador', '00136985214', 'testescomprador@gmail.com');
insert into USUARIO VALUES (default, (select ID from TIPOUSUARIO where TIPO = 'VENDEDOR'), 'Teste vendedor', '12345678945678', 'testesvendedor@gmail.com');
