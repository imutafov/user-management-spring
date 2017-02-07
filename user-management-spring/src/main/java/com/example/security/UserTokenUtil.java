/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import com.example.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author msol-pc
 */
@Component
public class UserTokenUtil implements Serializable {

    private String secret = "secret";

    private Long expiration = 604800L;

    public String generateToken(UserDetails userDetails) throws UnsupportedEncodingException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("iat", new Date());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                .compact();
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get("iat"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            final Claims claims = getClaimsFromToken(token);
            expirationDate = claims.getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }

    private Date generateExpirationDate() {
        System.out.println("creating date " + new Date(System.currentTimeMillis() + expiration * 1000));
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token));
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token));
    }
}
