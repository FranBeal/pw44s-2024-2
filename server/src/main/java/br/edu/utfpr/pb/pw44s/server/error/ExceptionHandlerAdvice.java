package br.edu.utfpr.pb.pw44s.server.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    // Trata as exceções que irão ocorrer devido a anotação @Valid
    @ExceptionHandler({MethodArgumentNotValidException.class})
    // Define o status HTTP como 400 (Bad Request)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(MethodArgumentNotValidException exception,
                                               HttpServletRequest request) {
        // Recupera os resultados da validação que falhou
        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        // Preenche o mapa de erros de validação com os detalhes
        for (FieldError fieldError : result.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        // Retorna o objeto ApiError com os erros de validação
        return new ApiError(HttpStatus.BAD_REQUEST.
                value(),
                "Validation error!",
                request.getServletPath(),
                validationErrors);
    }
/*
    // Trata exceções do tipo IllegalStateException
    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(IllegalStateException exception,
                                               HttpServletRequest request) {
        // Retorna o objeto ApiError com a mensagem de erro
        return new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation error!",
                request.getServletPath(), null);
    }

    // Trata exceções do tipo HttpMessageNotReadableException
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(HttpMessageNotReadableException exception,
                                               HttpServletRequest request) {
        // Retorna o objeto ApiError com a mensagem de erro
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error!",
                request.getServletPath(),
                null);
    }

 */
}
