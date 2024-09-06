import React from "react";
import { Link } from "react-router-dom";

const NavbarPacientes: React.FC = () => {
  return (
    <nav>
      <div>
        <h1>Pacientes</h1>
        <ul>
          <li>
            <Link to="/">Inicio</Link>
          </li>
          <li>
            <Link to="/pacientes">Pacientes</Link>
          </li>
          <li>
            <Link to="/reportes">Reportes</Link>
          </li>
          <li>
            <Link to="/configuracion">Configuración</Link>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default NavbarPacientes;
