import React, { useState } from "react";
import axios from "axios";
import FormInput from "./formInput";
import useForm from "./useForm";

export const RegisterUser = () => {
  const { form, errors, handleChange } = useForm();
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const isValidUUID = (uuid: string) => {
    const regex =
      /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;
    return regex.test(uuid);
  };

  const submitForm = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const roleId = "2326ec2c-4f97-4007-b52c-ba5561b434b9";
    if (!isValidUUID(roleId)) {
      setErrorMessage("El UUID del rol no es válido.");
      return;
    }

    const payload = {
      username: form.email,
      password: form.password,
      rolesId: [roleId],
      email: form.email,
    };

    try {
      const response = await axios.post("/api/auth/register", payload, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("User registered successfully:", response.data);

      console.log("Registration successful. Response data:", response.data);

      setErrorMessage(null);
    } catch (error) {
      if (axios.isAxiosError(error)) {
        const errorMessage = error.response?.data?.message || error.message;
        console.error("Error registering user:", errorMessage);

        setErrorMessage(errorMessage);
      } else {
        console.error("Unexpected error:", error);
      }
    }
  };

  return (
    <div className="register-user">
      <h2>Registro de Usuarios</h2>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
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
        <button className="btn btn-secondary" type="submit">
          Regístrate
        </button>
      </form>
    </div>
  );
};
