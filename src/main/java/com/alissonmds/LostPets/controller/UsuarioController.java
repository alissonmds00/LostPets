package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.dto.usuario.DadosCadastramentoUsuario;
import com.alissonmds.LostPets.domain.services.UsuarioService;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/registro")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastramentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastramentoUsuario dados, UriComponentsBuilder uriBuilder) {
        try {
            var usuario = usuarioService.criarUsuario(dados);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
