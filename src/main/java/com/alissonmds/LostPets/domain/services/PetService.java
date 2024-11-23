package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.domain.models.pet.Pet;
import com.alissonmds.LostPets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet criarNovoPost(DadosCadastramentoPet dados, Perfil autor) {
        var post = new Pet(dados, autor);
        try {
            petRepository.save(post);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o anúncio" + e.getMessage());
        }
        return post;
    }

}
