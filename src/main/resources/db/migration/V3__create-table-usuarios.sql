create table usuarios(
    id SERIAL NOT NULL,
    login varchar(255) NOT NULL UNIQUE,
    senha varchar(300) NOT NULL,
    ativo smallint NOT NULL,
    PRIMARY KEY (id)
)