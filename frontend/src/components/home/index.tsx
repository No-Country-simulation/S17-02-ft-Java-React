import React, { useState } from "react";
import Modal from "react-modal";
import { Header } from "../header/index.tsx";
import { Footer } from "../footer/index.tsx";
import { Navbar } from "../navbar/index.tsx";
import { RegisterUser } from "../registerUser/index.tsx";
import { RegisterClinic } from "../registerClinic/index.tsx";
import { RegisterEspecialist } from "../registerEspecialist/index.tsx";

Modal.setAppElement("#root");

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

  return (
    <div>
      <Header />
      <Navbar />
      <button onClick={() => openModal("user")}>
        {activeForm === "user"
          ? "Cerrar Registro de Usuario"
          : "Registro de Usuario"}
      </button>
      <button onClick={() => openModal("clinic")}>
        {activeForm === "clinic"
          ? "Cerrar Registro de Clínica"
          : "Registro de Clínica"}
      </button>
      <button onClick={() => openModal("especialist")}>
        {activeForm === "especialist"
          ? "Cerrar Registro de Especialista"
          : "Registro de Especialista"}
      </button>

      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Registro Modal"
      >
        <button onClick={closeModal}>Cerrar</button>
        {activeForm === "user" && <RegisterUser />}
        {activeForm === "clinic" && <RegisterClinic />}
        {activeForm === "especialist" && <RegisterEspecialist />}
      </Modal>

      <Footer />
    </div>
  );
};

export default Home;
