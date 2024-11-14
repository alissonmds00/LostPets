package com.alissonmds.LostPets.domain.models.usuario;

import com.alissonmds.LostPets.domain.models.pet.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "perfil")
@Entity(name = "Perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String instagram;
    @OneToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.REMOVE)
    private List<Pet> pets;
}
