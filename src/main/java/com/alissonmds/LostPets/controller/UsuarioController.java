package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.dto.usuario.DadosAlteracaoSenha;
import com.alissonmds.LostPets.domain.dto.usuario.DadosCadastramentoUsuario;
import com.alissonmds.LostPets.domain.services.UsuarioService;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/registro")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastramentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastramentoUsuario dados, UriComponentsBuilder uriBuilder) throws Exception {
        var usuario = usuarioService.criarUsuario(dados);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
