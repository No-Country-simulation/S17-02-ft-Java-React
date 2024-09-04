import React, { useState } from "react";
import Modal from "react-modal";
import { RegisterUser } from "../registerUser";
import { RegisterClinic } from "../registerClinic";
import { Link } from "react-router-dom";
import { useAuth } from "../../context/context.tsx"
import Hero from "./Hero/Hero.tsx";
import Brands from "./Brands/Brands.tsx";
import About from "./About/About.tsx";
import Unete from "./Unete/Unete.tsx";
import Services from "./Services/Services.tsx";
import Contact from "./Contact/Contact.tsx";

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
  // const { token, role } = useAuth();

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
       <div>
        <Hero />
        <Brands />
        <About />
        <Unete />
        <Services />
        <Contact />
      </div>
{/*       

//! momentaneamente deshabilitado, para renderizar los componentes que creó Pablo Guerreño
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
        className="register-modal"
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Registro Modal"
        >
          <div>
        <button className="close-btn" onClick={closeModal}>X</button>
          </div>
        {renderForm()}
      </Modal> */}
    </>
  );
};

export default Home;
