package org.waveaccess.conferences.security.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.waveaccess.conferences.models.User;

import java.time.LocalDateTime;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private int jwtExpirationMs;

    @Autowired
    private Algorithm algorithm;

    public JwtUtil(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String createAccessTokenFor(User user) {
        System.out.println("CREATE: " + user.role.toString());
        String token = JWT.create()
                .withSubject(user.id.toString())
                .withClaim("role", user.role.toString())
                .withClaim("name", user.name)
                .withClaim("email", user.email)
                .sign(algorithm);
        return token;
    }
}