package com.backend.system.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.backend.system.models.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret; // A chave secreta usada para assinar e verificar o token JWT

    // Método para gerar um token JWT para um usuário
    public String generateToken(User user) {
        try {
            // Define o algoritmo HMAC256 para assinatura do token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Cria o token JWT com as informações do usuário e a data de expiração
            String token = JWT.create()
                    .withIssuer("system") // Definindo o emissor do token
                    .withSubject(user.getEmail()) // O sujeito é o e-mail do usuário
                    .withExpiresAt(expirationDate()) // Data de expiração do token
                    .sign(algorithm); // Assina o token com o algoritmo definido

            return token; // Retorna o token gerado
        } catch (JWTCreationException e) {
            // Caso ocorra um erro durante a criação do token, lança uma exceção em tempo de execução
            throw new RuntimeException("Error while generating token: " + e);
        }
    }

    // Método para validar um token JWT
    public String validateToken(String token) {
        try {
            // Define o algoritmo HMAC256 para verificar a assinatura do token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verifica o token com o algoritmo e o emissor esperado
            return JWT.require(algorithm)
                    .withIssuer("system") // Verifica se o emissor é o esperado
                    .build() // Constrói o verificador
                    .verify(token) // Verifica o token
                    .getSubject(); // Retorna o sujeito (e-mail do usuário) do token
        } catch (JWTVerificationException e) {
            // Se o token for inválido, retorna null
            return null;
        }
    }

    // Método privado para calcular a data de expiração do token (2 horas a partir do momento atual)
    private Instant expirationDate() {
        // Define a expiração para 2 horas a partir do momento atual
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
