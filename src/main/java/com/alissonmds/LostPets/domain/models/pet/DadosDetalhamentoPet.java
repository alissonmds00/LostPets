package com.alissonmds.LostPets.domain.models.pet;

import com.alissonmds.LostPets.domain.models.endereco.Endereco;

import java.time.LocalDateTime;

public record DadosDetalhamentoPet(String nomeAutor,
                                   String contatoAutor,
                                   Endereco endereco,
                                   String titulo,
                                   LocalDateTime dataPostagem,
                                   Situacao situacao,
                                   Animal animal,
                                   String urlFoto) {

    public DadosDetalhamentoPet(Pet pet) {
        this(pet.getPerfil().getNome(), pet.getPerfil().getTelefone(), pet.getLocal(), pet.getTitulo(), pet.getData(), pet.getSituacao(), pet.getAnimal(), pet.getFoto());
    }
}
