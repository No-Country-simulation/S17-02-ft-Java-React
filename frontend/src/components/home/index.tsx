import React, { useState } from "react";
import Header from "../header/index.tsx";
import Footer from "../footer/index.tsx";
import NavBar from "../navbar/index.tsx";
import RegisterUser from "../registerUser/index.tsx";
import RegisterClinic from "../registerClinic/index.tsx";
import RegisterEspecialist from "../registerEspecialist/index.tsx";

const Home: React.FC = () => {
  const [showRegisterUser, setShowRegisterUser] = useState<boolean>(false);
  const [showRegisterClinic, setShowRegisterClinic] = useState<boolean>(false);
  const [showRegisterEspecialist, setShowRegisterEspecialist] =
    useState<boolean>(false);

  const toggleRegisterUser = () => {
    setShowRegisterUser((prev) => !prev);
  };

  const toggleRegisterClinic = () => {
    setShowRegisterClinic((prev) => !prev);
  };

  const toggleRegisterEspecialist = () => {
    setShowRegisterEspecialist((prev) => !prev);
  };

  return (
    <div>
      <Header />
      <NavBar />
      <button onClick={toggleRegisterUser}>
        {showRegisterUser
          ? "Cerrar Registro de Usuario"
          : "Registro de Usuario"}
      </button>
      {showRegisterUser && <RegisterUser />}
      <button onClick={toggleRegisterClinic}>
        {showRegisterClinic
          ? "Cerrar Registro de Clínica"
          : "Registro de Clínica"}
      </button>
      {showRegisterClinic && <RegisterClinic />}
      <button onClick={toggleRegisterEspecialist}>
        {showRegisterEspecialist
          ? "Cerrar Registro de Especialista"
          : "Registro de Especialista"}
      </button>
      {showRegisterEspecialist && <RegisterEspecialist />} <Footer />
    </div>
  );
};

export default Home;
