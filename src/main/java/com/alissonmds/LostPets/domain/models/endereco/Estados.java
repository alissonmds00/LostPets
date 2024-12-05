package com.alissonmds.LostPets.domain.models.endereco;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Estados {
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");

    public final String nomeEstado;

    Estados(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getNomeEstado() {
        return this.nomeEstado;
    }

    public static Estados verificarEstado(String nomeEstado) {
        for (Estados estado : Estados.values()) {
            if (estado.nomeEstado.equalsIgnoreCase(nomeEstado)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Nenhum estado encontrado");
    }

    public static List<String> listarNomesEstados() {
        return Stream.of(Estados.values())
                .map(e -> e.nomeEstado)
                .collect(Collectors.toList());
    }
}