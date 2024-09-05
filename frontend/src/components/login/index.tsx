import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

const validationSchema = Yup.object({
  username: Yup.string().required("El nombre de usuario es obligatorio"),
  password: Yup.string().required("La contraseña es obligatoria"),
});

export const Login: React.FC = () => {
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      try {
        await axios.post("/api/auth/login", {
          username: values.username,
          password: values.password,
        });

        Swal.fire({
          icon: "success",
          title: "Éxito",
          text: "Inicio de sesión exitoso.",
        });

        navigate("/");
      } catch (err) {
        if (axios.isAxiosError(err)) {
          const errorMessage =
            err.response?.data?.message ||
            "Login fallido. Por favor verifica tus credenciales e intenta de nuevo.";
          Swal.fire({
            icon: "error",
            title: "Error",
            text: errorMessage,
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "Error Inesperado",
            text: "Hubo un error inesperado. Inténtalo de nuevo.",
          });
        }
      }
    },
  });

  return (
    <div style={{ padding: "20px", maxWidth: "400px", margin: "auto" }}>
      <h2>Iniciar sesión</h2>
      <form onSubmit={formik.handleSubmit}>
        <div>
          <label htmlFor="username">Nombre de usuario:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={formik.values.username}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            required
          />
          {formik.touched.username && formik.errors.username ? (
            <p style={{ color: "red" }}>{formik.errors.username}</p>
          ) : null}
        </div>
        <div>
          <label htmlFor="password">Contraseña:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formik.values.password}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            required
          />
          {formik.touched.password && formik.errors.password ? (
            <p style={{ color: "red" }}>{formik.errors.password}</p>
          ) : null}
        </div>
        <div className="d-flex justify-content-end">
          <button className="btn btn-secondary" type="submit">
            Iniciar sesión
          </button>
        </div>
      </form>
    </div>
  );
};
