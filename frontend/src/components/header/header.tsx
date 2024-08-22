import React from "react";

const Header: React.FC = () => {
  return (
    <header>
      <h1>Mi Aplicación</h1>
      <nav>
        <ul>
          <li>
            <a href="#home">Inicio</a>
          </li>
          <li>
            <a href="#about">Acerca de</a>
          </li>
          <li>
            <a href="#contact">Contacto</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
