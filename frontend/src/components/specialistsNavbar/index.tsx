import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context";

const NavbarEspecialista: React.FC = () => {
  const { token, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    logout();
    navigate("/");
  };

  return (
    <header className="navbar p-4">
      <nav className="bg-body-tertiar">
        <button className="badge">HeyDoc!</button>

        <div className="d-flex gap-2">
          <div className="d-flex flex-column flex-md-row justify-content-center gap-4">
            <Link to="/">
              <button className="btn-navbar-prof">Inicio</button>
            </Link>
            <Link to="/especialistas">
              <button className="btn-navbar-prof">Especialistas</button>
            </Link>
            <Link to="/citas">
              <button className="btn-navbar-prof">Citas</button>
            </Link>
            <Link to="/historial">
              <button className="btn-navbar-prof">Historial</button>
            </Link>
            {token && (
              <button onClick={handleLogout} className="btn-navbar-prof">
                Logout
              </button>
            )}
          </div>
        </div>
      </nav>
    </header>
  );
};

export default NavbarEspecialista;
