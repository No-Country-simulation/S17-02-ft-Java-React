import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context";
import { Modal } from "react-bootstrap";
import  Login  from "../login";
import { useState } from "react";

const NavBar = () => {
  const { token, logout } = useAuth();
  const navigate = useNavigate();
  const [showModal, setShowModal] = useState(false);
  const handleLogout = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    logout();
    navigate("/");
  };

  const handleHomeRedirect = () => {
    navigate("/");
  };
  const handleShowModal = () => setShowModal(true);
  const handleCloseModal = () => setShowModal(false);
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
                <Link to="/">
                  <button className="btn-navbar-prof"onClick={handleShowModal}>Login</button>
                </Link>
              
            </>
          ) : (
            <button onClick={handleLogout} className="btn-navbar-prof">
              Logout
            </button>
          )}
        </div>
      </nav>
            <Modal show={showModal} onHide={handleCloseModal}  
            dialogClassName="modal-90w"
            centered>
                    <Login onClose={handleCloseModal} />    
            </Modal>
    </header>
  );
};

export default NavBar;







