import { useState, ChangeEvent, FormEvent } from "react";

type Specialty =
  | "Cardiología"
  | "Neurología"
  | "Pediatría"
  | "Dermatología"
  | "Ginecología"
  | "Oftalmología";

interface FormData {
  name: string;
  licenseNumber: string;
  email: string;
  phone: string;
  username: string;
  password: string;
  selectedSpecialties: Specialty[];
}

const specialties: Specialty[] = [
  "Cardiología",
  "Neurología",
  "Pediatría",
  "Dermatología",
  "Ginecología",
  "Oftalmología",
];

export const RegisterEspecialist = () => {
  const [formData, setFormData] = useState<FormData>({
    name: "",
    licenseNumber: "",
    email: "",
    phone: "",
    username: "",
    password: "",
    selectedSpecialties: [],
  });

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSpecialtyChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { value, checked } = e.target;
    if (value in specialties) {
      setFormData((prevData) => {
        const selectedSpecialties = checked
          ? [...prevData.selectedSpecialties, value as Specialty]
          : prevData.selectedSpecialties.filter(
              (specialty) => specialty !== (value as Specialty)
            );

        return { ...prevData, selectedSpecialties };
      });
    }
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Datos del formulario:", formData);
  };

  return (
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
        />
      </label>

      <label>
        Teléfono:
        <input
          type="tel"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
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
  );
};
