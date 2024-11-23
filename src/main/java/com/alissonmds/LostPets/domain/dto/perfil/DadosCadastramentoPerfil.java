package com.alissonmds.LostPets.domain.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastramentoPerfil(
        @NotBlank @Pattern(regexp = "^[\\p{L}\\s]{2,50}$") String nome,
        @NotBlank @Pattern(regexp = "\\d{2}[\\s-]?\\d{5}[\\s-]?\\d{4}") String telefone,
        @Pattern(regexp = "^@[a-zA-z0-9._]+$") String instagram
) {
}
