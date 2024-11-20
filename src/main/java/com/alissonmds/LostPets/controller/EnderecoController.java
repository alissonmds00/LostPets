package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.models.endereco.Estado;
import com.alissonmds.LostPets.domain.models.endereco.Municipio;
import com.alissonmds.LostPets.repository.EstadoRepository;
import com.alissonmds.LostPets.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping("/estados")
    public ResponseEntity<List<String>> listarEstados() {
        List<String> estados = estadoRepository.findAll().stream()
                .map(Estado::getNome)
                .collect(Collectors.toList());
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
