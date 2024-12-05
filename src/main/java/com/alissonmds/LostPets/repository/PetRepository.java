package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.pet.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT p FROM Pet p WHERE p.local.cidade ilike :cidade AND p.local.estado ilike :estado AND p.situacao = :situacao AND p.ativo ORDER BY p.data DESC")
    Page<Pet> findBySituacaoECidade(Pageable page, String situacao, String estado, String cidade);
}
