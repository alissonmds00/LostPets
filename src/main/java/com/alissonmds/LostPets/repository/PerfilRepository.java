package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("SELECT p FROM Perfil p WHERE p.id = :id AND p.usuario.ativo = true")
    Perfil getReferenceByIdAtivo(Long id);
}
