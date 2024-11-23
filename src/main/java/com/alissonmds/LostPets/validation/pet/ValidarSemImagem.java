package com.alissonmds.LostPets.validation.pet;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import org.springframework.stereotype.Component;

@Component
public class ValidarSemImagem implements ValidadorCadastroPostPet {

    @Override
    public void validar(DadosCadastramentoPet dados) {
        var urlImagem = dados.foto();

    }
}
