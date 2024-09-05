import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";

const FormField: React.FC<{
  id: string;
  type: string;
  name: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur: (e: React.FocusEvent<HTMLInputElement, Element>) => void;
  error?: string;
}> = ({ id, type, name, value, onChange, onBlur, error }) => (
  <label>
    {name}:
    <input
      type={type}
      id={id}
      name={id}
      value={value}
      onChange={onChange}
      onBlur={onBlur}
      aria-required="true"
    />
    {error && <div style={{ color: "red", marginTop: "0.5em" }}>{error}</div>}
  </label>
);

const validationSchema = Yup.object({
  username: Yup.string()
    .min(3, "El nombre de usuario debe tener al menos 3 caracteres")
    .max(20, "El nombre de usuario no puede tener más de 20 caracteres")
    .required("El nombre de usuario es obligatorio"),
  password: Yup.string()
    .min(8, "La contraseña debe tener al menos 8 caracteres")
    .matches(/[a-zA-Z]/, "La contraseña debe contener letras")
    .matches(/[0-9]/, "La contraseña debe contener números")
    .required("La contraseña es obligatoria"),
  confirmPassword: Yup.string()
    .oneOf([Yup.ref("password")], "Las contraseñas deben coincidir")
    .required("La confirmación de la contraseña es obligatoria"),
});

const handleError = (error: any, defaultMessage: string) => {
  let errorTitle = defaultMessage;
  if (axios.isAxiosError(error)) {
    errorTitle = "Error al Registrar";
  }
  Swal.fire({
    icon: "error",
    title: errorTitle,
    text: "",
  });
};

export const RegisterEspecialist: React.FC = () => {
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      const requestBody = {
        username: values.username,
        password: values.password,
        rolesId: ["9c765b7d-9eec-421b-85c6-6d53bcd002da"],
      };

      try {
        await axios.post("/api/auth/register", requestBody, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        await loginUser(values.username, values.password);
        Swal.fire({
          icon: "success",
          title: "Registro Exitoso",
          text: "El usuario ha sido registrado y se ha iniciado sesión correctamente.",
        });
        formik.resetForm();
        navigate("/");
      } catch (error) {
        handleError(error, "Error Inesperado");
      }
    },
  });

  const loginUser = async (username: string, password: string) => {
    try {
      await axios.post("/api/auth/login", { username, password });
      console.log("Inicio de sesión exitoso"); // Agregado para mostrar mensaje en consola
      navigate("/");
    } catch (err) {
      handleError(err, "Error al Iniciar Sesión");
      throw err;
    }
  };

  return (
    <div>
      <nav>
        <Link to="/">Inicio</Link>
      </nav>

      <form onSubmit={formik.handleSubmit}>
        <h2>Registro de Usuario</h2>

        <FormField
          id="username"
          type="text"
          name="Nombre de usuario"
          value={formik.values.username}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.username ? formik.errors.username : undefined}
        />

        <FormField
          id="password"
          type="password"
          name="Contraseña"
          value={formik.values.password}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.password ? formik.errors.password : undefined}
        />

        <FormField
          id="confirmPassword"
          type="password"
          name="Confirmar contraseña"
          value={formik.values.confirmPassword}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={
            formik.touched.confirmPassword
              ? formik.errors.confirmPassword
              : undefined
          }
        />

        <button type="submit">Registrar Usuario</button>
      </form>
    </div>
  );
};
