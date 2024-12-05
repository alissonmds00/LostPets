package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT e FROM Estado e WHERE e.nome ilike :nome")
    boolean existsByNome(String nome);
}
