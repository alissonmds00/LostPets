package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.perfil.DadosCadastramentoPerfil;
import com.alissonmds.LostPets.domain.dto.perfil.DadosDetalhamentoPerfil;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.infra.security.TokenService;
import com.alissonmds.LostPets.repository.PerfilRepository;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil criarPerfil(DadosCadastramentoPerfil dados, String token) {
        var login = tokenService.getSubject(token);
        var usuario = usuarioRepository.findByLogin(login).get();
        if (usuario.getPerfil() != null) {
            throw new ValidacaoException("Este usuário já possui um perfil.");
        }
        var perfil = new Perfil(dados, usuario);
        usuario.setPerfil(perfil);
        perfilRepository.save(perfil);
        return perfil;
    }

    public DadosDetalhamentoPerfil buscarPerfil(Long id) {
        var perfil = perfilRepository.findById(id);
        if (perfil.isEmpty()) {
            throw new ValidacaoException("Perfil não encontrado");
        }
        return new DadosDetalhamentoPerfil(perfil.get());
    }
}
