import { useState, useEffect } from "react";
import axios from "axios";

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
  specialists: any[];
}

const useSpecialistForm = (token: string | null, roleId: string | null) => {
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
        if (Array.isArray(response.data.content)) {
          setSpecialties(response.data.content);
        } else {
          setError("Unexpected response format for specialties.");
        }
      } catch (err) {
        setError("Failed to fetch specialties.");
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
    }
  };

  return { formData, specialties, error, success, handleChange, handleSubmit };
};

export default useSpecialistForm;
