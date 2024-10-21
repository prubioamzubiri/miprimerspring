package com.zubiri.miprimerspring.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConf{



    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    
    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){

        InMemoryUserDetailsManager userService = new InMemoryUserDetailsManager();

        UserDetails usuario1 = User.builder()
                                    .username("user")
                                    .password(passwordEncoder.encode("1234"))
                                    .roles("USER")
                                    .build(),
                    usuario2= User.builder()
                                    .username("admin")
                                    .password(passwordEncoder.encode("1234"))
                                    .roles("USER", "ADMIN")
                                    .build();

        
        userService.createUser(usuario1);
        userService.createUser(usuario2);

        return userService;
    }*/

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){

        String usersByUsernameQuery = "select username, password, enabled from Usuarios where username = ?";
        
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        return jdbcUserDetailsManager;

    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        
        http
            .authorizeHttpRequests((requests) -> requests              
            .requestMatchers("/**").permitAll()
            .requestMatchers("/actor/**").hasRole("USER")
            .requestMatchers("/actor/addActor").hasRole("ADMIN")
            );

        /*http.
            authorizeHttpRequests((requests) -> requests
            .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());*/
            
            

        return http.build();
    }

}
