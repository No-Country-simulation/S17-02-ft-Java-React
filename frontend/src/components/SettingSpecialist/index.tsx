import React, { useState, useEffect } from "react";
import axios from "axios";
import { useAuth } from "../../context/context.tsx";

interface SpecialistFormData {
  specialistCode: string;
  specialty: {
    specialtyId: number;
  };
  bookingPrice: number;
  reputation: number;
}

interface Specialty {
  specialtyId: number;
  specialtyName: string;
  specialtyDescription: string;
  specialists: any[]; // Adjust if you know the structure of specialists
}

const CreateSpecialist: React.FC = () => {
  const { token, roleId } = useAuth();
  const [formData, setFormData] = useState<SpecialistFormData>({
    specialistCode: "",
    specialty: {
      specialtyId: 0,
    },
    bookingPrice: 0,
    reputation: 0,
  });
  const [specialties, setSpecialties] = useState<Specialty[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  useEffect(() => {
    const fetchSpecialties = async () => {
      try {
        const response = await axios.get("/api/specialty");
        console.log("Response data:", response.data); // Log the full response to debug
        if (Array.isArray(response.data.content)) {
          setSpecialties(response.data.content); // Accessing the 'content' field which holds the array
        } else {
          setError("Unexpected response format for specialties.");
          console.error("Unexpected response format:", response.data);
        }
      } catch (err) {
        setError("Failed to fetch specialties.");
        console.error(err);
      }
    };

    fetchSpecialties();
  }, []);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;

    if (name === "specialtyId") {
      setFormData((prevData) => ({
        ...prevData,
        specialty: {
          ...prevData.specialty,
          specialtyId: Number(value),
        },
      }));
    } else {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!token || !roleId) {
      setError("Authentication details are missing.");
      return;
    }

    try {
      await axios.post("/api/specialist", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Role-Id": roleId,
          "Content-Type": "application/json",
        },
      });
      setSuccess("Especialista Creado Exitosamente.");
    } catch (err) {
      setError("Falla en  crear especialista.");
      console.error(err);
    }
  };

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
            <option value={0}>Seleciona Especialidad</option>
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

export default CreateSpecialist;
