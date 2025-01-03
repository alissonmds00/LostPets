package com.alissonmds.LostPets.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosRedefinicaoSenha(
        @NotBlank String token,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$") String novaSenha) {
}
