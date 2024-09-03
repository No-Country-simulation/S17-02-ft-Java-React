import { useState } from "react";
import {
  Container,
  Navbar,

  Nav,
} from "react-bootstrap";
import Modal from "react-modal";
import { Link, useNavigate } from "react-router-dom";
import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
import { useAuth } from "../context";
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
      <Navbar collapseOnSelect expand="lg" className="bg-body-tertiar">
        <Container className="container-fluid d-flex w-100">
          <Navbar.Brand className="badge" href="#home">HeyDoc!</Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mx-auto">
              <Nav.Link href="#features" className="fw-bolder fs-3">Home</Nav.Link>
              <Nav.Link href="#pricing" className="fw-bolder fs-3">Acerca de</Nav.Link>
              <Nav.Link href="#deets" className="fw-bolder fs-3">Servicios</Nav.Link>
          {token?   (<>
           <Nav.Link eventKey={2} href="#memes" className="fw-bolder fs-3">Video llamadas</Nav.Link>
            <button onClick={handleLogout} className="btn btn-secondary" >Logout</button>
            </>):
            <Nav.Link eventKey={2} href="/login" className="fw-bolder fs-3">Login</Nav.Link>}
          </Nav>
          </Navbar.Collapse>
        </Container>
      <div className="d-flex flex-column flex-md-row justify-content-center gap-4">
      <button className="btn-navbar-w" onClick={() => openModal("user")}>
        {activeForm === "user"
          ? "Cerrar Registro de Usuario"
          : "Registro de Usuario"}
      </button>
      {/* <button className="btn btn-primary" onClick={() => openModal("clinic")}>
        {activeForm === "clinic"
          ? "Cerrar Registro de Clínica"
          : "Registro de Clínica"}
      </button> */}
      <Link to="/registerespecialist">
        <button className="btn-navbar-g">Registro de Especialista</button>
      </Link>

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
      </Navbar>
    </header>
  );
};

export default NavBar;

