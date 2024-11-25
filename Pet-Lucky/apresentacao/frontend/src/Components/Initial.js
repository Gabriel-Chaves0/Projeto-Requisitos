import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Helmet } from 'react-helmet';
import useMediaQuery from '../hooks/useMediaQuery';
import LogoImagem from '../assets/logoName.svg';

const Home = () => {
  const matches = useMediaQuery('(max-width: 767px)');

  return (
    <motion.section className='initial' initial={{ width: 0 }} animate={{ width: "auto", transition: { duration: 0.5 } }} exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}>
      <Helmet>
  <style>{`
    body {
    width: 100%;
    height: 100%;
    position: relative;
    background-color: #b6c687;
    }
  `}</style>
      </Helmet>
      <img src={LogoImagem} alt="Logo PetLucky" style={{ width: '500px', height: 'auto', marginTop: '50px' }} />
      <h3>Boas-vindas!</h3>
      <p>
        {matches ? 'Que tal mudar sua vida adotando seu novo melhor amigo? Vem com a gente!' : 'Adotar pode mudar uma vida. Que tal buscar seu novo melhor amigo hoje? Vem com a gente!'}
      </p>
      <div className='home__buttons'>
        <Link className='button' to='/home'>PETS QUE JÁ FORAM ADOTADOS</Link>
        <Link className='button' to='/home'>ABRIGOS COM PETS DISPONÍVEIS</Link>
        <div>
          <a className='initial__link' href="/cadastro">Cadastrar</a>
          <span style={{ fontSize: 14, padding: '0 1rem', color: '#fff' }}>ou</span>
          <a className='initial__link' href="/login">Fazer login</a>
        </div>
      </div>
    </motion.section >
  );
};

export default Home;