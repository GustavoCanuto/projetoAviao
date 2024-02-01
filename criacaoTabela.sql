CREATE TABLE tb_pais(
    id  INT NOT NULL ,
    nome VARCHAR (75) NOT NULL,
    sigla   CHAR(5)  NOT null,
    PRIMARY KEY (Id)
);

CREATE TABLE tb_estado(
    id  INT primary KEY ,
    nome VARCHAR (75) NOT NULL,
    uf   CHAR(5)  NOT null,
    fk_pais int NOT NULL REFERENCES tb_pais(id)
);

CREATE TABLE tb_cidade(
    id int PRIMARY KEY, 
    nome VARCHAR(120) NOT null,
    fk_estado int NOT NULL REFERENCES tb_estado(id)
);

CREATE TABLE tb_bairro(
    id bigserial PRIMARY KEY, 
    nome VARCHAR(100) NOT null,
    logradouros varchar(255),
    fk_cidade int NOT NULL REFERENCES tb_cidade(id)
);

CREATE TABLE tb_endereco(
    id bigserial PRIMARY KEY, 
    logradouro varchar(255),
    numero varchar(255),
    complemento varchar(255),
    cep varchar(15),
    fk_bairro int NOT NULL REFERENCES tb_bairro(id)
);


CREATE TABLE tb_companhia_aerea(
    id bigserial PRIMARY KEY,
    nome VARCHAR(100) not null, 
    cnpj varchar(20) not null unique,
    email VARCHAR(60) unique
);



CREATE TABLE tb_aeronave(
    id bigserial PRIMARY KEY,
    modelo  VARCHAR(100) not null,
    qtdAssentosEconomico int not null, 
    qtdAssentosVIP int not null,
    NSA VARCHAR(20) not null unique,
    fk_companhia int not null references tb_companhia_aerea(id)
);

CREATE TABLE tb_funcionario(
    id bigserial PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf varchar (20) not null unique,
    data_nascimento date not null, 
    email VARCHAR(50) unique ,
    funcao  varchar(100) not null
);

CREATE TABLE tb_aeroporto(
    id bigserial PRIMARY KEY,
    nome VARCHAR(150) not null,
    email VARCHAR(60) unique,
    fk_endereco int NOT NULL REFERENCES tb_endereco(id)
);

CREATE TABLE tb_funcionario_aeronave(
    id bigserial PRIMARY KEY,
    fk_aeronave int not null references tb_aeronave(id),
    fk_funcionario int not null references tb_funcionario(id)
);

CREATE TABLE tb_passageiro(
    id bigserial PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf varchar (20) not null unique,
    data_nascimento date not null, 
    email VARCHAR(50) unique 
);


CREATE TABLE tb_passagem(
    id bigserial PRIMARY KEY,
    timestamp_compra  TIMESTAMP not null, 
    timestamp_partida TIMESTAMP not null, 
    timestamp_chegada TIMESTAMP not null, 
   	fk_origem  INT not null references tb_aeroporto(id), 
    fk_destino INT not null references tb_aeroporto(id),
    fk_aeronave INT not null references tb_aeronave(id),
    tipo_assento VARCHAR(20) not null,
    valor_passagem decimal not null, 
  	fk_passageiro INT not null references tb_passageiro(id)
);


