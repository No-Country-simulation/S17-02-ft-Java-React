import React from "react";
import { useForm } from "./userForm";
import { Link } from "react-router-dom";
import axios from "axios";

export const RegisterEspecialist: React.FC = () => {
  const { formData, handleChange } = useForm();

  const handleFormSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const requestBody = {
      username: formData.username,
      password: formData.password,
      rolesId: ["9c765b7d-9eec-421b-85c6-6d53bcd002da"],
    };

    try {
      const response = await axios.post("/api/auth/register", requestBody, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("Registro exitoso:", response.data);
    } catch (error) {
      console.error("Error al registrar el usuario:", error);
    }
  };

  return (
    <div>
      <nav>
        <Link to="/">Inicio</Link>
      </nav>

      <form onSubmit={handleFormSubmit}>
        <h2>Registro de Usuario</h2>

        <label>
          Nombre de usuario:
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <label>
          Contraseña:
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <label>
          Confirmar contraseña:
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <button type="submit">Registrar Usuario</button>
      </form>
    </div>
  );
};
