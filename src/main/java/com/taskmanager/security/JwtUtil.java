package com.taskmanager.security;

import com.taskmanager.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private long EXPIRATION=1000*60*60;
    public String generateToken(String username){
        return Jwts.builder()
                .signWith(key)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
    public Date extractExpirationDate(String token){
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration();
    }
    public boolean validateToken(String token, UserDetails userDetails){
        String username=extractUsername(token);
        Date expiration=extractExpirationDate(token);
        boolean isExpired=expiration.before(new Date());
        return(username.equals(userDetails.getUsername()) && !isExpired);
    }
}
