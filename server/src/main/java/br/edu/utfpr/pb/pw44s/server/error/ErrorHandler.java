package br.edu.utfpr.pb.pw44s.server.error;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class ErrorHandler implements ErrorController {
    // Injeta as informações de erro do Spring
    private final ErrorAttributes errorAttributes;

    // Construtor que recebe ErrorAttributes para manipular os atributos do erro
    public ErrorHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    // Mapeia a URL "/error" para tratar erros globais não tratados
    @RequestMapping("error")
    public ApiError handlerError(WebRequest webRequest, HttpServletResponse response) {
        // Obtém os atributos do erro a partir do WebRequest
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));

        // Se o status não for encontrado nos atributos, utiliza o status da resposta
        if (attributes.get("status") == null) {
            attributes.put("status", response.getStatus());
        }

        // Retorna o objeto ApiError com as informações do erro
        return new ApiError( (String) attributes.get("message"),
                (String) attributes.get("path"),
                (Integer) attributes.get("status")
        );
    }
}
