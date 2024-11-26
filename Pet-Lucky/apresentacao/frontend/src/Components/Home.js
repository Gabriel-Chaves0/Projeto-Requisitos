import { motion } from 'framer-motion';
import { useState, useEffect } from 'react';
import api from "../services/api";

const Home = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [pets, setPets] = useState([]);
  const [error, setError] = useState(null);

  // Função que faz a requisição
  const getPet = async () => {
    try {
      const response = await api.get('/animais/'); // Endpoint para listar animais não adotados
      console.log(response.data); // Verifica o formato dos dados
      const data = Array.isArray(response.data) ? response.data : [];
      setPets(data); // Atualiza o estado com os pets recebidos
    } catch (error) {
      console.error('Failed to fetch pets:', error.message || error);
      setError('Failed to load pets'); // Armazena a mensagem de erro no estado
    }
  };

  // Chama a função getPet quando o componente é montado
  useEffect(() => {
    getPet();
  }, []);

  // Filtra os pets baseados no termo de busca
  const filteredPets = Array.isArray(pets) ? pets.filter(pet =>
      pet.nomeAnimal?.toLowerCase().includes(searchTerm.toLowerCase())
  ) : [];

  return (
      <motion.section
          className='home'
          initial={{ width: 0 }}
          animate={{ width: "auto", transition: { duration: 0.5 } }}
          exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
      >
        <p>Olá! <br /> Veja os amigos disponíveis para adoção!</p>

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
                <div key={i} className="card-pet">
                  {pet.img && <img src={pet.img} alt={`Foto de ${pet.nomeAnimal}`} />}
                  <h3>{pet.nomeAnimal}</h3>
                  <p><strong>Idade:</strong> {pet.idadeAnimal}</p>
                  <p><strong>Porte:</strong> {pet.porte}</p>
                  <p><strong>Espécie:</strong> {pet.especie}</p>
                  <p><strong>Sexo:</strong> {pet.sexo}</p>
                </div>
            ))
          }
          {filteredPets.length === 0 && !error && <p>Nenhum animal encontrado com esse nome.</p>}
          {error && <p>{error}</p>}
        </div>
      </motion.section>
  );
};

export default Home;
