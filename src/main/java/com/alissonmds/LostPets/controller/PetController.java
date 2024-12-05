package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import com.alissonmds.LostPets.domain.dto.pet.DadosDetalhamentoPet;
import com.alissonmds.LostPets.domain.models.endereco.Estados;
import com.alissonmds.LostPets.domain.models.pet.Situacao;
import com.alissonmds.LostPets.domain.services.ExtracaoDadosTokenService;
import com.alissonmds.LostPets.domain.services.PetService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final ExtracaoDadosTokenService dadosTokenService;

    public PetController(PetService petService, ExtracaoDadosTokenService dadosTokenService) {
        this.petService = petService;
        this.dadosTokenService = dadosTokenService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPet> cadastrarPet(@RequestBody @Valid DadosCadastramentoPet dados, @RequestHeader("Authorization") String bearerToken) {
        var autor = dadosTokenService.identificarPerfil(bearerToken);
        var postPet = petService.criarNovoPost(dados, autor);
        return ResponseEntity.ok(new DadosDetalhamentoPet(postPet));
    }

    @GetMapping("/{situacao}/{estado}/{cidade}")
    public ResponseEntity<Page<DadosDetalhamentoPet>> obterPostPetsPorSituacao(
            @PathVariable Situacao situacao,
            @PathVariable Estados estado,
            @PathVariable String cidade,
            @PageableDefault(size = 20, sort = {"data"}) Pageable page) {
        var postsPet = petService.filtrarPosts(page, situacao, estado, cidade).map(DadosDetalhamentoPet::new);
        return ResponseEntity.ok(postsPet);
    }
}