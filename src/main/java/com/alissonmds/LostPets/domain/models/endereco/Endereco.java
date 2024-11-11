package com.alissonmds.LostPets.domain.models.endereco;

import com.alissonmds.LostPets.domain.dto.endereco.DadosCadastramentoEndereco;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String referencia;

    public Endereco(DadosCadastramentoEndereco local) {
        this.estado = local.estado();
        this.cidade = local.cidade();
        this.bairro = local.bairro();
        this.rua = local.rua();
        this.referencia = local.referencia();
    }
}
