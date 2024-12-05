package com.alissonmds.LostPets.repository;

import com.alissonmds.LostPets.domain.models.endereco.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    @Query("SELECT m FROM Municipio m JOIN Estado e ON m.uf = e.uf WHERE e.nome = :nome")
    List<Municipio> findAllByNomeEstado(String nome);

    @Query("SELECT m FROM Municipio m JOIN Estado e ON m.uf = e.uf WHERE e.nome = :estado AND m.nome = :cidade")
    boolean existsByNome(String estado, String cidade);

}
