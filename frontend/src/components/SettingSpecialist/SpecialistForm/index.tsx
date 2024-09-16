import React from "react";

interface SpecialistFormProps {
  formData: any;
  specialties: any[];
  error: string | null;
  success: string | null;
  handleChange: (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => void;
  handleSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
}

const SpecialistForm: React.FC<SpecialistFormProps> = ({
  formData,
  specialties,
  error,
  success,
  handleChange,
  handleSubmit,
}) => {
  return (
    <div>
      <h2>Mi Especialidad</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Matricula:
          <input
            type="text"
            name="specialistCode"
            value={formData.specialistCode}
            onChange={handleChange}
          />
        </label>
        <label>
          Especialidad:
          <select
            name="specialtyId"
            value={formData.specialty.specialtyId}
            onChange={handleChange}
          >
            <option value={0}>Selecciona Especialidad</option>
            {specialties.map((specialty) => (
              <option key={specialty.specialtyId} value={specialty.specialtyId}>
                {specialty.specialtyName}
              </option>
            ))}
          </select>
        </label>
        <label>
          Precio:
          <input
            type="number"
            name="bookingPrice"
            value={formData.bookingPrice}
            onChange={handleChange}
          />
        </label>
        <label>
          Reputacion:
          <input
            type="number"
            name="reputation"
            value={formData.reputation}
            onChange={handleChange}
          />
        </label>
        <button type="submit">Crear Especialidad</button>
      </form>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}
    </div>
  );
};

export default SpecialistForm;
