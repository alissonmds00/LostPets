package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.usuario.DadosCadastramentoUsuario;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(DadosCadastramentoUsuario dados) throws Exception {
        try {
            var busca = repository.findByLogin(dados.login());
            if (busca.isPresent()) {
                throw new ValidacaoException("Usuário já cadastrado");
            }
            var senhaCriptografada = passwordEncoder.encode(dados.senha());
            return new Usuario(dados, senhaCriptografada);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
