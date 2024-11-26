package br.edu.utfpr.pb.pw44s.server.validation;

import br.edu.utfpr.pb.pw44s.server.annotation.UniqueUsername;
import br.edu.utfpr.pb.pw44s.server.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    public UniqueUsernameValidator() {
        // Construtor padrão necessário
    }
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || username.trim().isEmpty()) {
            return true; // Permite valores nulos ou vazios
        }
        return !userRepository.existsByUsername(username);
    }
}