package com.deep.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SIGN = "deep";

    public static String getToken(Map<String, String> map){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);

        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v) ->{
            builder.withClaim(k,v);
        });

        String token =  builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT decode = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return decode;
    }
}
