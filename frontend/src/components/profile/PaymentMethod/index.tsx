import React, { useContext, useState } from "react";
import { UserContext } from "../../../context/userContext";

const PaymentMethod: React.FC = () => {
  const context = useContext(UserContext);
  const [isExpanded, setIsExpanded] = useState(false);

  if (!context) {
    throw new Error("PaymentMethod must be used within a UserProvider");
  }

  const { user } = context;

  const toggleExpand = () => {
    if (user.paymentMethod) {
      setIsExpanded(!isExpanded);
    }
  };

  return (
    <div className={`payment-card ${isExpanded ? "expanded" : ""}`}>
      <h3>Método de pago</h3>
      {user.paymentMethod ? (
        <div className={`payment-info ${isExpanded ? "visible" : "hidden"}`}>
          <p>{user.paymentMethod}</p>
        </div>
      ) : (
        <p>Sin método de pago</p>
      )}
      <button
        className={`add-payment`}
        onClick={user.paymentMethod ? toggleExpand : () => {/* lógica para añadir método de pago */}}
      >
        {user.paymentMethod ? "Ver más" : "Añadir método de pago"}
      </button>
    </div>
  );
};

export default React.memo(PaymentMethod);
