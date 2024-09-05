import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { Link } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";

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

export const RegisterEspecialist: React.FC = () => {
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
        formik.resetForm();
        Swal.fire({
          icon: "success",
          title: "Registro Exitoso",
          text: "",
        });
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "Error al Registrar",
          text: "",
        });
      }
    },
  });

  return (
    <div>
      <nav>
        <Link to="/">Inicio</Link>
      </nav>

      <form onSubmit={formik.handleSubmit}>
        <h2>Registro de Usuario</h2>

        <label>
          Nombre de usuario:
          <input
            type="text"
            name="username"
            value={formik.values.username}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            aria-required="true"
          />
          {formik.touched.username && formik.errors.username ? (
            <div style={{ color: "red", marginTop: "0.5em" }}>
              {formik.errors.username}
            </div>
          ) : null}
        </label>

        <label>
          Contraseña:
          <input
            type="password"
            name="password"
            value={formik.values.password}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            aria-required="true"
          />
          {formik.touched.password && formik.errors.password ? (
            <div style={{ color: "red", marginTop: "0.5em" }}>
              {formik.errors.password}
            </div>
          ) : null}
        </label>

        <label>
          Confirmar contraseña:
          <input
            type="password"
            name="confirmPassword"
            value={formik.values.confirmPassword}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            aria-required="true"
          />
          {formik.touched.confirmPassword && formik.errors.confirmPassword ? (
            <div style={{ color: "red", marginTop: "0.5em" }}>
              {formik.errors.confirmPassword}
            </div>
          ) : null}
        </label>

        <button type="submit">Registrar Usuario</button>
      </form>
    </div>
  );
};
