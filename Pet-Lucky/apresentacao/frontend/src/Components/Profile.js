import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import api from '../services/api'

const Profile = () => {
    const [profileData, setProfileData] = useState(null);
    const [error, setError] = useState(null);

    // Obtenha o CPF do localStorage
    const cpf = localStorage.getItem('cpf');

    useEffect(() => {
        const fetchProfileData = async () => {
            try {
                // Faz uma requisição GET para o endpoint /pessoas/cpf/{cpf}
                const response = await api.get(`/pessoas/cpf/${cpf}`);
                setProfileData(response.data);
            } catch (error) {
                console.error('Erro na requisição:', error);
                if (error.response) {
                    // A requisição foi feita e o servidor respondeu com um status fora do intervalo 2xx
                    if (error.response.status === 404) {
                        setError('Usuário não encontrado.');
                    } else {
                        setError(error.response.data.message || 'Erro ao obter os dados do perfil.');
                    }
                } else if (error.request) {
                    // A requisição foi feita mas nenhuma resposta foi recebida
                    setError('Erro ao conectar com o servidor.');
                } else {
                    // Algo aconteceu na configuração da requisição que acionou um erro
                    setError('Erro ao obter os dados do perfil.');
                }
            }
        };

        if (cpf) {
            fetchProfileData();
        } else {
            setError('Usuário não autenticado.');
        }
    }, [cpf]);

    if (error) {
        return <p className="error">{error}</p>;
    }

    if (!profileData) {
        return <p>Carregando...</p>;
    }

    return (
        <motion.section
            className="profile"
            initial={{ width: 0 }}
            animate={{ width: 'auto', transition: { duration: 0.5 } }}
            exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
        >
            <h1>Perfil do Usuário</h1>
            <p>
                <strong>Nome:</strong> {profileData.nome}
            </p>
            <p>
                <strong>Telefone:</strong> {profileData.telefone}
            </p>
            <p>
                <strong>Rua:</strong> {profileData.rua}
            </p>
            <p>
                <strong>Cidade:</strong> {profileData.cidade}
            </p>
            <p>
                <strong>Email:</strong> {profileData.email}
            </p>
            <p>
                <strong>CPF:</strong> {profileData.cpf}
            </p>
        </motion.section>
    );
};

export default Profile;
