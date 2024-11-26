import { motion } from 'framer-motion';
import { useState } from 'react';
import { pets } from '../data/data.js';
import CardPet from './CardPet.js';

const Home = () => {
  const [searchTerm, setSearchTerm] = useState('');

  const filteredPets = pets.filter(pet =>
    pet.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const getPet = async() => {
    try {
        return await axiosInstance.get('/api/animais/adotados');
    } catch (e) {
        console.log('Error', e);
    }
  }

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
              age={pet.idade}
              size={pet.porte}
              behavior={pet.especie}
              city={pet.sexo}
              name={pet.nome}
              img={pet.img}
              key={i}
            />
          ))
        }
        {filteredPets.length === 0 && <p>Nenhum animal encontrado com esse nome.</p>}
      </div>
    </motion.section>
  );
};

export default Home;
