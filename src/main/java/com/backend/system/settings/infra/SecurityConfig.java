package com.backend.system.settings.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.SecurityFilterChain;
import com.backend.system.services.CustomUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService service;

    @Bean // Define o filtro de segurança para a aplicação
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())  // Desabilita a proteção CSRF, permitindo requisições de outros domínios
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a política de autenticação para STATELESS
                .authorizeHttpRequests((authorize) -> authorize  // Configura permissões para diferentes URLs
                        .requestMatchers("/auth/register", "/auth/login").permitAll()  // Permite acesso sem autenticação para as rotas de login e registro
                        .anyRequest().authenticated()  // Exige autenticação para qualquer outra requisição
                );

        return http.build();
    }

    @Bean // Cria um bean para o AuthenticationManager, que gerencia o processo de autenticação.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
    }

    @Bean  // Cria um bean de BCryptPasswordEncoder para codificar as senhas
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}