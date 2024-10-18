package com.zubiri.miprimerspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConf {

    
    
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){

        InMemoryUserDetailsManager userService = new InMemoryUserDetailsManager();
        UserDetails ususario1 = User.builder()
                                    .username("user")
                                    .password(passwordEncoder.encode("1234"))
                                    .roles("USER")
                                    .build(),
                    usuario2= User.builder()
                                    .username("admin")
                                    .password(passwordEncoder.encode("1234"))
                                    .roles("USER", "ADMIN")
                                    .build();

        
        userService.createUser(ususario1);
        userService.createUser(usuario2);

        return userService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests              
            .requestMatchers("/**").permitAll()
            .requestMatchers("/swagger-ui/index.html").permitAll()
            .requestMatchers("/actor/addActor").hasRole("ADMIN")
            .requestMatchers("/actor").hasRole("USER"));

        return http.build();
    }

}
