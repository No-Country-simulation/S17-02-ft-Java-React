import { useState, useCallback } from "react";

interface FormData {
  nombre: string;
  correo: string;
  telefono: string;
  direccion: string;
}

const useForm = () => {
  const [formData, setFormData] = useState<FormData>({
    nombre: "",
    correo: "",
    telefono: "",
    direccion: "",
  });

  const handleChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  }, []);

  const handleSubmit = useCallback(
    (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      console.log("Datos del formulario:", formData);
    },
    [formData]
  );

  return { formData, handleChange, handleSubmit };
};

export default useForm;
