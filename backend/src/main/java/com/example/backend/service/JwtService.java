package com.example.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    public String getToken(String key,Object value);
    Claims getClaims(String token);
    boolean isValid(String token);
    int getId(String token);

    String getToken(String key1, int no, String key2, String id, String key3, String position);
}
