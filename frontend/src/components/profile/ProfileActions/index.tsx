import React, { useContext, useState } from "react";
import { UserContext } from "../../../context/userContext";

const ProfileActions: React.FC = () => {
  const context = useContext(UserContext);
  const [isExpanded, setIsExpanded] = useState(false);

  if (!context) {
    throw new Error("ProfileActions must be used within a UserProvider");
  }

  const { user } = context;

  const toggleExpand = () => {
    if (user.appointments.length > 0) {
      setIsExpanded(!isExpanded);
    }
  };

  return (
    <div className={`bar-card-shift ${isExpanded ? "expanded" : ""}`}>
      <h3>Mis turnos</h3>
      {user.appointments.length > 0 ? (
        <ul className={isExpanded ? "visible" : "hidden"}>
          {user.appointments.map((appointment, index) => (
            <li key={index}>{appointment}</li>
          ))}
        </ul>
      ) : (
        <p>Sin reservas</p>
      )}
      <button
        className={`ver-mas ${user.appointments.length === 0 ? "disabled" : ""}`}
        onClick={toggleExpand}
        disabled={user.appointments.length === 0}
      >
        Ver más
      </button>
    </div>
  );
};

export default React.memo(ProfileActions);

