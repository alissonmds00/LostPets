package com.alissonmds.LostPets.infra.security;

import com.alissonmds.LostPets.domain.models.perfil.Perfil;
import com.alissonmds.LostPets.domain.models.usuario.Usuario;
import com.alissonmds.LostPets.repository.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String ISSUER = "LostPets api";
    private final String SECRET;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TokenService(Dotenv dotenv, UsuarioRepository usuarioRepository) {
        this.SECRET = dotenv.get("JWT_SECRET");
        this.usuarioRepository = usuarioRepository;
    }

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            return JWT.create().
                    withIssuer(ISSUER)
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na geração do token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado.");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
