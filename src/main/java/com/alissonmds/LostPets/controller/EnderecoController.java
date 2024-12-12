package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.models.endereco.Estados;
import com.alissonmds.LostPets.domain.models.endereco.Municipio;
import com.alissonmds.LostPets.repository.MunicipioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
@SecurityRequirement(name = "bearer-key")
public class EnderecoController {

    private final MunicipioRepository municipioRepository;

    public EnderecoController(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @GetMapping("/estados")
    public ResponseEntity<List<String>> listarEstados() {
        var estados = Estados.listarNomesEstados();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{nomeEstado}")
    public ResponseEntity<List<String>> listarMunicipios(@PathVariable String nomeEstado) {
        List<String> municipios = municipioRepository.findAllByNomeEstado(nomeEstado).stream()
                .map(Municipio::getNome)
                .collect(Collectors.toList());
        return ResponseEntity.ok(municipios);
    }
}
