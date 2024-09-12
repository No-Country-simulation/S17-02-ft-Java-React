import React, { useContext } from "react";
import { UserContext } from "../../../context/userContext";

const PaymentMethod: React.FC = () => {
  const context = useContext(UserContext);

  if (!context) {
    throw new Error("PaymentMethod must be used within a UserProvider");
  }

  const { user } = context;

  return (
    <div>
      <h3>Método de pago</h3>
      {user.paymentMethod ? (
        <p>{user.paymentMethod}</p>
      ) : (
        <button>Añadir método de pago</button>
      )}
    </div>
  );
};

export default React.memo(PaymentMethod);
