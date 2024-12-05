package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.endereco.DadosAtualizacaoEndereco;
import com.alissonmds.LostPets.domain.dto.perfil.DadosCadastramentoPerfil;
import com.alissonmds.LostPets.domain.dto.perfil.DadosDetalhamentoPerfil;
import com.alissonmds.LostPets.domain.models.endereco.Estados;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.repository.EstadoRepository;
import com.alissonmds.LostPets.repository.PerfilRepository;
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

    public DadosDetalhamentoPerfil buscarPerfil(Long id) {
        return new DadosDetalhamentoPerfil(perfilRepository.findById(id)
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
}
