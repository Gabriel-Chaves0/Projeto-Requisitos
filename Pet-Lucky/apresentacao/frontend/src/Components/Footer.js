import { useLocation } from "react-router-dom";

const Footer = () => {
  const location = useLocation();

  return (
    <>
      {location.pathname === '/' && <img className="footer__img" src="pets.svg" alt="" aria-hidden='true' />}
      <footer className="footer">
        <p>2024 - Desenvolvido para Cadeira de Requisitos.</p>
      </footer>
    </>
  );
};

export default Footer;