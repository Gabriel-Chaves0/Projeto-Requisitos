import { motion } from 'framer-motion';
import { useState, useEffect } from 'react';
import { pets as staticPets } from '../data/data.js'; // Renomeie a importação para 'staticPets'
import CardPet from './CardPet.js';
import api from "../services/api";

const Home = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [pets, setPets] = useState([]); // Este estado armazena os pets da API
  const [error, setError] = useState(null);

  // Função que faz a requisição
  const getPet = async () => {
    try {
      const response = await api.get('/api/animais/adotados');
      setPets(response.data); // Atualiza o estado com os pets recebidos
    } catch (error) {
      console.error('Failed to fetch adopted pets:', error.message || error);
      setError('Failed to load pets'); // Armazena a mensagem de erro no estado
    }
  };

  // Chama a função getPet quando o componente é montado
  useEffect(() => {
    getPet();
  }, []);

  // Filtra os pets baseados no termo de busca
  const filteredPets = pets.filter(pet =>
    pet.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <motion.section
      className='home'
      initial={{ width: 0 }}
      animate={{ width: "auto", transition: { duration: 0.5 } }}
      exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
    >
      <p>Olá! <br /> Veja os amigos que já foram adotados!</p>

      {/* Input de busca */}
      <input
        type="text"
        placeholder="Busque pelo nome do animal..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="search-input"
      />

      <div className='cards'>
        {
          filteredPets.map((pet, i) => (
            <CardPet
              idade={pet.idadeAnimal}
              porte={pet.porte}
              especie={pet.especie}
              sexo={pet.sexo}
              nome={pet.nomeAnimal}
              img={pet.img}
              key={i}
            />
          ))
        }
        {filteredPets.length === 0 && !error && <p>Nenhum animal encontrado com esse nome.</p>}
        {error && <p>{error}</p>}
      </div>
    </motion.section>
  );
};

export default Home;
