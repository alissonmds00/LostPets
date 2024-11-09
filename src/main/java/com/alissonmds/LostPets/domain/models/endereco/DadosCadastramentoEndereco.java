package com.alissonmds.LostPets.domain.models.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastramentoEndereco(
        @NotBlank String estado,
        @NotBlank String cidade,
        @NotBlank String bairro,
        @NotBlank String rua,
        String referencia
) {
}
