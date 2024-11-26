import { Route, Routes, useLocation, Navigate } from "react-router-dom";
import { AnimatePresence } from 'framer-motion';
import { useContext } from "react";
import { AuthProvider, AuthContext } from "../contexts/auth";
import Initial from './Initial';
import LoginForm from "./LoginForm.js";
import Home from "./Home.js";
import AnimalFilter from "./AnimalFilter.js";
import RegisterForm from "./RegisterForm.js";
import Header from "./Header";
import Footer from "./Footer";
import Abrigo from "./Abrigo";
import Profile from "./Profile";

const AnimatedRoutes = () => {
  const location = useLocation();

  const Private = ({ children }) => {
    const { authenticated, loading } = useContext(AuthContext);

    if (loading) {
      return <div className="loading">Carregando...</div>;
    }

    if (!authenticated) {
      return <Navigate to='/login' />;
    }

    return children;
  };

  return (
    <AuthProvider>
      <Header />
      <AnimatePresence mode="wait">
        <Routes location={location} key={location.pathname}>
          <Route path='/' element={<Initial />} />
          <Route path='/login' element={<LoginForm />} />
          <Route path='/cadastro' element={<RegisterForm />} />
          <Route path='/home' element={<Home />} />
          <Route path='/filter' element={<Private><AnimalFilter /></Private>} />
          <Route path='/perfil' element={<Private><AnimalFilter /></Private>} />
          <Route path='/abrigo' element={<Abrigo />} />
        </Routes>
      </AnimatePresence>
      <Footer />
    </AuthProvider>
  );
};

export default AnimatedRoutes;
