package com.crms.hrms_backend.Utils;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.crms.hrms_backend.CustomException.UnauthoriseException;
import com.crms.hrms_backend.Entity.RefreshToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtills {

    @Value("${jwt.secret}")
    private String secret;

    // @Value("${jwt.setExpirationTimeInMs}")
    // private int expirationTime;
    // @Value("${jwt.setRefreshExpirationDateInMs}")
    // private String refreshExpirationTime;
    // @Autowired
    // private RefreshTokenRepository dbRefreshTokenRepository;
    private Key key() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("The specified key is not secure enough. It must be at least 256 bits (32 bytes).");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }

    public String getToken(UserDetails user) {
        final Map<String, Object> claims = new HashMap<>();

        String s = doGenerateToken(claims, user.getUsername());
        System.out.println(s);
        return s;
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (600000 * 10)))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    public String getRefreshToken(UserDetails user) throws Exception {
        final Map<String, Object> claims = new HashMap<>();
        return doGenerateRefreshToken(claims, user.getUsername());
    }

    private String doGenerateRefreshToken(Map<String, Object> claims, String username) throws Exception {
        Long expTime = 6000000l;
        final Date createdDate = new Date();
        final Date expiryDate = new Date(createdDate.getTime() + (expTime * 30));

        List<Map<String, Object>> _claims = new ArrayList<>();
        Map<String, Object> claim = new HashMap<>();
        claim.put("authority", "RERRESH");
        _claims.add(claim);

        String token = Jwts
                .builder()
                .setClaims(claim)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expiryDate)
                .signWith(key(), SignatureAlgorithm.HS256).compact();

        RefreshToken refreshToken = _claims.stream().map(r -> {
            RefreshToken tokenEntity = new RefreshToken();
            tokenEntity.setToken(token);
            tokenEntity.setUsername(username);
            tokenEntity.setExpiryDate(expiryDate.toInstant());
            tokenEntity.setExpired(false);
            return tokenEntity;
        }).findFirst().orElseThrow(() -> new BadRequestException("Problem with Genration Refresh Token"));

        //dbRefreshTokenRepository.saveRefreshTokenDetail(refreshToken);
        return token;
    }

    public boolean validateToken(String token) throws Exception {
        if (isTokenExpired(token)) {
            throw new UnauthoriseException("Token is Expired");
        }
        return true;
    }

}
