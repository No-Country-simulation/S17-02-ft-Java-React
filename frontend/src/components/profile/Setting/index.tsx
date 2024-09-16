import React from "react";
import { useNavigate } from "react-router-dom";

const Settings: React.FC = () => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/dashboardespecialista/settings");
  };

  return (
    <div className="bar-card config">
      <h3>Configuración</h3>
      <p>Configura la Tu Especialidad</p>
      <button onClick={handleClick}>Ir a Configuraciones</button>
    </div>
  );
};

export default React.memo(Settings);
