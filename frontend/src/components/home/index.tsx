import React, { useState } from "react";
import Header from "../header/index.tsx";
import Footer from "../footer/index.tsx";
import NavBar from "../navbar/index.tsx";
import RegisterUser from "../registerUser/index.tsx";
import RegisterClinic from "../registerClinic/index.tsx";
import RegisterEspecialist from "../registerEspecialist/index.tsx";

const Home: React.FC = () => {
  const [activeForm, setActiveForm] = useState<string | null>(null);

  const toggleForm = (form: string) => {
    setActiveForm((prevForm) => (prevForm === form ? null : form));
  };

  return (
    <div>
      <Header />
      <NavBar />
      <button onClick={() => toggleForm("user")}>
        {activeForm === "user"
          ? "Cerrar Registro de Usuario"
          : "Registro de Usuario"}
      </button>
      {activeForm === "user" && <RegisterUser />}

      <button onClick={() => toggleForm("clinic")}>
        {activeForm === "clinic"
          ? "Cerrar Registro de Clínica"
          : "Registro de Clínica"}
      </button>
      {activeForm === "clinic" && <RegisterClinic />}

      <button onClick={() => toggleForm("especialist")}>
        {activeForm === "especialist"
          ? "Cerrar Registro de Especialista"
          : "Registro de Especialista"}
      </button>
      {activeForm === "especialist" && <RegisterEspecialist />}

      <Footer />
    </div>
  );
};

export default Home;
