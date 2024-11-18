package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.endereco.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}
