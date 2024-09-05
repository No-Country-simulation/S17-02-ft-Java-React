import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import FormInput from "./formInput";

const validationSchema = Yup.object({
  email: Yup.string()
    .email("Debe ser un correo electrónico válido")
    .required("El correo electrónico es obligatorio"),
  password: Yup.string()
    .min(8, "La contraseña debe tener al menos 8 caracteres")
    .matches(/[a-zA-Z]/, "La contraseña debe contener letras")
    .matches(/[0-9]/, "La contraseña debe contener números")
    .required("La contraseña es obligatoria"),
});

export const RegisterUser: React.FC = () => {
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      const roleId = "2326ec2c-4f97-4007-b52c-ba5561b434b9";

      // Validación del UUID
      const isValidUUID = (uuid: string) => {
        const regex =
          /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;
        return regex.test(uuid);
      };

      if (!isValidUUID(roleId)) {
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "El UUID del rol no es válido.",
        });
        return;
      }

      const payload = {
        username: values.email,
        password: values.password,
        rolesId: [roleId],
        email: values.email,
      };

      try {
        await axios.post("/api/auth/register", payload, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        Swal.fire({
          icon: "success",
          title: "Registro Exitoso",
          text: "El usuario ha sido registrado correctamente.",
        });
        formik.resetForm();
      } catch (error) {
        if (axios.isAxiosError(error)) {
          const errorMessage = error.response?.data?.message || error.message;
          Swal.fire({
            icon: "error",
            title: "Error al Registrar",
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
    <div className="register-user">
      <h2>Registro de Usuarios</h2>

      {formik.errors.email || formik.errors.password ? (
        <ul>
          {formik.errors.email && <li>{formik.errors.email}</li>}
          {formik.errors.password && <li>{formik.errors.password}</li>}
        </ul>
      ) : null}

      <form onSubmit={formik.handleSubmit}>
        <FormInput
          id="email"
          name="Correo Electrónico"
          type="email"
          value={formik.values.email}
          onChange={formik.handleChange}
          error={formik.touched.email ? formik.errors.email : undefined}
        />
        <FormInput
          id="password"
          name="Contraseña"
          type="password"
          value={formik.values.password}
          onChange={formik.handleChange}
          error={formik.touched.password ? formik.errors.password : undefined}
        />
        <button className="btn btn-secondary" type="submit">
          Regístrate
        </button>
      </form>
    </div>
  );
};
