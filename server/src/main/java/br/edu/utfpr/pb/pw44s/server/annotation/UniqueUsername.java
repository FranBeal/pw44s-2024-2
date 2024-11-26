package br.edu.utfpr.pb.pw44s.server.annotation;

import br.edu.utfpr.pb.pw44s.server.validation.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Especifica que é uma anotação de validação
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD) // Pode ser usada em campos
@Retention(RetentionPolicy.RUNTIME)  // Disponível em tempo de execução
public @interface UniqueUsername {
    String message() default "Esse usuário já está sendo utilizado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
