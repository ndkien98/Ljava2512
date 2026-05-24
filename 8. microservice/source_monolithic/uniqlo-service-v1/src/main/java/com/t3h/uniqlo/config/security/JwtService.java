package com.t3h.uniqlo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final RsaKeyGeneratorUtil rsaKeyGeneratorUtil;
    
    // Thoi gian song cua Access Token (1 gio)
    private final long jwtExpiration = 3600000; 
    // Thoi gian song cua Refresh Token (7 ngay)
    private final long refreshExpiration = 604800000; 

    // Lay username (email) tu trong payload cua JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Lay mot thong tin cu the tu JWT payload thong qua ham resolver
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Tao Access Token voi cac thong tin mac dinh
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Tao Access Token co them cac extra claims (thong tin bo sung)
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // Tao Refresh Token (dung de xin lai Access Token khi het han)
    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    // Ham core de build JWT: su dung Private Key RSA (Asymmetric) de tao chu ky (Sign)
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(rsaKeyGeneratorUtil.getPrivateKey(), Jwts.SIG.RS256) // SIGN bang PRIVATE KEY
                .compact();
    }

    // Kiem tra tinh hop le cua token (dung username va thoi gian het han)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Kiem tra xem token da het han chua
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Giai ma token bang Public Key RSA de lay thong tin
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(rsaKeyGeneratorUtil.getPublicKey()) // VERIFY bang PUBLIC KEY
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
