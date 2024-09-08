import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../context/context.tsx";
import { loginUser } from "../api"; // Puedes mover la lógica de login a un archivo api.ts

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

const useRegisterForm = () => {
  const navigate = useNavigate();
  const { setToken, setRole, setRoleId, setUsername, setPassword } = useAuth();
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
        setRoleId(roleId);
        setUsername(values.username);
        setPassword(values.password);

        Swal.fire({
          icon: "success",
          title: "Registro Exitoso",
          text: "El usuario ha sido registrado y se ha iniciado sesión correctamente.",
        });

        formik.resetForm();
        navigate("/profilesesion");
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "Error Inesperado",
          text: "Hubo un problema al registrar al usuario.",
        });
      }
    },
  });

  return formik;
};

export default useRegisterForm;
