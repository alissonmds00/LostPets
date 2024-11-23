create table pet(
    id SERIAL NOT NULL,
    animal varchar(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    data timestamp NOT NULL,
    foto varchar(255) NOT NULL,
    bairro varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    estado varchar(255) NOT NULL,
    referencia varchar(255) NOT NULL,
    rua varchar(255) NOT NULL,
    situacao varchar(50) NOT NULL,
    titulo varchar(255) NOT NULL,
    perfil_id bigint NOT NULL,
    FOREIGN KEY (perfil_id) REFERENCES perfis(id),
    PRIMARY KEY (id)
)