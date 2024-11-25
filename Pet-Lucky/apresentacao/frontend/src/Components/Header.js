import { useCallback, useContext, useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Menu } from '@headlessui/react';
import userPic from '../assets/user.svg';
import loggedUser from '../assets/logged-user.png';
import Button from './Button';
import { AuthContext } from '../contexts/auth';
import Voluntarios from '../assets/voluntarios.svg';

const Header = () => {
  const location = useLocation();
  const [user, setUser] = useState('');
  const { authenticated, logout } = useContext(AuthContext);

  const handleLogout = useCallback(() => {
    logout();
  }, [logout]);

  useEffect(() => {
    if (location.pathname === '/' || location.pathname === '/login' || location.pathname === '/cadastro') {
      setUser('');
    } else if (authenticated) {
      setUser(
        <Menu>
          <Menu.Button className="menu__button">
            <img className='header__user' src={loggedUser} alt="Usuário" />
          </Menu.Button>
          <Menu.Items className='menu__content'>
            <a className='button' href="/perfil">Ver Perfil</a>
            <Button handleClick={handleLogout} children="Logout"></Button>
          </Menu.Items>
        </Menu>
      );
    } else {
      setUser(
        <Menu>
          <Menu.Button className="menu__button">
            <img className='header__user' src={userPic} alt="Usuário" />
          </Menu.Button>
          <Menu.Items className='menu__content'>
            <a className='button' href="/login">Login</a>
          </Menu.Items>
        </Menu>
      );
    }
  }, [location, handleLogout, authenticated]);

  return (
    <header className='header'>
      <nav>
        <div style={{ display: 'flex', alignItems: 'center', margintTop: '30px', color: '#da828f' }}>
          {/* <img className='header__logo' src={LogoNome}  alt="PetLuck Logo" /> */}
          <Link className='header__home' aria-label='Tela inicial' to="/" ></Link>
          <Link className='header__message' to="/mensagem" aria-label='Ir para Mensagens'></Link>
          <img src={Voluntarios} alt="Voluntários"></img>
          <Link className='header__voluntario' to="/voluntario" aria-label='Ir para tela de voluntários'></Link>
        </div>
        {user}
      </nav>
    </header>

  );
};

export default Header;