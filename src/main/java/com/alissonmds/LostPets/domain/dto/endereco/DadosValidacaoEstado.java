package com.alissonmds.LostPets.domain.dto.endereco;

import com.alissonmds.LostPets.domain.models.endereco.Estados;

public record DadosValidacaoEstado(String estado) {

    public DadosValidacaoEstado {
        Estados.verificarEstado(estado);
    }

    public static DadosValidacaoEstado criar(String estado) {
        return new DadosValidacaoEstado(estado);
    }
}
