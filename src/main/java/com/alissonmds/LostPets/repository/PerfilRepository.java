package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.usuario.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
