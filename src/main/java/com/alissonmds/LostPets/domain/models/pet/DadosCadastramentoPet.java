package com.alissonmds.LostPets.domain.models.pet;


import com.alissonmds.LostPets.domain.models.endereco.DadosCadastramentoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record DadosCadastramentoPet(
        @Valid @NotNull DadosCadastramentoEndereco local,
        @NotBlank String titulo,
        @NotNull Situacao situacao,
        @NotNull Animal animal,
        @URL @NotBlank String foto
        ) {
}
