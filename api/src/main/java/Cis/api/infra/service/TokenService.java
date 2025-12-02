/*
package Cis.api.infra.service;

import Cis.api.domain.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String JWT_ISSUER = "API_CIS";

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("api")
                    .withSubject(usuario.getLogin()) //identifica o usuario passando o login
                    .withExpiresAt(dataExpiracao()) //tempo de expiração do token
                    .sign(algorithm);
            /*
            return JWT.create()
                    .withIssuer(JWT_ISSUER) // AQUI: "API_CIS"
                    .withSubject(usuario.getLogin())
                    // ADICIONA A PERMISSÃO COMO UMA CLAIM (REIVINDICAÇÃO)
                    .withClaim("role", usuario.getPermissao().name())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
             */
/*
        }catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("api") // AQUI: AGORA IGUAL AO DE CIMA ("API_CIS")
                    .build()
                    .verify(tokenJWT) // Tenta decodificar e verificar a assinatura
                    .getSubject(); // Retorna o login (Subject)

        } catch (JWTVerificationException exception){
            // Se o token for inválido, expirado ou a assinatura não bater
            throw new RuntimeException("Token JWT inválido ou expirado!", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}

 */
