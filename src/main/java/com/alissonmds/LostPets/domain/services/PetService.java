package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import com.alissonmds.LostPets.domain.models.endereco.Estados;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.domain.models.pet.Pet;
import com.alissonmds.LostPets.domain.models.pet.Situacao;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Pet> filtrarPosts(Pageable pagina, Situacao situacao, Estados estado, String cidade) {
        try {
            return petRepository.findBySituacaoECidade(pagina, situacao.toString(), estado.getNomeEstado(), cidade);

        } catch (Exception e) {
            throw new ValidacaoException("Nenhum anúncio encontrado.");
        }
    }
}
