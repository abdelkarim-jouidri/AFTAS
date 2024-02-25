package com.example.aftas.Services.Implementations;

import com.example.aftas.Services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static String SECRET_KEY = "f7b5c19f433459acc6816901e4457b05c6186110a481d5b1a838aed92650946b";
    @Override
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        String compact = Jwts.builder().
                setClaims(extraClaims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).
                signWith(getSignKey(), SignatureAlgorithm.HS256).
                compact();
        return compact;
    }

    @Override
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails){
        String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    @Override
    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    @Override
    public Claims extractAllClaims(String jwt) {
        return Jwts.
                parserBuilder().
                setSigningKey(getSignKey()).
                build().
                parseClaimsJws(jwt).
                getBody();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(jwt);
        return claimsTFunction.apply(claims);
    }

    private Key getSignKey() {
        if (SECRET_KEY == null) {
            throw new IllegalArgumentException("SECRET_KEY cannot be null");
        }
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
