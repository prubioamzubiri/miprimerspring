package com.zubiri.miprimerspring.security;

import java.io.IOException;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    public String realmName;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {
        
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
    }

    @PostConstruct
    private void initialRealmName() {
        this.setRealmName("zubirimanteo.com");
    }
    
}
