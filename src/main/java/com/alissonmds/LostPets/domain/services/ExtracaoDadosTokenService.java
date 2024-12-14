package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.infra.security.TokenService;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtracaoDadosTokenService {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public ExtracaoDadosTokenService(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    public String extrairToken(String bearerToken) {
        return bearerToken.replace("Bearer ", "");
    }

    public Usuario identificarUsuario(String bearerToken) {
       var token = this.extrairToken(bearerToken);
       var loginUsuario = tokenService.getSubject(token);
       return usuarioRepository.findByEmail(loginUsuario)
               .orElseThrow(() -> new ValidacaoException("Usuário não encontrado."));
    }

    public Perfil identificarPerfil(String bearerToken) {
        var usuario = this.identificarUsuario(bearerToken);
        if (usuario.getPerfil() == null) {
            throw new ValidacaoException("Perfil não encontrado");
        }
        if (!usuario.isAtivo()) {
            throw new ValidacaoException("Este usuário está desativado.");
        }
        return usuario.getPerfil();
    }
}
