import AuthService from "@/service/AuthService"
import { Link, NavLink, useNavigate } from "react-router-dom";
import logo from "@/assets/utfpr-logo.png";

export function NavBar() {
    const navigate = useNavigates();

    const onClickLogout = () => {
        AuthService.logout();

        navigate("/login");
    }

    return (

        <div className="bg-white shadow-sm mb-2">
            <div className="container">
                <nav className="navbar navbar-light navbar-expand">

                    {/* Logo da aplicação que redireciona para a página inicial */}
                    <Link to="/" className="navbar-brand">
                        <img src={logo} width="60" alt="UTFPR" />
                    </Link>

                    {/* Lista de itens de navegação */}
                    <ul className="navbar-nav me-auto mb-2 mb-md-0">

                        {/* Link para a página inicial */}
                        <li className="nav-item">
                            <NavLink
                                to="/"
                                className={(navData) =>
                                    navData.isActive ? "nav-link active" : "nav-link"
                                }
                            >
                                Home
                            </NavLink>
                        </li>

                        {/* Link para a página de categorias */}
                        <li className="nav-item">
                            <NavLink
                                to="/categories"
                                className={(navData) =>
                                    navData.isActive ? "nav-link active" : "nav-link"
                                }
                            >
                                Categorias
                            </NavLink>
                        </li>

                        {/* Link para a página de produtos */}
                        <li className="nav-item">
                            <NavLink
                                to="/products"
                                className={(navData) =>
                                    navData.isActive ? "nav-link active" : "nav-link"
                                }
                            >
                                Produtos
                            </NavLink>
                        </li>

                        {/* Link para a página de produtos (versão 2) */}
                        <li className="nav-item">
                            <NavLink
                                to="/products-v2"
                                className={(navData) =>
                                    navData.isActive ? "nav-link active" : "nav-link"
                                }
                            >
                                Produtos V2
                            </NavLink>
                        </li>

                        {/* Botão de logout */}
                        <li className="nav-item">
                            <button className="btn btn-light" onClick={onClickLogout}>
                                &times; Sair
                            </button>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}