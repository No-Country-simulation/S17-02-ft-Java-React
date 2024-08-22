import React from "react";
import Login from "../login/login.tsx";

const Navbar: React.FC = () => {
  return (
    <nav>
      <ul>
        <li>
          <a href="#home">Inicio</a>
        </li>
        <li>
          <a href="#about">Acerca de</a>
        </li>
        <li>
          <a href="#services">Servicios</a>
        </li>
        <li>
          <a href="#contact">Contacto</a>
        </li>
      </ul>
      <div className="login-container">
        <Login />
      </div>
    </nav>
  );
};

export default Navbar;
