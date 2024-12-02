export interface IUserSignup {
    displayName: string;
    username: string;
    password: string;
}

// Interface para tipar a resposta esperada da API
export interface ApiResponse {
    message: string; // Mensagem de sucesso ou erro
    validationErrors: any; // Objeto contendo erros de validação específicos para os campos
}

export interface IUserLogin{
    username: string;
    password: string;
}
