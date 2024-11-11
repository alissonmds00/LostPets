create table usuarios(
    id bigint not null auto_increwent,
    login varchar(50) UNIQUE,
    senha varchar(100),
    ativo smallint,
    primary key (id), UNIQUE(login)
)