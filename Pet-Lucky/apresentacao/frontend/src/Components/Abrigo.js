import { motion } from 'framer-motion';
import { useState } from 'react';
import { abrigos } from '../data/data.js'; // Alteração para "abrigos"
import AbrigoCard from './AbrigoCard.js'; // Alteração para "AbrigoCard"

const Home = () => {
  const [searchTerm, setSearchTerm] = useState('');

  const filteredAbrigos = abrigos.filter(abrigo =>
    abrigo.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <motion.section
      className='home'
      initial={{ width: 0 }}
      animate={{ width: "auto", transition: { duration: 0.5 } }}
      exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
    >
      <p>Olá! <br /> Veja os abrigos que estão cadastrados!</p>

      {/* Input de busca */}
      <input
        type="text"
        placeholder="Busque pelo nome do abrigo..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="search-input"
      />

      <div className='cards'>
        {
          filteredAbrigos.map((abrigo, i) => (
            <AbrigoCard
              city={abrigo.city}
              street={abrigo.street}
              name={abrigo.name}
              img={abrigo.img}
              key={i}
            />
          ))
        }
        {filteredAbrigos.length === 0 && <p>Nenhum abrigo encontrado com esse nome.</p>}
      </div>
    </motion.section>
  );
};

export default Home;
