package com.alissonmds.LostPets.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank String login, @NotBlank String senha) {
}
