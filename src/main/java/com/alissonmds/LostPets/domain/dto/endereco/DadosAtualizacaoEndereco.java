package com.alissonmds.LostPets.domain.dto.endereco;

import com.alissonmds.LostPets.domain.models.endereco.Estados;

public record DadosAtualizacaoEndereco(
         String estado,
         String cidade
) {
    public DadosAtualizacaoEndereco(String estado, String cidade) {
        this.estado = Estados.verificarEstado(estado).getNomeEstado();
        this.cidade = cidade;
    }
}
