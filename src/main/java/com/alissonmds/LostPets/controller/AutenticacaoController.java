package com.alissonmds.LostPets.controller;

import com.alissonmds.LostPets.domain.dto.usuario.DadosAlteracaoSenha;
import com.alissonmds.LostPets.domain.dto.usuario.DadosAutenticacao;
import com.alissonmds.LostPets.domain.dto.usuario.DadosRecuperacaoSenha;
import com.alissonmds.LostPets.domain.dto.usuario.DadosRedefinicaoSenha;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import com.alissonmds.LostPets.domain.services.UsuarioService;
import com.alissonmds.LostPets.infra.security.DadosTokenJWT;
import com.alissonmds.LostPets.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @Autowired
    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService, UsuarioService usuarioService) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }
    
    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/alterar-senha")
    @Transactional
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid DadosAlteracaoSenha dados) {
        usuarioService.alterarSenha(dados);
        return ResponseEntity.ok().build();
    }

    //para utilizar, necessita de um serviço de email que permita login de fontes pouco confiáveis
    @PostMapping("/recuperar-senha")
    public ResponseEntity<Void> solicitarRecuperacaoSenha(@RequestBody @Valid DadosRecuperacaoSenha dados) {
        usuarioService.solicitarRecuperacaoSenha(dados);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(@RequestBody @Valid DadosRedefinicaoSenha dados) {
        usuarioService.redefinirSenha(dados);
        return ResponseEntity.ok().build();
    }
}