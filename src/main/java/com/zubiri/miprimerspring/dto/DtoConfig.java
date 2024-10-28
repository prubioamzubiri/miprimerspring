package com.zubiri.miprimerspring.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zubiri.miprimerspring.dto.userdtos.UserDtoConverter;

@Configuration
public class DtoConfig {

    @Bean
    public UserDtoConverter userDtoConverter(PasswordEncoder passwordEncoder) {
        return new UserDtoConverter(passwordEncoder);
    }
    
}
