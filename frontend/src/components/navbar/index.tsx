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

  const handleHomeRedirect = () => {
    navigate("/");
  };

  return (
    <header className="navbar">
      <nav className="bg-body-tertiar">
        <button className="badge" onClick={handleHomeRedirect}>
          HeyDoc!
        </button>

        <div className="button-container-nav">
          {!token ? (
            <>
              <Link to="/registerespecialist">
                <button className="btn-navbar-prof">
                  Registro profesionales
                </button>
              </Link>
              
                <Link to="/registeruser">
                  <button className="btn-navbar-pct" type="button" >Registro pacientes</button>
                </Link>
                <Link to="/login">
                  <button className="btn-navbar-prof">Login</button>
                </Link>
              
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
