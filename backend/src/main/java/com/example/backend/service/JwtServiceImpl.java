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
        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 60 );

        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key,value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap).setClaims(map).setExpiration(expTime).signWith(signKey,SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            try{
                byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
                Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();

                return claims;
            }catch (ExpiredJwtException e){
                // 만료됨
                System.out.println("만료");
            }catch (JwtException e){
                // 유효 하지 않음
                System.out.println("유효하지않음");
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
