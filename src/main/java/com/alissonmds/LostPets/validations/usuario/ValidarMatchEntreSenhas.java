package com.alissonmds.LostPets.validations.usuario;

import com.alissonmds.LostPets.domain.dto.usuario.DadosAlteracaoSenha;
import com.alissonmds.LostPets.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidarMatchEntreSenhas implements ValidacoesAlteracaoDeSenha{
    @Override
    public void validar(DadosAlteracaoSenha dados) {
        if (!dados.novaSenha().equals(dados.confirmacaoNovaSenha())) {
            throw new ValidacaoException("As senhas devem ser iguais entre sí.");
        }
    }
}
