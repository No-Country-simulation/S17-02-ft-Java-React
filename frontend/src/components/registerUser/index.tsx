import React, { useCallback } from "react";
import { useFormik } from "formik";
import { validationSchema } from "./validation";
import { loginUser } from "./loginUser";
import { isValidUUID } from "../registerUser/validation/uuidUtils";
import { handleError } from "./handleError";
import axios from "axios";
import Swal from "sweetalert2";
import { Link, useNavigate } from "react-router-dom";
import FormInput from "./formInput";
import { useAuth } from "../../context/context";

const roleId = "2326ec2c-4f97-4007-b52c-ba5561b434b9";

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
      
        
      
        <Link to="/" className="close-btn">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" className="bi bi-x-lg" viewBox="0 0 16 16">
  <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z"/>
</svg>
        </Link>
      
      <h2>Bienvenido</h2>

   

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
        <div className="button-container">
        <button className="btn-register-user" type="submit">
          Continuar
        </button>
        </div>
      </form>
    </div>
  );
};
