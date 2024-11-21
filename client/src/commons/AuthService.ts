import { IUserSignUp } from  '../commons/interfaces';
import { api } from  '../lib/axios'

const signup = (user: IUserSignUp) => {
	return api.post('/users', user);
}

const AuthService = {
	signup,
}

export  default AuthService;