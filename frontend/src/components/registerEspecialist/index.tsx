import { useForm } from "./userForm";
import { Link } from "react-router-dom";

export const RegisterEspecialist = () => {
  const { formData, handleChange, handleSubmit } = useForm();

  return (
    <div>
      <nav>
        <Link to="/">Inicio</Link>
      </nav>

      <form onSubmit={handleSubmit}>
        <h2>Registro de Usuario</h2>

        <label>
          Nombre de usuario:
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <label>
          Contraseña:
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <label>
          Confirmar contraseña:
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <button type="submit">Registrar Usuario</button>
      </form>
    </div>
  );
};
