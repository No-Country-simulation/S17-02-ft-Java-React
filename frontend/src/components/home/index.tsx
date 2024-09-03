import React, { useState } from "react";
import Modal from "react-modal";


import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
import { Link } from "react-router-dom";
import { useAuth } from "../../context/context.tsx";

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
    </>
  );
};

export default Home;
