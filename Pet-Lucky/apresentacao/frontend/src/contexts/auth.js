/*
Aqui o contexto é como se fosse uma 'memória central' disponível para gravar certas informações globais, por exemplo, um usuário logado.
Esse contexto deverá ser importado no arquivo de rotas e deve envolver todas as rotas que precisam ter acesso aos dados desse contexto. Usaremos o localStorage para armazenar os dados.
*/

import { createContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const navigate = useNavigate();

    // nesse primeiro momento, usamos dados fixo pra login;
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const recoveredUser = localStorage.getItem('user');

        if (recoveredUser) {
            setUser(JSON.parse(recoveredUser));
        }

        // pra evitar que a página carregue sem carregar tudo que precisa;
        setLoading(false);
    }, []);

    const login = (email, password) => {
        console.log('login auth', { email, password });

        // criar a API de login
        const loggedUser = {
            id: '123',
            email
        };

        // salvar o usuário no localStorage
        localStorage.setItem('user', JSON.stringify(loggedUser));

        if (password === '12345aA') {
            setUser(loggedUser);
            navigate('/home');
        }
    };

    const logout = () => {
        console.log('logout');
        localStorage.removeItem('user');
        setUser(null);
        navigate('/');
    };

    return (
        <AuthContext.Provider value={{ authenticated: !!user, user, loading, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};