package com.SWP391_Project.Util;

import com.SWP391_Project.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    public String generateToken(User user) throws Exception{
        //properties => claims
        Map<String, Object> claims = new HashMap<>();
        //this.generateSecretKey();
        claims.put("phoneNumber", user.getPhoneNumber());
        try {
            String token = Jwts.builder()
                    .setClaims(claims) //how to extract claims from this ?  Payload
                    .setSubject(user.getPhoneNumber())
                    .setExpiration(new Date(System.currentTimeMillis() + 2592000 * 1000L)) //30 ngày
                    .signWith(getSignInKey(), SignatureAlgorithm.HS384)
                    .compact();
            return token;
        }catch (Exception e) {
            throw new InvalidParameterException("Cannot create jwt token, error: "+e.getMessage());
        }
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode("9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9");
        //Keys.hmacShaKeyFor(Decoders.BASE64.decode("TaqlmGv1iEDMRiFp/pHuID1+T84IABfuA0xXh4GhiUI="));
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //check expiration
    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        String phoneNumber = extractPhoneNumber(token);
        return (phoneNumber.equals(userDetails.getUsername()))
                && !isTokenExpired(token); //check hạn của token
    }
}
