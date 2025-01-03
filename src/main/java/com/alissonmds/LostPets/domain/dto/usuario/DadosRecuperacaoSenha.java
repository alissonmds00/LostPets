package com.alissonmds.LostPets.domain.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosRecuperacaoSenha(
        @Email @NotBlank String email) {
}
