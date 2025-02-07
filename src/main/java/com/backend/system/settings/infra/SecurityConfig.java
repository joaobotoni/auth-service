package com.backend.system.settings.infra;

import com.backend.system.filter.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter filter;

    @Bean // Define o filtro de segurança para a aplicação
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())  // Desabilita a proteção CSRF, permitindo requisições de outros domínios
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a política de autenticação para STATELESS
                .authorizeHttpRequests((authorize) -> authorize  // Configura permissões para diferentes URLs
                        .requestMatchers(HttpMethod.POST,"/auth/register", "/auth/login").permitAll()  // Permite acesso sem autenticação para as rotas de login e registro
                        .requestMatchers("/product").hasRole("ADMIN") // se permite acessar as rotas de produtos os admin
                        .anyRequest().authenticated()  // Exige autenticação para qualquer outra requisição
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); // Adiciona um filtro antes de fazer a verificação das credenciais
        return http.build();
    }

    @Bean // O método retorna um AuthenticationManager configurado automaticamente pelo Spring Security.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
    }

    @Bean // É usado para codificar senhas de maneira segura no Spring Security.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}