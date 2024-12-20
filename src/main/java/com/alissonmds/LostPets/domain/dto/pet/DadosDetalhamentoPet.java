package com.alissonmds.LostPets.domain.dto.pet;

import com.alissonmds.LostPets.domain.models.endereco.Endereco;
import com.alissonmds.LostPets.domain.models.pet.Pet;

import java.time.LocalDateTime;

public record DadosDetalhamentoPet(Long id,
                                   String nomeAutor,
                                   String contatoAutor,
                                   Endereco endereco,
                                   String titulo,
                                   LocalDateTime dataPostagem,
                                   String situacao,
                                   String animal,
                                   String urlFoto) {

    public DadosDetalhamentoPet(Pet pet) {
        this(pet.getId(), pet.getPerfil().getNome(), pet.getPerfil().getTelefone(), pet.getLocal(), pet.getTitulo(), pet.getData(), pet.getSituacao(), pet.getAnimal(), pet.getFoto());
    }
}
