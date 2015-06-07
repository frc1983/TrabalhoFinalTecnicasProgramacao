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
DataInicio timestamp not null,
DataTermino timestamp not null,
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
IdBem int not null,
Preco decimal(10,2) not null
);

CREATE TABLE Bem (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IdCategoriaBem int not null,
Descricao varchar(255) not null,
DescricaoCompleta long varchar not null
);

CREATE TABLE CategoriaBem (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Categoria varchar(255) not null
);

CREATE TABLE Lance (
Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
IdUsuario int not null,
IdLote int not null,
DataHora timestamp not null,
Valor decimal(10,2) not null,
FOREIGN KEY(IdUsuario) REFERENCES Usuario (Id),
FOREIGN KEY(IdLote) REFERENCES Lote (Id)
);

ALTER TABLE Usuario ADD FOREIGN KEY(IdTipoUsuario) REFERENCES TipoUsuario (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdLote) REFERENCES Lote (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdNatureza) REFERENCES Natureza (Id);
ALTER TABLE Leilao ADD FOREIGN KEY(IdFormaLance) REFERENCES FormaLance (Id);
ALTER TABLE Lote ADD FOREIGN KEY(IdBem) REFERENCES Bem (Id);
ALTER TABLE Bem ADD FOREIGN KEY(IdCategoriaBem) REFERENCES CategoriaBem (Id);



--INSERTS
insert into tipousuario VALUES (default, 'COMPRADOR');
insert into tipousuario VALUES (default, 'VENDEDOR');

insert into Natureza VALUES (default, 'OFERTA');
insert into Natureza VALUES (default, 'DEMANDA');

insert into FormaLance VALUES (default, 'ABERTO');
insert into FormaLance VALUES (default, 'FECHADO');