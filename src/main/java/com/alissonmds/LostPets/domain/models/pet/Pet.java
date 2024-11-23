package com.alissonmds.LostPets.domain.models.pet;

import com.alissonmds.LostPets.domain.dto.pet.DadosCadastramentoPet;
import com.alissonmds.LostPets.domain.models.endereco.Endereco;
import com.alissonmds.LostPets.domain.models.perfil.Perfil;
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
    private String titulo;
    private LocalDateTime data;
    private Situacao situacao;
    private Animal animal;
    private String foto;

    public Pet(DadosCadastramentoPet dados, Perfil autor) {
        this.ativo = true;
        this.local = new Endereco(dados.local());
        this.titulo = dados.titulo();
        this.data = LocalDateTime.now();
        this.situacao = dados.situacao();
        this.perfil = autor;
    }

    public void desativar() {
        this.ativo = false;
    }
}
