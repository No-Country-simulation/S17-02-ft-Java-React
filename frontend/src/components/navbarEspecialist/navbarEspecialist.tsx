import React from "react";
import { Link } from "react-router-dom";

const NavbarEspecialista: React.FC = () => {
  return (
    <nav>
      <div>
        <h1>Especialistas</h1>
        <ul>
          <li>
            <Link to="/">Inicio</Link>
          </li>
          <li>
            <Link to="/especialistas">Especialistas</Link>
          </li>
          <li>
            <Link to="/citas">Citas</Link>
          </li>
          <li>
            <Link to="/historial">Historial</Link>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default NavbarEspecialista;
