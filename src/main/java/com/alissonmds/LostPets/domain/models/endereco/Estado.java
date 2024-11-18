package com.alissonmds.LostPets.domain.models.endereco;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Estado")
@Entity(name = "Estado")
@EqualsAndHashCode(of = "id")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigoUf;
    private String nome;
    private String uf;

}
