package com.zubiri.miprimerspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.zubiri.miprimerspring.dominio.usuario.Usuario;
import com.zubiri.miprimerspring.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConf {

    PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userdetails(IPersistencia<Usuario> userRepository)
    {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(requests -> requests
                
                .requestMatchers(HttpMethod.GET,"/actor/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/actor/**").hasRole("ADMIN")
                .requestMatchers("/security/**").hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll()
                
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
}
