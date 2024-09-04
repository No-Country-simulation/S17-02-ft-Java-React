import { useState } from "react";
import { Container, Navbar, Nav } from "react-bootstrap";
import Modal from "react-modal";
import { Link, useNavigate } from "react-router-dom";
import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
import { useAuth } from "../../context/context";
const NavBar = () => {
  const { token, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    logout();
    navigate("/");
  };
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [activeForm, setActiveForm] = useState<string | null>(null);



  const openModal = (form: string) => {
    setActiveForm(form);
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
    setActiveForm(null);
  };



  return (
    <header className="navbar p-4">
      <nav  className="bg-body-tertiar">
        
          <button className="badge" >HeyDoc!</button>
          
            <div className="d-flex gap-2">
            <Link to="/registerespecialist">
        <button className="btn-navbar-prof">soy profesional</button>
      </Link>  
          {token?   <>
           <Nav.Link eventKey={2} href="#memes" className="fw-bolder fs-3">Video llamadas</Nav.Link>
            <button onClick={handleLogout} className="btn btn-secondary" >Logout</button>
            </> :"" }
              
          
      <div className="d-flex flex-column flex-md-row justify-content-center gap-4">
      <button className="btn-navbar-pct" onClick={() => openModal("user")}>
        {activeForm === "user"
          ? "Cerrar"
          : "Soy paciente"}
      </button>
          </div>
      {/* <button className="btn btn-primary" onClick={() => openModal("clinic")}>
        {activeForm === "clinic"
          ? "Cerrar Registro de Clínica"
          : "Registro de Clínica"}
      </button> */}
      

      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Registro Modal"
      >
        <button className="btn btn-primary" onClick={closeModal}>Cerrar</button>
        {activeForm === "user" && <RegisterUser />}
        {activeForm === "clinic" && <RegisterClinic />}
      </Modal>

      
    </div>
      </nav>
    </header>
  );
};

export default NavBar;

