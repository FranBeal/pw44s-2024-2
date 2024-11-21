/* 
	O ChangeEvent será utilizado para tipar o parâmetro do método onChange, que será utilizado para recuperar os valores digitados nos campos de texto ao cadastrar um novo usuário.
	O hook[4] useState será utilizado para manter os valores informados pelo usuário nos campos de texto no estado (state[3]) da aplicação.	
	IUserSignUp - interface utilizada para tipar os objeto que armazena os dados de usuário
	AuthService - contém as funções para realizar as requisições HTTP à API REST. No caso do cadastro de usuário uma requisição do tipo HTTP POST
*/
import { ChangeEvent, useState } from  'react'
import { IUserSignUp } from  '../../commons/interfaces';
import AuthService from  '../../service/AuthService';

export  function UserSignUpPage() {
	/* Criação de um objeto chamado `form` no state para armazenar o username e passord do usuário */
	const [form, setForm] = useState({
		displayName: '',
		username: '',
		password: '',
	});
	/* Criação de um objeto chamado `errors` no state para armazenar os erros de validação retornados pelo servidor */
	const [errors, setErrors] = useState({
		displayName: '',
		username: '',
		password: '',
	});
	/* função criada para monitorar o evento Change dos componentes input */
	const onChange = (event: ChangeEvent<HTMLInputElement>) => {
		const { value, name } = event.target;
		/* Recuperar o valor do state e altera apenas a propriedade relacionada ao input que está sendo editado
		*/
		setForm((previousForm) => {
			return {
				...previousForm,
				[name]: value,
			}
		});
		/* Limpa o valor do erro relacionado à propriedade do input que está sendo editada */
		setErrors((previousErrors) => {
			return {
				...previousErrors,
				[name]: '',
			}
		});
	}
	/* trata o click o botão para cadastrar um novo usuário */
	const onClickSignUp = () => {
		/* recupera o valor do state e cria um objeto do tipo IUserSignUp */
		const userSignUp: IUserSignUp = {
			displayName: form.displayName,
			username: form.username,
			password: form.password,
		};
		/* Chama o método signup do service AuthService, passando o usuário que será enviado via POST para API */
		AuthService.signup(userSignUp)
			.then((response) => {
				/* Em caso de sucesso imprime o resultado no console */
				console.log(response);
			})
			.catch((errorResponse) => {
				/* Em caso de erro imprime o resultado no console e preenche o conjunto de erros armazenado no state com os dados vindos da validação realizada na API. */
				console.log(errorResponse);
				if (errorResponse.response.data.validationErrors) {
					setErrors(errorResponse.response.data.validationErrors);
				}
			}
		);
	}
	/*Retorna o TSX com o formulário de cadastro. */
	return (
		<main  className="container">
		<form>
			<div  className="text-center">
				<h1  className="h3 mb-3 fw-normal">User Signup Page</h1>
			</div>
			<div  className="form-floating mb-3">
				{/*input utilizado para informar o nome do usuário. Nos atributos onChange e value são informados o método que trata a atualização do state e a ligação com o valor armazenado no state, respectivamente.*/}
				<input 
					type="text"
					className={errors.displayName ? "form-control is-invalid" : "form-control"}
					placeholder="Informe o seu nome"
					onChange={onChange}
					value={form.displayName}
					name="displayName"
				/>
				<label>Nome</label>
				{errors.displayName && <div  className="invalid-feedback">{errors.displayName}</div>}
			</div>
			<div  className="form-floating mb-3">
				<input
					type="text"
					className="form-control"
					placeholder="Informe o seu usuário"
					onChange={onChange}
					value={form.username}
					name="username"
				/>
				<label>Usuário</label>
				{errors.username && <div  className="invalid-feedback">{errors.username}</div>}
			</div>
			<div  className="col-12 mb-3">
				<input
					type="password"
					className="form-control"
					placeholder="Informe a sua senha"
					onChange={onChange}
					value={form.password}
					name="password"  
				/>
				<label>Senha</label>
				{errors.password&& <div  className="invalid-feedback">{errors.password}</div>}
			</div>
			<div  className="text-center">
				{/* Ao clicar no botão é chamado o método onClickSignUp */}
				<button
					className="btn btn-primary"
					onClick={onClickSignUp}
				>Cadastrar</button>
			</div>
		</form>
        </main>
	)
}