import React from "react";
import { Link } from "react-router-dom";

const Navbar: React.FC = () => {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Inicio</Link>
        </li>
        <li>
          <Link to="#about">Acerca de</Link>
        </li>
        <li>
          <Link to="#services">Servicios</Link>
        </li>
        <li>
          <Link to="#contact">Contacto</Link>
        </li>
        <li>
          <Link to="/login">Login</Link>
        </li>
        <li>
          <Link to="/video-call">Video Llamada</Link>{" "}
        </li>
        <li>
          <Link to="/profile">Ver Perfil</Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
