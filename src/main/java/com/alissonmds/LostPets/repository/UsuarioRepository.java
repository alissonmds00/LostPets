package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
