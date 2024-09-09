package com.chrisferdev.hus.configuration.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.chrisferdev.hus.configuration.security.jwt.Constans.*;

@Service
// Asignamos los roles una vez el usuario ha sido asignado
public class JWTGenerator {
    public String getToken(String username){
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getAuthorities().stream()
                        .findFirst().toString()
        );

        String token = Jwts.builder()
                .setId("pragma")
                .setSubject(username)
                .claim("authorities", authorityList.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TOKEN_EXPIRATION_TIME))
                .signWith(getSignedKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512).compact();

        return "Bearer "+token;

    }
}
