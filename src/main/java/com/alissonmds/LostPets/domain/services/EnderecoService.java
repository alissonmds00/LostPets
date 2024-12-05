package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.repository.EstadoRepository;
import com.alissonmds.LostPets.repository.MunicipioRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EstadoRepository estadoRepository;
    private final MunicipioRepository municipioRepository;

    public EnderecoService(EstadoRepository estadoRepository, MunicipioRepository municipioRepository) {
        this.estadoRepository = estadoRepository;
        this.municipioRepository = municipioRepository;
    }

    public boolean validarEstado(String estado) {
        return estadoRepository.existsByNome(estado);
    }

    public boolean validarMunicipio(String estado, String cidade) {
        return municipioRepository.existsByNome(estado, cidade);
    }
}
