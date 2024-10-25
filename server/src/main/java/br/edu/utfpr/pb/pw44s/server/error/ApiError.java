package br.edu.utfpr.pb.pw44s.server.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {
    // Armazena o timestamp (data/hora) de quando o erro ocorreu
    private long timestamp = new Date().getTime();

    // Código de status HTTP (ex: 400, 404, 500)
    private int status;

    // Mensagem descritiva do erro
    private String message;

    // URL da requisição onde o erro ocorreu
    private String url;

    // Erros de validação dos campos, se houver
    private Map<String, String> validationErrors;

    // Construtor que inicializa todos os atributos, incluindo erros de validação
    public ApiError(int status, String message, String url, Map<String, String> validationErrors) {
        this.status = status;
        this.message = message;
        this.url = url;
        this.validationErrors = validationErrors;
    }
    // Construtor que inicializa atributos sem erros de validação
    public ApiError(String message, String url, Integer status) {
        this.message = message;
        this.url = url;
        this.status = status;
    }
}
