import React from "react";
import axios from "axios";
import FormInput from "./formInput"; // Asegúrate de que esta ruta sea correcta
import useForm from "./useForm"; // Asegúrate de que esta ruta sea correcta

export const RegisterUser = () => {
  const { form, errors, handleChange } = useForm();

  const submitForm = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const payload = {
      username: form.email,
      password: form.password,
      rolesId: [0], // Asegúrate de que este valor es correcto
    };

    try {
      const response = await axios.post("/api/auth/register", payload, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("User registered successfully:", response.data);
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.error(
          "Error registering user:",
          error.response?.data || error.message
        );
      } else {
        console.error("Unexpected error:", error);
      }
    }
  };

  return (
    <div>
      <h2>Registro de Usuarios</h2>
      {Object.keys(errors).length > 0 && (
        <ul>
          {Object.entries(errors).map(([key, error]) => (
            <li key={key}>{error}</li>
          ))}
        </ul>
      )}
      <form onSubmit={submitForm}>
        <FormInput
          id="email"
          name="Email"
          type="email"
          value={form.email}
          onChange={handleChange}
          error={errors.email}
        />
        <FormInput
          id="password"
          name="Contraseña"
          type="password"
          value={form.password}
          onChange={handleChange}
          error={errors.password}
        />
        <button type="submit">Regístrate</button>
      </form>
    </div>
  );
};
