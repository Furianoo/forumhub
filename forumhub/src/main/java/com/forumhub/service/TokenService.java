package com.forumhub.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Segredo para assinar o token (configure no application.properties para melhor segurança)
    @Value("${forumhub.jwt.secret}")
    private String secret;

    // Tempo de validade do token em ms (exemplo: 1 hora)
    @Value("${forumhub.jwt.expiration}")
    private Long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(Object usuario) {
        // Supondo que usuario tem método getLogin() para pegar o nome do usuário
        String login = usuario.toString(); // Ajuste conforme seu objeto Usuario

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + expiration);

        return Jwts.builder()
                .setIssuer("ForumHub API")
                .setSubject(login)
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
