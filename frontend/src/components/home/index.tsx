import React, { useState } from "react";
import Modal from "react-modal";
import { Header } from "../header";
import { Footer } from "../footer";
import { Navbar } from "../navbar";
import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
import { Link } from "react-router-dom";

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
    <section className="container-home">

      <div>
        
      <Header />
      <Navbar />
      </div>
      <div>

      <button className="btn btn-secondary" onClick={() => openModal("user")}>
        {activeForm === "user"
          ? REGISTRATION_TEXTS.user.close
          : REGISTRATION_TEXTS.user.open}
      </button>
      <button className="btn btn-secondary" onClick={() => openModal("clinic")}>
        {activeForm === "clinic"
          ? REGISTRATION_TEXTS.clinic.close
          : REGISTRATION_TEXTS.clinic.open}
      </button>
      <Link to="/registerespecialist">
        <button  className="btn btn-secondary">{REGISTRATION_TEXTS.specialist}</button>
      </Link>
      </div>
          <div>

      <Modal
        className="d-flex justify-content-center"
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Registro Modal"
        >
        <button className="close-btn" onClick={closeModal}>X</button>
        {renderForm()}
      </Modal>

      <Footer />
          </div>
    </section>
  );
};

export default Home;
