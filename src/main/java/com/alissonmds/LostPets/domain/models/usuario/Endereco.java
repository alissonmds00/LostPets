package com.alissonmds.LostPets.domain.models.usuario;

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
}
