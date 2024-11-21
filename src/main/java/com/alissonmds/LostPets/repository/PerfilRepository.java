package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
