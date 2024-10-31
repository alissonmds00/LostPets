package com.alissonmds.LostPets.domain.models.pet;

import com.alissonmds.LostPets.domain.models.usuario.Endereco;
import com.alissonmds.LostPets.domain.models.usuario.Perfil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Pet")
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    private boolean ativo;
    private Endereco local;
    private LocalDateTime data;
    private Situacao situacao;
}
