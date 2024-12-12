package com.alissonmds.LostPets.validations.usuario;

import com.alissonmds.LostPets.domain.dto.usuario.DadosAlteracaoSenha;

public interface ValidacoesAlteracaoDeSenha {
    void validar(DadosAlteracaoSenha dados);
}
