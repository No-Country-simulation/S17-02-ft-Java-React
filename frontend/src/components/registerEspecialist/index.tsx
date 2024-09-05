import { useForm } from "./userForm";
import { Link } from "react-router-dom";

type Specialty =
  | "Cardiología"
  | "Neurología"
  | "Pediatría"
  | "Dermatología"
  | "Ginecología"
  | "Oftalmología";

const specialties: Specialty[] = [
  "Cardiología",
  "Neurología",
  "Pediatría",
  "Dermatología",
  "Ginecología",
  "Oftalmología",
];

export const RegisterEspecialist = () => {
  const { formData, handleChange, handleSpecialtyChange, handleSubmit } =
    useForm(specialties);

  return (
    <div>
      <nav>
        <Link to="/">Inicio</Link>
      </nav>

      <form onSubmit={handleSubmit}>
        <h2>Registro de Médico</h2>

        <label>
          Nombre completo:
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <label>
          Número de licencia:
          <input
            type="text"
            name="licenseNumber"
            value={formData.licenseNumber}
            onChange={handleChange}
            required
            aria-required="true"
          />
        </label>

        <label>
          Correo electrónico:
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
            aria-required="true"
            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
            title="Please enter a valid email address"
          />
        </label>

        <label>
          Teléfono:
          <input
            type="tel"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            pattern="\d{10}"
            title="Please enter a 10-digit phone number"
          />
        </label>

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

        <fieldset>
          <legend>Especialidades:</legend>
          {specialties.map((specialty) => (
            <label key={specialty}>
              <input
                type="checkbox"
                value={specialty}
                checked={formData.selectedSpecialties.includes(specialty)}
                onChange={handleSpecialtyChange}
              />
              {specialty}
            </label>
          ))}
        </fieldset>

        <button type="submit">Registrar Médico</button>
      </form>
    </div>
  );
};
