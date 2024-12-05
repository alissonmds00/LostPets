package com.alissonmds.LostPets.domain.dto.endereco;

import com.alissonmds.LostPets.domain.models.endereco.Estados;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastramentoEndereco(
        String estado,
        @NotBlank String cidade,
        @NotBlank String bairro,
        @NotBlank String rua,
        String referencia
) {
    public DadosCadastramentoEndereco {
        Estados.verificarEstado(estado);
    }
}
