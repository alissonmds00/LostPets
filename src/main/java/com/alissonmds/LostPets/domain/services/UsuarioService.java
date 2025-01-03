package com.alissonmds.LostPets.domain.services;

import com.alissonmds.LostPets.domain.dto.usuario.DadosAlteracaoSenha;
import com.alissonmds.LostPets.domain.dto.usuario.DadosCadastramentoUsuario;
import com.alissonmds.LostPets.domain.dto.usuario.DadosRecuperacaoSenha;
import com.alissonmds.LostPets.domain.dto.usuario.DadosRedefinicaoSenha;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import com.alissonmds.LostPets.infra.security.TokenService;
import com.alissonmds.LostPets.validations.usuario.ValidacoesAlteracaoDeSenha;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final List<ValidacoesAlteracaoDeSenha> validacoesAlteracaoDeSenhas;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final EmailService emailService;

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder, List<ValidacoesAlteracaoDeSenha> validacoesAlteracaoDeSenha, UsuarioRepository usuarioRepository, TokenService tokenService, EmailService emailService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.validacoesAlteracaoDeSenhas = validacoesAlteracaoDeSenha;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    public Usuario criarUsuario(DadosCadastramentoUsuario dados) throws Exception {
        try {
            var busca = repository.findByEmail(dados.email());
            if (busca.isPresent()) {
                throw new ValidacaoException("Usuário já cadastrado");
            }
            var senhaCriptografada = passwordEncoder.encode(dados.senha());
            return new Usuario(dados, senhaCriptografada);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void alterarSenha(DadosAlteracaoSenha dados) {
        var usuario = repository.findByEmail(dados.email()).orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
        var senhaAntiga = usuario.getSenha();
        if (passwordEncoder.matches(dados.senhaAntiga(), senhaAntiga)) {
            validacoesAlteracaoDeSenhas.forEach(v -> v.validar(dados));
            usuario.alterarSenha(passwordEncoder.encode(dados.novaSenha()));
        } else {
            throw new ValidacaoException("A senha antiga está incorreta.");
        }
    }


    public void solicitarRecuperacaoSenha(DadosRecuperacaoSenha dados) {
        var usuario = usuarioRepository.findByEmail(dados.email())
                .orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário encontrado vinculado a este e-mail."));
        var token = tokenService.gerarTokenRecuperacaoSenha(usuario);
        emailService.enviarEmailRecuperacaoSenha(usuario.getEmail(), token);
    }

    public void redefinirSenha(DadosRedefinicaoSenha dados) {
        var email = tokenService.getSubject(dados.token());
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário encontrado vinculado a este e-mail."));
        usuario.alterarSenha(passwordEncoder.encode(dados.novaSenha()));
    }
}
