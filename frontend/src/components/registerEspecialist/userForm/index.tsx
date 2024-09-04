import { useState, ChangeEvent, FormEvent, useCallback } from "react";

export type Specialty =
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

export const useForm = (specialties: Specialty[]) => {
  const [formData, setFormData] = useState<FormData>({
    name: "",
    licenseNumber: "",
    email: "",
    phone: "",
    username: "",
    password: "",
    selectedSpecialties: [],
  });
  const [passwordVisible, setPasswordVisible] = useState<boolean>(false);

  const handleChange = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  }, []);

  const handleSpecialtyChange = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => {
      const { value, checked } = e.target;
      if (specialties.includes(value as Specialty)) {
        setFormData((prevData) => {
          const selectedSpecialties = checked
            ? [...prevData.selectedSpecialties, value as Specialty]
            : prevData.selectedSpecialties.filter(
                (specialty) => specialty !== (value as Specialty)
              );

          return { ...prevData, selectedSpecialties };
        });
      }
    },
    [specialties]
  );

  const handleSubmit = useCallback(
    (e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      console.log("Datos del formulario:", formData);
    },
    [formData]
  );

  return {
    formData,
    passwordVisible,
    handleChange,
    handleSpecialtyChange,
    handleSubmit,
    setPasswordVisible,
  };
};
