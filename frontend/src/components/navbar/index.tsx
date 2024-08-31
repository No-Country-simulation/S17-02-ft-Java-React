import { Link } from "react-router-dom";

export const Navbar = () => {
  return (
    <nav className="navbar">
      <ul className="d-flex justify-content-center">
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
        {/* <li>
          <Link to="/video-call">Video Llamada</Link>{" "}
        </li> */}
      </ul>
    </nav>
  );
};
