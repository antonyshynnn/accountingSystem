package com.antonyshyn.accountingSystem.filter;

import com.antonyshyn.accountingSystem.entity.UserEntity;
import com.antonyshyn.accountingSystem.security.SecureUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    public String generateJwtToken(Authentication authentication) {
        SecureUser user = (SecureUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .claim("id", user.getId())
                .setExpiration(new Date((new Date()).getTime() + 10 * 60 * 1000 * 6))
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
}
