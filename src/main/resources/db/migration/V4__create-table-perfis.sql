create table perfis(
   id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    nome varchar(255) NOT NULL,
    telefone varchar(50) NOT NULL,
    instagram varchar(255),
    usuario_id bigint NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    primary key (id)
)