package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
