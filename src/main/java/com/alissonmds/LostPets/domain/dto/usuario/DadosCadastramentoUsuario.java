package com.alissonmds.LostPets.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastramentoUsuario(
        @NotBlank @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$") String login,
        @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$") String senha
) {
}
