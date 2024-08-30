import React, { useState } from "react";
import Modal from "react-modal";
import { Header } from "../header";
import { Footer } from "../footer";
import { Navbar } from "../navbar";
import { NavbarAdmin } from "../../componentsAdmin/navbarAdmin/index.tsx";
import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
import { Link } from "react-router-dom";
import { useAuth } from "../context/index.tsx";

Modal.setAppElement("#root");

const REGISTRATION_TEXTS = {
  user: {
    open: "Registro de Usuario",
    close: "Cerrar Registro de Usuario",
  },
  clinic: {
    open: "Registro de Clínica",
    close: "Cerrar Registro de Clínica",
  },
  specialist: "Registro de Especialista",
};

const Home: React.FC = () => {
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [activeForm, setActiveForm] = useState<string | null>(null);
  const { token, role } = useAuth();

  const openModal = (form: string) => {
    setActiveForm(form);
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
    setActiveForm(null);
  };

  const renderForm = () => {
    switch (activeForm) {
      case "user":
        return <RegisterUser />;
      case "clinic":
        return <RegisterClinic />;
      default:
        return null;
    }
  };

  return (
    <>
      <Header />
      {role === "admin" ? <NavbarAdmin /> : <Navbar />}
      {!token ? (
        <>
          <button onClick={() => openModal("user")}>
            {activeForm === "user"
              ? REGISTRATION_TEXTS.user.close
              : REGISTRATION_TEXTS.user.open}
          </button>
          <button onClick={() => openModal("clinic")}>
            {activeForm === "clinic"
              ? REGISTRATION_TEXTS.clinic.close
              : REGISTRATION_TEXTS.clinic.open}
          </button>
        </>
      ) : (
        <>
          {role === "admin" && (
            <Link to="/registerespecialist">
              <button>{REGISTRATION_TEXTS.specialist}</button>
            </Link>
          )}
        </>
      )}
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Registro Modal"
      >
        <button onClick={closeModal}>Cerrar</button>
        {renderForm()}
      </Modal>
      <Footer />
    </>
  );
};

export default Home;
