import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context.tsx";

const validationSchema = Yup.object({
  username: Yup.string().required("El nombre de usuario es obligatorio"),
  password: Yup.string().required("La contraseña es obligatoria"),
});

const TextField: React.FC<{
  id: string;
  name: string;
  type: string;
  value: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
  onBlur: React.FocusEventHandler<HTMLInputElement>;
  error?: string;
}> = ({ id, name, type, value, onChange, onBlur, error }) => (
  <div className="form-group">
    <label htmlFor={id}>
      {name === "username" ? "Nombre de usuario" : "Contraseña"}:
    </label>
    <input
      type={type}
      id={id}
      name={name}
      value={value}
      onChange={onChange}
      onBlur={onBlur}
      className={`form-control ${error ? "is-invalid" : ""}`}
      required
    />
    {error && <p className="error-text">{error}</p>}
  </div>
);

export const Login: React.FC = () => {
  const navigate = useNavigate();
  const { setToken, setRole, setRoleId } = useAuth();

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      try {
        const response = await axios.post("/api/auth/login", {
          username: values.username,
          password: values.password,
        });

        // Desestructurar la respuesta para obtener userResponseDTO y token
        const { userResponseDTO, token } = response.data;
        const { roles } = userResponseDTO;

        // Mostrar en consola la información deseada
        console.log("User Response DTO:", userResponseDTO);
        console.log("Token:", token);

        // Suponiendo que deseas guardar el role y roleId en el contexto
        if (roles && roles.length > 0) {
          const { roleId, roleName } = roles[0]; // Usar el primer rol como ejemplo
          setToken(token);
          setRole(roleName);
          setRoleId(roleId);
          console.log("Role ID:", roleId);
        }

        Swal.fire({
          icon: "success",
          title: "Éxito",
          text: "Has iniciado sesión correctamente.",
        });

        navigate("/");
      } catch (err) {
        const errorMessage = axios.isAxiosError(err)
          ? "Error al iniciar sesión. Por favor, intenta nuevamente."
          : "Error inesperado. Por favor, intenta nuevamente.";

        Swal.fire({
          icon: "error",
          title: "Error",
          text: errorMessage,
        });
      }
    },
  });

  return (
    <div className="login-container">
      <nav>
        <Link to="/">Cerrar</Link>
      </nav>
      <h2>Iniciar sesión</h2>
      <form onSubmit={formik.handleSubmit}>
        <TextField
          id="username"
          name="username"
          type="text"
          value={formik.values.username}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.username ? formik.errors.username : undefined}
        />
        <TextField
          id="password"
          name="password"
          type="password"
          value={formik.values.password}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.password ? formik.errors.password : undefined}
        />
        <div className="d-flex justify-content-end">
          <button className="btn btn-secondary" type="submit">
            Iniciar sesión
          </button>
        </div>
      </form>
    </div>
  );
};
