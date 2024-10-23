package com.zubiri.miprimerspring.dto.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConverterConf {

    @Bean
    public UserDtoConverter userInDtoToUserConverter(PasswordEncoder passwordEncoder) {
        return new UserDtoConverter(passwordEncoder);
    }
    
}
