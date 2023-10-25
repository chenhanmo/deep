package com.deep.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.deep.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try{
            JWTUtils.verify(token);
        } catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","Invalid signature!");
        } catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","Token expired!");
        } catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","Algorithm inconsistency!");
        } catch (Exception e){
            e.printStackTrace();
            map.put("msg","Token invalid!");
        }

        map.put("state",false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

        return false;
    }
}
