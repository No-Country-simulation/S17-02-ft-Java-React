import { useState } from "react";
import {
  Container,
  Navbar,

  Nav,
} from "react-bootstrap";
import Modal from "react-modal";
import { Link } from "react-router-dom";
import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
const Header = () => {

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
    <header className="header-section p-4">
      <Navbar collapseOnSelect expand="lg" className="bg-body-tertiary">
        <Container className="container d-flex mx-auto w-50">
          <Navbar.Brand href="#home">Hey Doctor!</Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mx-auto">
              <Nav.Link href="#features" className="fw-bolder fs-5">Home</Nav.Link>
              <Nav.Link href="#pricing" className="fw-bolder fs-5">Acerca de</Nav.Link>
              <Nav.Link href="#deets" className="fw-bolder fs-5">Servicios</Nav.Link>
              <Nav.Link eventKey={2} href="#memes" className="fw-bolder fs-5">Video llamadas</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      <div className="d-flex flex-column flex-md-row justify-content-center gap-4">
      <button className="btn btn-primary" onClick={() => openModal("user")}>
        {activeForm === "user"
          ? "Cerrar Registro de Usuario"
          : "Registro de Usuario"}
      </button>
      <button className="btn btn-primary" onClick={() => openModal("clinic")}>
        {activeForm === "clinic"
          ? "Cerrar Registro de Clínica"
          : "Registro de Clínica"}
      </button>
      <Link to="/registerespecialist">
        <button className="btn btn-primary">Registro de Especialista</button>
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

export default Header;

// import { Link } from "react-router-dom";

// export const Navbar = () => {
//   return (
//     <nav>
//       <ul>
//         <li>
//           <Link to="/">Inicio</Link>
//         </li>
//         <li>
//           <Link to="#about">Acerca de</Link>
//         </li>
//         <li>
//           <Link to="#services">Servicios</Link>
//         </li>
//         <li>
//           <Link to="#contact">Contacto</Link>
//         </li>
//         <li>
//           <Link to="/login">Login</Link>
//         </li>
//         <li>
//           <Link to="/video-call">Video Llamada</Link>{" "}
//         </li>
//       </ul>
//     </nav>
//   );
// };
