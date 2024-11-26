import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';

const Profile = () => {
    const [profileData, setProfileData] = useState(null);
    const [error, setError] = useState(null);

    // Supondo que o ID do usuário esteja armazenado no localStorage após o login
    const userId = localStorage.getItem('userId');

    useEffect(() => {
        const fetchProfileData = async () => {
            try {
                const response = await fetch(`/api/pessoas/id/${userId}`);
                if (!response.ok) {
                    throw new Error('Erro ao obter os dados do perfil.');
                }
                const data = await response.json();
                setProfileData(data);
            } catch (error) {
                setError(error.message);
            }
        };

        if (userId) {
            fetchProfileData();
        } else {
            setError('Usuário não autenticado.');
        }
    }, [userId]);

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
            animate={{ width: "auto", transition: { duration: 0.5 } }}
            exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
        >
            <h1>Perfil do Usuário</h1>
            <p><strong>Nome:</strong> {profileData.nome}</p>
            <p><strong>Telefone:</strong> {profileData.telefone}</p>
            <p><strong>Rua:</strong> {profileData.rua}</p>
            <p><strong>Cidade:</strong> {profileData.cidade}</p>
            <p><strong>Email:</strong> {profileData.email}</p>
        </motion.section>
    );
};

export default Profile;
