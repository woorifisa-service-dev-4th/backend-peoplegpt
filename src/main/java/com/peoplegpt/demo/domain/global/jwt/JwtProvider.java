package com.peoplegpt.demo.domain.global.jwt;

import com.peoplegpt.demo.domain.user.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @SuppressWarnings("deprecation")
    public String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.getEmail())
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
}
