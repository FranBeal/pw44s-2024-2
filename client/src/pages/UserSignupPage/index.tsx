import React from 'react';
import axios, { AxiosError, AxiosResponse } from 'axios'; // Importa o Axios e seus tipos para requisições HTTP
import { ChangeEvent, useState } from 'react';
import './style.css';

export function UserSignUpPage() {
     // Cria o state 'form' para armazenar os dados do formulário, inicializado com campos vazios
     const [form, setForm] = useState({
        displayName: '',
        username: '',
        password: '',
        });

      const onChange = (event: ChangeEvent<HTMLInputElement>) => {
             const { value, name } = event.target; // Extrai 'value' e 'name' do evento para obter o valor do campo e seu nome
            
            /* Atualiza o estado 'form' com o novo valor do campo editado, 
            preservando os outros campos */
            setForm((previousForm) => {
                return {
                    ...previousForm, // Mantém os valores anteriores de 'form'
                    [name]: value, // Atualiza o campo específico com o novo valor
                }
             });
        }

        const onClickSignup = () => {
            /*Montar um objeto com as pprpriedades que serão enviadas para o back-end*/
             const user = {
                displayName: form.displayName,
                username: form.username, 
                password: form.password,
             }
             axios.post("http://localhost:8080/users", user)

          };        

    return (
     <>
        <main className="form-signup xw-100 m-auto">
            <form>
                <div className="text-center">
                    <h1 className="h3 mb-3 fw-normal">Novo Usuário</h1>
                </div>
                
                <div className="form-floating">
                    <input
                        id="displayName"
                        name="displayName"
                        className="form-control"
                        type="text"
                        placeholder="Informe o seu nome"
                        onChange={onChange} // A função onChange é chamada sempre que o valor do campo é alterado
                    />
                    <label htmlFor="displayName">Informe seu nome</label>
                </div>
     
                <div className="form-floating">
                    <input
                        id="username"
                        name="username"
                        className="form-control"
                        type="text"
                        placeholder="Informe o seu usuário"
                        onChange={onChange}
                    />
                    <label htmlFor="username">Informe o usuário</label>
                </div>

                <div className="form-floating">
                    <input
                        id="password"
                        name="password"
                        className="form-control"
                        type="password"
                        placeholder="Informe a sua senha"
                        onChange={onChange}
                    />
                    <label htmlFor="password">Informe a senha</label>
                
                </div>
                <button type="button" className="w-100 btn btn-lg btn-primary mb-3" onClick={onClickSignup}>
                   Cadastrar
                </button>
            </form>
        </main>
      </>
     );
    }