package com.zubiri.miprimerspring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zubiri.miprimerspring.aplicacion.AplicacionUsuario;
import com.zubiri.miprimerspring.dominio.user.Usuario;
import com.zubiri.miprimerspring.dto.GetUserDto;
import com.zubiri.miprimerspring.dto.UserInDto;
import com.zubiri.miprimerspring.dto.converter.UserDtoConverter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.zubiri.miprimerspring.security.jwt.JwtAuthenticationResponse;
import com.zubiri.miprimerspring.security.jwt.JwtTokenProvider;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/create")
    public ResponseEntity<GetUserDto> create() {
        GetUserDto to_return = aplicacionUsuario.guardar(new UserInDto("Admin", "admin", "admin", "admin@admin.com","admin@admin.com"));
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
    
    
}