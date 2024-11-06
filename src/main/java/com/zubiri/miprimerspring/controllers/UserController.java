package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.log.Log;
import com.zubiri.miprimerspring.aplicacion.AplicacionUsuario;
import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.dto.GetUserDto;
import com.zubiri.miprimerspring.dto.UserInDto;
import com.zubiri.miprimerspring.dto.converter.LoginDto;
import com.zubiri.miprimerspring.dto.converter.UserDtoConverter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.zubiri.miprimerspring.security.jwt.JwtTokenProvider;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@EnableMethodSecurity
public class UserController {

    UserDtoConverter userConverter;
    AplicacionUsuario aplicacionUsuario;
    AuthenticationManager authenticationManager;
    JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<GetUserDto> Register(@RequestBody UserInDto entity) {
        
        if((entity.getPassword().compareTo(entity.getPassword2())==0)
           && (entity.getEmail().compareTo(entity.getEmail2())==0))
        {

            GetUserDto to_return = aplicacionUsuario.guardar(entity);

            if (to_return != null)
            {
                return ResponseEntity.status(HttpStatus.CREATED)
                                     .body(to_return);
                
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);



            ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                                                .path("/")
                                                .httpOnly(true)
                                                .sameSite("None")
                                                .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok("Logged in");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid username/password supplied");
        }
    }

    @GetMapping("/create")
    public ResponseEntity<GetUserDto> create() {
        GetUserDto to_return = aplicacionUsuario.createAdmin();
        if (to_return != null)
        {
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(to_return);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(null);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    GetUserDto getUserDto(@AuthenticationPrincipal Usuario user){
        return userConverter.fromUser(user);
    }

    @GetMapping("/meTodo")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario getTodo(@AuthenticationPrincipal Usuario user){

        System.out.println(user);
        return user;
    }
    
    
}
