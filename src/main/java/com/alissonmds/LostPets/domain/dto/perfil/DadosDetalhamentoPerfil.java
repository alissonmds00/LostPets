package com.alissonmds.LostPets.domain.dto.perfil;

import com.alissonmds.LostPets.domain.models.perfil.Perfil;

public record DadosDetalhamentoPerfil (
        Long id,
        String nome,
        String telefone,
        String instagram,
        Long idUsuario
) {
    public DadosDetalhamentoPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNome(), perfil.getTelefone(), perfil.getInstagram(), perfil.getUsuario().getId());
    }
}
