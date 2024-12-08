package com.alissonmds.LostPets.domain.dto.perfil;

import com.alissonmds.LostPets.domain.models.perfil.Perfil;

public record DadosDetalhamentoPerfilEndereco(
        Long id,
        String nome,
        String telefone,
        String instagram,
        Long idUsuario,
        String estado,
        String cidade
) {
        public DadosDetalhamentoPerfilEndereco(Perfil perfil) {
        this(perfil.getId(), perfil.getNome(), perfil.getTelefone(), perfil.getInstagram(), perfil.getUsuario().getId(), perfil.getEstado(), perfil.getCidade());
    }
}
