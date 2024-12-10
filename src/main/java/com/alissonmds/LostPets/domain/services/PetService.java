package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import com.alissonmds.LostPets.domain.models.endereco.Estados;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.domain.models.pet.Pet;
import com.alissonmds.LostPets.domain.models.pet.Situacao;
import com.alissonmds.LostPets.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
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

    public Page<Pet> filtrarPostsEstadoCidade(Pageable pagina, Situacao situacao, Estados estado, String cidade) {
        try {
            return petRepository.findBySituacaoECidade(pagina, situacao.toString(), estado.getNomeEstado(), cidade);

        } catch (Exception e) {
            throw new EntityNotFoundException("Nenhum anúncio encontrado.");
        }
    }

    public Page<Pet> filtrarPostsEstado(Pageable pagina, Situacao situacao, Estados estado) {
        try {
            return petRepository.findBySituacaoEstado(pagina, situacao.toString(), estado.getNomeEstado());

        } catch (Exception e) {
            throw new EntityNotFoundException("Nenhum anúncio encontrado." + e.getMessage());
        }
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));
    }

    public void desativarPost(Long id)  {
        Pet post;
        try {
            post = petRepository.getByIdAtivo(id);
            post.desativar();
            petRepository.save(post);
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("Nenhum post encontrado, ou já inativo.");
        }
    }
}
