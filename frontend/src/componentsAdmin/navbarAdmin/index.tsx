// src/components/navbarAdmin.tsx
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context";

export const NavbarAdmin: React.FC = () => {
  const { token, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = (event: React.MouseEvent<HTMLAnchorElement>) => {
    event.preventDefault();
    logout();
    navigate("/");
  };

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
        {token ? (
          <>
            <li>
              <Link to="/video-call">Video Llamada</Link>
            </li>
            <li>
              <a href="#" onClick={handleLogout}>
                Logout
              </a>{" "}
            </li>
          </>
        ) : (
          <li>
            <Link to="/login">Login</Link>
          </li>
        )}
      </ul>
    </nav>
  );
};
