package br.edu.utfpr.pb.pw44s.server.annotation;

// Importa a classe do validador que implementará a lógica de validação personalizada
import br.edu.utfpr.pb.pw44s.server.validation.UniqueUsernameValidator;

// Importa a anotação que identifica uma constraint de validação
import jakarta.validation.Constraint;

// Importa a interface usada para fornecer informações adicionais sobre a validação
import jakarta.validation.Payload;

// Importa as anotações para definir o alvo e a política de retenção da anotação
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Declara que esta é uma anotação de validação e associa a ela uma classe validadora
@Constraint(validatedBy = UniqueUsernameValidator.class)

// Define que esta anotação pode ser aplicada apenas a campos (propriedades de classes)
@Target(ElementType.FIELD)

// Especifica que esta anotação será mantida em tempo de execução, permitindo que o framework de validação a utilize
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    // Define a mensagem padrão de erro que será exibida quando a validação falhar
    String message() default "Esse usuário já está sendo utilizado";

    // Permite agrupar validações relacionadas (pouco usado, mas necessário para conformidade com o Bean Validation)
    Class<?>[] groups() default {};

    // Permite transportar informações adicionais sobre a validação, como metadados
    Class<? extends Payload>[] payload() default {};
}
