package com.alissonmds.LostPets.validation.pet;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;

public interface ValidadorCadastroPostPet {
    void validar(DadosCadastramentoPet dados);
}
