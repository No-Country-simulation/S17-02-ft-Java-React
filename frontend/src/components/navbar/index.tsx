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
          {!token ? (
            <>
              <Link to="/registerespecialist">
                <button className="btn-navbar-prof">
                  Registro profesionales
                </button>
              </Link>
              <div className="d-flex flex-column flex-md-row justify-content-center gap-4">
                <Link to="/registeruser">
                  <button className="btn-navbar-pct">Registro pacientes</button>
                </Link>
                <Link to="/login">
                  <button className="btn-navbar-prof">Login</button>
                </Link>
              </div>
            </>
          ) : (
            <button onClick={handleLogout} className="btn-navbar-prof">
              Logout
            </button>
          )}
        </div>
      </nav>
    </header>
  );
};

export default NavBar;
