package com.example.backend.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("jwtService")
public class JwtServiceImpl implements JwtService{
    private final String secretKey = "abc2roadie@@7a662##8139!asknaklngvasklnaskflcnai42f`24gorge31@#@#sfs";

    @Override
    public String getToken(String key, Object value) {
        // 토큰 유효시간 설정
        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + (1000 * 60 * 60 * 2) );

        // 암호화
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        // header 설정
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT"); // 토큰 타입 지정 : JWT
        headerMap.put("alg","HS256"); // 알고리즘 방식 지정(서명 및 토큰 검증에 사용)

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }
    @Override
    public String getToken(String key1, int no, String key2, String id, String key3, String position) {
        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + (1000 * 60 * 60 * 2) );

        // 암호화
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        // header 설정
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT"); // 토큰 타입 지정 : JWT
        headerMap.put("alg","HS256"); // 알고리즘 방식 지정(서명 및 토큰 검증에 사용)

        Map<String, Object> map = new HashMap<>();
        map.put(key1, no);
        map.put(key2, id);
        map.put(key3, position);

        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }
    @Override
    public Claims getClaims(String token) {
        // 토큰 null 확인
        if(token != null && !"".equals(token)){
            try{
                byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
                Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();

                return claims;
            }catch (ExpiredJwtException e){
                // 만료됨
                System.out.println("Expired JWT Exception >> "+e);
            }catch (JwtException e){
                // 유효 하지 않음
                System.out.println("JWT Exception >> "+e);
            }
        }
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public int getId(String token) {
        Claims claims = this.getClaims(token);
        if(claims != null){
            return Integer.parseInt(claims.get("id").toString());
        }

        return 0;
    }
}
