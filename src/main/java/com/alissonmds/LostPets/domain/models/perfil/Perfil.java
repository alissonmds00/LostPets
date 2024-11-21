package com.alissonmds.LostPets.domain.models.perfil;

import com.alissonmds.LostPets.domain.dto.perfil.DadosCadastramentoPerfil;
import com.alissonmds.LostPets.domain.models.pet.Pet;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
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

    public Perfil(DadosCadastramentoPerfil dados, Usuario usuario) {
        this.nome = dados.nome();
        this.usuario = usuario;
        this.telefone = dados.telefone();
        this.instagram = dados.instagram();
    }
}
