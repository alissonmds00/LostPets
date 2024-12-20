create table Estado(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    codigoUf INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    uf VARCHAR(255) NOT NULL,
    regiao SMALLINT NOT NULL,
    PRIMARY KEY (id)
);
-- Insert Data --

Insert into Estado (codigoUf, nome, uf, regiao)values (12, 'Acre', 'AC', 1);
Insert into Estado (codigoUf, nome, uf, regiao)values (27, 'Alagoas', 'AL', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (16, 'Amapá', 'AP', 1);
Insert into Estado (codigoUf, nome, uf, regiao)values (13, 'Amazonas', 'AM', 1);
Insert into Estado (codigoUf, nome, uf, regiao)values (29, 'Bahia', 'BA', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (23, 'Ceará', 'CE', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (53, 'Distrito Federal', 'DF', 5);
Insert into Estado (codigoUf, nome, uf, regiao)values (32, 'Espírito Santo', 'ES', 3);
Insert into Estado (codigoUf, nome, uf, regiao)values (52, 'Goiás', 'GO', 5);
Insert into Estado (codigoUf, nome, uf, regiao)values (21, 'Maranhão', 'MA', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (51, 'Mato Grosso', 'MT', 5);
Insert into Estado (codigoUf, nome, uf, regiao)values (50, 'Mato Grosso do Sul', 'MS', 5);
Insert into Estado (codigoUf, nome, uf, regiao)values (31, 'Minas Gerais', 'MG', 3);
Insert into Estado (codigoUf, nome, uf, regiao)values (15, 'Pará', 'PA', 1);
Insert into Estado (codigoUf, nome, uf, regiao)values (25, 'Paraíba', 'PB', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (41, 'Paraná', 'PR', 4);
Insert into Estado (codigoUf, nome, uf, regiao)values (26, 'Pernambuco', 'PE', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (22, 'Piauí', 'PI', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (33, 'Rio de Janeiro', 'RJ', 3);
Insert into Estado (codigoUf, nome, uf, regiao)values (24, 'Rio Grande do Norte', 'RN', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (43, 'Rio Grande do Sul', 'RS', 4);
Insert into Estado (codigoUf, nome, uf, regiao)values (11, 'Rondônia', 'RO', 1);
Insert into Estado (codigoUf, nome, uf, regiao)values (14, 'Roraima', 'RR', 1);
Insert into Estado (codigoUf, nome, uf, regiao)values (42, 'Santa Catarina', 'SC', 4);
Insert into Estado (codigoUf, nome, uf, regiao)values (35, 'São Paulo', 'SP', 3);
Insert into Estado (codigoUf, nome, uf, regiao)values (28, 'Sergipe', 'SE', 2);
Insert into Estado (codigoUf, nome, uf, regiao)values (17, 'Tocantins', 'TO', 1);