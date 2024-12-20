package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.endereco.DadosAtualizacaoEndereco;
import com.alissonmds.LostPets.domain.dto.perfil.DadosCadastramentoPerfil;
import com.alissonmds.LostPets.domain.dto.perfil.DadosDetalhamentoPerfilEndereco;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {


    private final PerfilRepository perfilRepository;
    private final ExtracaoDadosTokenService dadosTokenService;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository, ExtracaoDadosTokenService dadosTokenService) {
        this.perfilRepository = perfilRepository;
        this.dadosTokenService = dadosTokenService;
    }

    public Perfil criarPerfil(DadosCadastramentoPerfil dados, String token) {
        var usuario = dadosTokenService.identificarUsuario(token);
        if (usuario.getPerfil() != null) {
            throw new ValidacaoException("Este usuário já possui um perfil.");
        }
        if (!usuario.isAtivo()) {
            throw new ValidacaoException("Este usuário foi desativado");
        }
        var perfil = new Perfil(dados, usuario);
        usuario.setPerfil(perfil);
        perfilRepository.save(perfil);
        return perfil;
    }

    public DadosDetalhamentoPerfilEndereco buscarPerfil(Long id) {
        return new DadosDetalhamentoPerfilEndereco(perfilRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Perfil não encontrado")));
    }

    public Perfil atualizarEndereco(DadosAtualizacaoEndereco dados, String bearerToken) {
            var perfil = dadosTokenService.identificarPerfil(bearerToken);
            try {
                perfil.atualizarEndereco(dados);
            } catch (Exception ex) {
                throw new ValidacaoException("Ocorreu um erro ao tentar atualizar o endereço deste perfil");
            }
            return perfil;
    }

    public void bloquearPerfil(Long id) {
        var perfil = perfilRepository.getReferenceByIdAtivo(id);
        try {
            perfil.getUsuario().bloquearUsuario();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Não foi encontrado. O usuário é inválido ou está bloqueado");
        }
    }
}
