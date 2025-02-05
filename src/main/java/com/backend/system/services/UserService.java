package com.backend.system.services;

import com.backend.system.models.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.backend.system.models.domain.user.User;
import com.backend.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    // Cria um novo usuário no banco de dados registrando as informações fornecidas.
    public ResponseEntity<UserDTO> create(User user) throws RuntimeException{
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("A user with this email already exists");
        } else {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            repository.save(user);
            UserDTO dto = new UserDTO(user.getUsername(),user.getEmail(), user.getPassword());
            return ResponseEntity.ok(dto);
        }
    }

    // Retorna uma nova instância do BCryptPasswordEncoder, utilizado para codificar senhas de forma segura.
    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
