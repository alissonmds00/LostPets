package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import com.alissonmds.LostPets.domain.dto.pet.DadosDetalhamentoPet;
import com.alissonmds.LostPets.domain.services.ExtracaoDadosTokenService;
import com.alissonmds.LostPets.domain.services.PetService;
import com.alissonmds.LostPets.repository.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final PetRepository petRepository;
    private final ExtracaoDadosTokenService dadosTokenService;


    @Autowired
    public PetController(PetService petService, PetRepository petRepository, ExtracaoDadosTokenService dadosTokenService) {
        this.petService = petService;
        this.petRepository = petRepository;
        this.dadosTokenService = dadosTokenService;

    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPet> cadastrarPet(@RequestBody @Valid DadosCadastramentoPet dados, @RequestHeader("Authorization") String bearerToken) {
        try {
            var autor = dadosTokenService.identificarPerfil(bearerToken);
            var postPet = petService.criarNovoPost(dados, autor);
            return ResponseEntity.ok(new DadosDetalhamentoPet(postPet));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
