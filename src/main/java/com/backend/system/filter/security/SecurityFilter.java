package com.backend.system.filter.security;

import com.backend.system.repositories.UserRepository;
import com.backend.system.services.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService service; // Serviço para validar e extrair informações do token

    @Autowired
    UserRepository repository; // Repositório para consultar usuários pelo e-mail

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request); // Tenta recuperar o token da requisição
        if (token != null) {
            var subject = service.validateToken(token); // Valida o token e extrai o sujeito (e-mail do usuário)
            UserDetails user = repository.findByEmail(subject); // Busca os detalhes do usuário com base no e-mail extraído do token
            // Cria um objeto de autenticação para o usuário, passando os papéis e permissões do usuário
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // Define a autenticação no contexto de segurança para que ela esteja disponível durante o ciclo de vida da requisição
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para extrair o token do cabeçalho Authorization da requisição HTTP
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization"); // Recupera o cabeçalho Authorization
        if (authHeader == null) return null; // Se não houver o cabeçalho, retorna null
        return authHeader.replace("Bearer ", ""); // Remove o prefixo "Bearer " do token e retorna o valor
    }
}
