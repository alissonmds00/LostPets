package com.alissonmds.LostPets.domain.dto.endereco;

import com.alissonmds.LostPets.domain.models.endereco.Estados;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;

public record DadosAtualizacaoEndereco(
         String estado,
         String cidade
) {
    public DadosAtualizacaoEndereco(Perfil perfil, String estado, String cidade) {
        this(Estados.verificarEstado(estado).getNomeEstado(), cidade);
    }
}
