package com.alissonmds.LostPets.domain.models.usuario;

import com.alissonmds.LostPets.domain.dto.usuario.DadosCadastramentoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private boolean ativo;
    @OneToOne(mappedBy = "usuario")
    private Perfil perfil;

    public Usuario(DadosCadastramentoUsuario dados) {
        this.login = dados.login();
        this.senha = dados.senha();
        this.ativo = true;
    }
}
