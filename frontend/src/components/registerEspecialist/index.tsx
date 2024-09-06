import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
import { useAuth } from "../../context/context.tsx";

const FormField: React.FC<{
  name: string;
  type: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur: (e: React.FocusEvent<HTMLInputElement, Element>) => void;
  error?: string;
}> = ({ name, type, value, onChange, onBlur, error }) => (
  <label>
    {name}:
    <input
      type={type}
      id={name}
      name={name}
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
  const errorMessage = axios.isAxiosError(error)
    ? "Error al Registrar"
    : defaultMessage;
  Swal.fire({
    icon: "error",
    title: errorMessage,
  });
};

const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post("/api/auth/login", {
      username,
      password,
    });
    return {
      token: response.data.token,
      role: response.data.roleId,
    };
  } catch (err) {
    handleError(err, "Error al Iniciar Sesión");
    throw err;
  }
};

export const RegisterEspecialist: React.FC = () => {
  const navigate = useNavigate();
  const { setToken, setRole } = useAuth();
  const roleId = "9c765b7d-9eec-421b-85c6-6d53bcd002da";

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      const payload = {
        username: values.username,
        password: values.password,
        rolesId: [roleId],
      };

      try {
        await axios.post("/api/auth/register", payload, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        const loginResponse = await loginUser(values.username, values.password);
        const { token, role } = loginResponse;

        setToken(token);
        setRole(role);

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

  const { values, handleChange, handleBlur, handleSubmit, errors, touched } =
    formik;

  return (
    <div>
      <nav>
        <Link to="/">Cerrar</Link>
      </nav>

      <form onSubmit={handleSubmit}>
        <h2>Registro de Profesionales</h2>

        <FormField
          name="username"
          type="text"
          value={values.username}
          onChange={handleChange}
          onBlur={handleBlur}
          error={touched.username ? errors.username : undefined}
        />

        <FormField
          name="password"
          type="password"
          value={values.password}
          onChange={handleChange}
          onBlur={handleBlur}
          error={touched.password ? errors.password : undefined}
        />

        <FormField
          name="confirmPassword"
          type="password"
          value={values.confirmPassword}
          onChange={handleChange}
          onBlur={handleBlur}
          error={touched.confirmPassword ? errors.confirmPassword : undefined}
        />

        <button type="submit">Registrar Usuario</button>
      </form>
    </div>
  );
};
