import { useState, ChangeEvent, FormEvent, useCallback } from "react";

interface FormData {
  username: string;
  password: string;
  confirmPassword: string;
}

export const useForm = () => {
  const [formData, setFormData] = useState<FormData>({
    username: "",
    password: "",
    confirmPassword: "",
  });
  const [passwordVisible, setPasswordVisible] = useState<boolean>(false);

  const handleChange = useCallback((e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  }, []);

  const handleSubmit = useCallback(
    (e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      if (formData.password !== formData.confirmPassword) {
        alert("Las contraseñas no coinciden");
        return;
      }
      console.log("Datos del formulario:", formData);
    },
    [formData]
  );

  return {
    formData,
    passwordVisible,
    handleChange,
    handleSubmit,
    setPasswordVisible,
  };
};
