package br.edu.utfpr.pb.pw44s.server.validation;

// Importa a anotação personalizada que será validada por esta classe
import br.edu.utfpr.pb.pw44s.server.annotation.UniqueUsername;

// Importa o repositório de usuários para verificar a existência do nome de usuário no banco de dados
import br.edu.utfpr.pb.pw44s.server.repository.UserRepository;

// Importa as interfaces necessárias para criar um validador personalizado
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Importa a anotação do Spring para marcar esta classe como um componente gerenciado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Marca a classe como um componente Spring para que possa ser injetada e gerenciada pelo contexto da aplicação
@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    // Injeção do repositório de usuários para verificar a existência de nomes de usuários
    @Autowired
    private UserRepository userRepository;

    // Construtor padrão, necessário para inicialização correta do Spring
    public UniqueUsernameValidator() {
        // Construtor padrão necessário
    }

    // Implementa o metodo que valida a anotação @UniqueUsername em um campo de String
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // Permite valores nulos ou vazios (a validação de presença deve ser feita por outra anotação)
        if (username == null || username.trim().isEmpty()) {
            return true; // Permite valores nulos ou vazios
        }
        // Retorna false se o nome de usuário já existe no banco de dados, true caso contrário
        return !userRepository.existsByUsername(username);
    }
}