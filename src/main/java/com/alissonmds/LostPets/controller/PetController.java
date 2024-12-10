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
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Page<DadosDetalhamentoPet>> obterPostPetsPorSituacaoEstadoCidade(
            @PathVariable Situacao situacao,
            @PathVariable Estados estado,
            @PathVariable String cidade,
            @PageableDefault(size = 20, sort = {"data"}) Pageable page) {
        var postsPet = petService.filtrarPostsEstadoCidade(page, situacao, estado, cidade).map(DadosDetalhamentoPet::new);
        return ResponseEntity.ok(postsPet);
    }

    @GetMapping("/{situacao}/{estado}")
    public ResponseEntity<Page<DadosDetalhamentoPet>> obterPostPetsPorSituacaoEstado(
            @PathVariable Situacao situacao,
            @PathVariable Estados estado,
            @PageableDefault(size = 20, sort = {"data"}) Pageable page) {
        var postsPet = petService.filtrarPostsEstado(page, situacao, estado).map(DadosDetalhamentoPet::new);
        return ResponseEntity.ok(postsPet);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("(principal.username.equals(@petService.getPetById(#id).perfil.usuario.login) || hasAuthority('ROLE_ADMIN'))")
    public ResponseEntity<Void> deletarPost(
            @PathVariable Long id) {
        petService.desativarPost(id);
        return ResponseEntity.noContent().build();
    }
}