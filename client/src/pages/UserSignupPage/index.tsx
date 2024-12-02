/*  
  O ChangeEvent será utilizado para tipar o parâmetro do método onChange, que será utilizado
  para recuperar os valores digitados nos campos de texto ao cadastrar um novo usuário.
  O hook useState será utilizado para manter os valores informados pelo usuário nos campos
  de texto no estado (state) da aplicação.
*/

import './style.css'; // Importa o arquivo de estilo CSS
import { ChangeEvent, useState } from 'react'; // Importa React e hooks necessários
import { Link, useNavigate } from 'react-router-dom';
import { ApiResponse, IUserSignup } from '@/commons/interfaces';
import AuthService from '@/service/AuthService';
import { AxiosResponse } from 'axios';

export function UserSignUpPage() {
    /* Criação de um objeto chamado `form` para armazenar o username e passord do usuário*/
    /* useState: Inicializa o estado do formulário com valores vazios para os campos displayName, username e password */
    const [form, setForm] = useState({
        displayName: "",
        username: "",
        password: "",
    });

    /* Criação de um objeto chamado `errors` para armazenar os erros referente a displayName, username e passord do usuário*/
    /* useState: Inicializa o estado para armazenar mensagens de erro relacionadas a cada campo */
    const [errors, setErros] = useState({
        displayName: "",
        username: "",
        password: "",
    });

    const navigate = useNavigate();

    const [pendingApiCall, setPendingApiCall] = useState(false);
    const [apiError, setApiError] = useState("");
    const [apiSuccess, setApiSuccess] = useState("");


    /* Função chamada sempre que ocorre uma mudança em algum campo de input. 
      A cada alteração no campo de entrada, ela atualiza o estado de 'form' e limpa os erros associados ao campo */
    const onChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { value, name } = event.target; // Obtém o valor e o nome do campo que disparou o evento

        /* Atualiza o estado do formulário mantendo os valores anteriores e modificando apenas o campo editado */
        setForm((previousForm) => ({
            ...previousForm, // Mantém os valores anteriores de 'form'
            [name]: value, // Atualiza o campo específico com o novo valor
        }));

        /* Limpa o erro do campo editado */
        setErros((previousErrors) => ({
            ...previousErrors,
            [name]: undefined, // Limpa o erro apenas do campo que está sendo editado
        }));
        setApiError("");
    };

    /* Função assíncrona executada quando o botão "Cadastrar" é clicado */
    const onClickSignup = async () => {
        setPendingApiCall(true);

        /* Monta o objeto com os valores do formulário que será enviado ao back-end */
        const user: IUserSignup = {
            displayName: form.displayName,
            username: form.username,
            password: form.password,
        };

        console.log("Meu objeto: " + user.displayName + " - " + user.username + " - " + user.password);

        const response: AxiosResponse<ApiResponse> = await AuthService.signup(user);
        if (response.status === 200 || response.status === 201) {
            setApiSuccess("Cadastro com sucesso");
            setTimeout(() => {
                navigate("/login");
            }, 3000)

        } else {
            setApiError("Falha ao cadastrar o usuário!");
            if (response.data.validationErrors) {
                setErros(response.data.validationErrors)
            }
            setPendingApiCall(false);
        }

    };

    /* Retorna o JSX que renderiza o formulário de cadastro */
    return (
        <>
            <main className="form-signup w-100 m-auto">
                <form>
                    {/* Título do formulário */}
                    <div className="text-center">

                        {/*h3:  classe de tamanho de texto equivalente ao elemento <h3>
            mb-3: "margin-bottom-3", adiciona uma margem inferior equivalente a 1rem (16px por padrão). 
            O valor 3 está relacionado à escala de espaçamento do Bootstrap, que vai de 0 (sem margem) a 5 (margem maior)
            fw-normal: "font-weight normal", define o peso da fonte como normal (geralmente equivalente a 400 em CSS)*/}

                        <h1 className="h3 mb-3 fw-normal">User Signup Page</h1>
                    </div>

                    {/* Campo para o nome do usuário */}
                    <div className="form-floating mb-3">
                        {/*input utilizado para informar o nome do usuário. Nos atributos onChange e value são informados o método que trata a atualização do state e a ligação com o valor armazenado no state, respectivamente.*/}
                        <input
                            id="displayName"
                            name="displayName"
                            className={"form-control " + (errors.displayName ? "is-invalid" : "")}
                            //is-invalid: classe usada para indicar campos de formulário com erro ou inválidos.
                            //Aplica uma borda vermelha e exibe mensagens de erro associadas, quando combinada com uma classe como invalid-feedback
                            type="text"
                            placeholder="Informe o seu nome"
                            onChange={onChange}
                        />
                        <label htmlFor="displayName">Informe seu nome</label>
                        {/* Exibe mensagem de erro, caso exista */}
                        {errors.displayName && (<div className="invalid-feedback">{errors.displayName}</div>)}
                    </div>

                    {/* Campo para o username */}
                    <div className="form-floating mb-3">
                        <input
                            id="username"
                            name="username"
                            type="text"
                            className={"form-control " + (errors.username ? "is-invalid" : "")}
                            placeholder="Informe o seu usuário"
                            onChange={onChange}
                        />
                        <label htmlFor="username">Informe o usuário</label>
                        {errors.username && (<div className="invalid-feedback">{errors.username}</div>)}
                    </div>

                    {/* Campo para a senha */}
                    <div className="form-floating mb-3">
                        <input
                            id="password"
                            name="password"
                            type="password" // Tipo password para ocultar os caracteres digitados
                            className={"form-control " + (errors.password ? "is-invalid" : "")}
                            placeholder="Informe a sua senha"
                            onChange={onChange}
                        />
                        <label htmlFor="password">Informe a senha</label>
                        {errors.password && (<div className="invalid-feedback">{errors.password}</div>)}
                    </div>

                    {apiError && (
                        <div className="col-12 mb-3">
                            <div className="alert alert-danger">{apiError}</div>
                        </div>
                    )}
                    {apiSuccess && (
                        <div className="col-12 mb-3">
                            <div className="alert alert-success">{apiSuccess}</div>
                        </div>
                    )}

                    {/* Botão para enviar o formulário */}
                    <div className="text-center">
                        <button type="button"
                            className="w-100 btn btn-lg btn-primary mb-3"
                            onClick={onClickSignup}
                            disabled={pendingApiCall}>
                            {pendingApiCall && (
                                <div
                                    className="spinner-border 
                                    text-light-spinner
                                    spinner-border-sm mr-sm-1"
                                    role="status">
                                    <span className="visually-hidden">Aguarde</span>
                                </div>
                            )}&nbsp;
                            Cadastrar
                        </button>
                    </div>
                </form>
                <div className="text-center">
                    {/*Link é do react-router-dom, to= é a url*/}
                    <Link to="/login">Ir para a tela de login</Link>
                </div>
            </main >
        </>
    );
}