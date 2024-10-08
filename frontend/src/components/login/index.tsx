import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import Swal from "sweetalert2";
import {  useNavigate } from "react-router-dom";
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
  <div className="input-container">
    
    <input
      type={type}
      id={id}
      name={name}
      value={value}
      onChange={onChange}
      onBlur={onBlur}
      placeholder={name === "username" ? "Nombre de usuario" : "Contraseña"}
      className={error? "input-register-error-pct":"input-register-pct"}
      required
    />
    {error && <div className="error-message-pct">{error}</div>}{" "}
  </div>
);

const Login = ({ onClose }: { onClose: () => void }) => {
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
          
          const routes: { [key: string]: string } = {
            "9c765b7d-9eec-421b-85c6-6d53bcd002da": "/dashboardEspecialista",
            "2326ec2c-4f97-4007-b52c-ba5561b434b9": "/dashboardCliente",
          };
          
          navigate(routes[roleId] || "/");
        }
        Swal.fire({
          icon: "success",
          title: "Éxito",
          text: "Has iniciado sesión correctamente.",
        });
        if (onClose) onClose();
        
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
    <div className="register-user">
      
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
        <div className="button-container">
        <button className="btn-register-user" type="submit">
          Continuar
        </button>
        </div>
      </form>
    </div>
  );
};

export default Login;