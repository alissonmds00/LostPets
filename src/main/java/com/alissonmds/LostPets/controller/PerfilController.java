package com.alissonmds.LostPets.controller;


import com.alissonmds.LostPets.domain.dto.endereco.DadosAtualizacaoEndereco;
import com.alissonmds.LostPets.domain.dto.perfil.DadosCadastramentoPerfil;
import com.alissonmds.LostPets.domain.dto.perfil.DadosDetalhamentoPerfil;
import com.alissonmds.LostPets.domain.dto.perfil.DadosDetalhamentoPerfilEndereco;
import com.alissonmds.LostPets.domain.services.ExtracaoDadosTokenService;
import com.alissonmds.LostPets.domain.services.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilService perfilService;
    private final ExtracaoDadosTokenService dadosTokenService;

    @Autowired
    public PerfilController(PerfilService perfilService, ExtracaoDadosTokenService dadosTokenService) {
        this.perfilService = perfilService;
        this.dadosTokenService = dadosTokenService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPerfil> criarUsuario(@Valid @RequestBody DadosCadastramentoPerfil dados, @RequestHeader("Authorization") String bearerToken) {
        var token = dadosTokenService.extrairToken(bearerToken);
        var novoPerfil = perfilService.criarPerfil(dados, token);
        return ResponseEntity.ok().body(new DadosDetalhamentoPerfil(novoPerfil));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPerfilEndereco> visualizarPerfil(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.buscarPerfil(id));
    }

    @PostMapping("/endereco")
    @Transactional
    public ResponseEntity<DadosDetalhamentoPerfilEndereco> atualizarEndereco(@RequestBody DadosAtualizacaoEndereco dados, @RequestHeader("Authorization") String bearerToken) {
        var perfil = perfilService.atualizarEndereco(dados, bearerToken);
        return ResponseEntity.ok(new DadosDetalhamentoPerfilEndereco(perfil));
    }
}
