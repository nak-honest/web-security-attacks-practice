package com.xss.xss.infrastructure;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String NAME_KEY = "name";

    private final String accessSecretKey;
    private final long accessExpiration;

    public JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") String accessSecretKey,
            @Value("${security.jwt.token.expire-length}") long accessExpiration
    ) {
        this.accessSecretKey = accessSecretKey;
        this.accessExpiration = accessExpiration;
    }

    public String createToken(String name) {
        return createToken(name, accessSecretKey, accessExpiration);
    }

    private String createToken(String name, String secretKey, long expiration) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(name)
                .claim(NAME_KEY, name)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public String decodeToken(String token) {
        return decode(token, accessSecretKey);
    }

    private String decode(String token, String secretKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get(NAME_KEY)
                    .toString();
        } catch (ExpiredJwtException exception) {
            throw new IllegalArgumentException("이미 만료된 토큰입니다.", exception.getCause());
        } catch (Exception exception) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.", exception.getCause());
        }
    }
}
