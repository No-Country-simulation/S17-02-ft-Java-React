import { Nav } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context";

const NavBar = () => {
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
          <Link to="/registerespecialist">
            <button className="btn-navbar-prof">Soy profesional</button>
          </Link>
          {token ? (
            <>
              <Nav.Link eventKey={2} href="#memes" className="fw-bolder fs-3">
                Video llamadas
              </Nav.Link>
              <button onClick={handleLogout} className="btn btn-secondary">
                Logout
              </button>
            </>
          ) : (
            <>
              <div className="d-flex flex-column flex-md-row justify-content-center gap-4">
                <Link to="/registeruser">
                  <button className="btn-navbar-pct">Soy paciente</button>
                </Link>
              </div>
              <Link to="/login">
                <button className="btn-navbar-prof">Login</button>
              </Link>
            </>
          )}
        </div>
      </nav>
    </header>
  );
};

export default NavBar;
