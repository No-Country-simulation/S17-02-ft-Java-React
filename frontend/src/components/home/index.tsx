import React, { useState } from "react";
import Modal from "react-modal";
import Hero from "./Hero/Hero.tsx";
import Brands from "./Brands/Brands.tsx";
import About from "./About/About.tsx";
import Unete from "./Unete/Unete.tsx";
import Services from "./Services/Services.tsx";
import Contact from "./Contact/Contact.tsx";

Modal.setAppElement("#root");

const Home: React.FC = () => {
  const [] = useState(false);
  const [] = useState<string | null>(null);

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
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Registro Modal"
      >
        <button onClick={closeModal}>Cerrar</button>
        {renderForm()}
      </Modal> */}
    </>
  );
};

export default Home;
