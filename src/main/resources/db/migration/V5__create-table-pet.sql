create table pet(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    animal varchar(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    data timestamp NOT NULL,
    foto varchar(255) NOT NULL,
    bairro varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    estado varchar(255) NOT NULL,
    referencia varchar(255) NOT NULL,
    rua varchar(255) NOT NULL,
    situacao varchar(255) NOT NULL,
    titulo varchar(255) NOT NULL,
    perfil_id bigint NOT NULL,
    FOREIGN KEY (perfil_id) REFERENCES perfis(id),
    PRIMARY KEY (id)
)