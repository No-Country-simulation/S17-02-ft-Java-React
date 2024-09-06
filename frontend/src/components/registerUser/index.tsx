import React, { useCallback } from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import { Link, useNavigate } from "react-router-dom";
import FormInput from "./formInput";
import { useAuth } from "../../context/context";

const validationSchema = Yup.object({
  email: Yup.string()
    .email("Debe ser un correo electrónico válido")
    .required("El correo electrónico es obligatorio"),
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

const isValidUUID = (uuid: string): boolean => {
  const regex =
    /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;
  return regex.test(uuid);
};

const roleId = "2326ec2c-4f97-4007-b52c-ba5561b434b9";

const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post("/api/auth/login", {
      username,
      password,
    });
    return { token: response.data.token, role: response.data.roleId };
  } catch (err) {
    handleError(err, "Error al Iniciar Sesión");
    throw err;
  }
};

export const RegisterUser: React.FC = () => {
  const navigate = useNavigate();
  const { setToken, setRole, setRoleId } = useAuth();

  const onSubmit = useCallback(
    async (values: { email: string; password: string }) => {
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
        await axios.post("/api/auth/register", payload, {
          headers: { "Content-Type": "application/json" },
        });

        const loginResponse = await loginUser(values.email, values.password);
        if (loginResponse) {
          const { token, role } = loginResponse;
          setToken(token);
          setRole(role);
          setRoleId(roleId);
          console.log("Role ID guardado en el contexto:", roleId);
        }

        await Swal.fire({
          icon: "success",
          title: "Registro Exitoso",
          text: "El usuario ha sido registrado correctamente y está ahora logueado.",
        });
        formik.resetForm();
        navigate("/");
      } catch (error) {
        handleError(error, "Error Inesperado");
      }
    },
    [navigate, setToken, setRole, setRoleId]
  );

  const formik = useFormik({
    initialValues: { email: "", password: "", confirmPassword: "" },
    validationSchema,
    onSubmit,
  });

  return (
    <div className="register-user">
      <nav>
        <Link to="/">Cerrar</Link>
      </nav>
      <h2>Registro de Pacientes</h2>

      {formik.errors.email ||
      formik.errors.password ||
      formik.errors.confirmPassword ? (
        <ul>
          {formik.errors.email && <li>{formik.errors.email}</li>}
          {formik.errors.password && <li>{formik.errors.password}</li>}
          {formik.errors.confirmPassword && (
            <li>{formik.errors.confirmPassword}</li>
          )}
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
        <FormInput
          id="confirmPassword"
          name="Confirmar Contraseña"
          type="password"
          value={formik.values.confirmPassword}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={
            formik.touched.confirmPassword
              ? formik.errors.confirmPassword
              : undefined
          }
        />
        <button className="btn btn-secondary" type="submit">
          Regístrar Paciente
        </button>
      </form>
    </div>
  );
};
