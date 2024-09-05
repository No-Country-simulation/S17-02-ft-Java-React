import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import FormInput from "./formInput";
import { useAuth } from "../../context/context.tsx";

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

const isValidUUID = (uuid: string): boolean => {
  const regex =
    /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;
  return regex.test(uuid);
};

export const RegisterUser: React.FC = () => {
  const navigate = useNavigate();
  const { setToken, setRole } = useAuth();
  const roleId = "2326ec2c-4f97-4007-b52c-ba5561b434b9";

  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      if (!isValidUUID(roleId)) {
        await Swal.fire({
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
        console.log("Enviando datos de registro:", payload);
        await axios.post("/api/auth/register", payload, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        console.log("Registro exitoso, procediendo a login");
        const loginResponse = await loginUser(values.email, values.password);
        if (loginResponse) {
          const { token, role } = loginResponse;
          console.log("Token recibido:", token);
          console.log("Rol recibido:", roleId);
          setToken(token);
          setRole(role);
        }
        await Swal.fire({
          icon: "success",
          title: "Registro Exitoso",
          text: "El usuario ha sido registrado correctamente y está ahora logueado.",
        });
        formik.resetForm();
        console.log("Redirigiendo a /");
        navigate("/");
      } catch (error) {
        handleError(error, "Error Inesperado");
      }
    },
  });

  const loginUser = async (username: string, password: string) => {
    try {
      const response = await axios.post("/api/auth/login", {
        username,
        password,
      });
      console.log("Inicio de sesión exitoso");
      console.log("Respuesta del login:", response.data);
      return {
        token: response.data.token,
        role: response.data.roleId,
      };
    } catch (err) {
      handleError(err, "Error al Iniciar Sesión");
      throw err;
    }
  };

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
          onBlur={formik.handleBlur}
          error={formik.touched.email ? formik.errors.email : undefined}
        />
        <FormInput
          id="password"
          name="Contraseña"
          type="password"
          value={formik.values.password}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.password ? formik.errors.password : undefined}
        />
        <button className="btn btn-secondary" type="submit">
          Regístrate
        </button>
      </form>
    </div>
  );
};
