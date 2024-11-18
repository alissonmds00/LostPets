package com.alissonmds.LostPets.domain.models.endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Municipio")
@Entity(name = "Municipio")
@EqualsAndHashCode(of = "id")
public class Municipio {
    @Id
    private Long id;
    private Integer codigo;
    private String nome;
    private String uf;

}
