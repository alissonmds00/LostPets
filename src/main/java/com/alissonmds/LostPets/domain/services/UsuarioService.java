package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.usuario.DadosCadastramentoUsuario;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario criarUsuario(DadosCadastramentoUsuario dados) throws Exception {
        try {
            var busca = repository.findByLogin(dados.login());
            if (busca.isPresent()) {
                throw new ValidacaoException("Usuário já cadastrado");
            }
            return new Usuario(dados);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
