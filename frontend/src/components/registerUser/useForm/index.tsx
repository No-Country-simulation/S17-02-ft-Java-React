import { useState } from "react";

const useForm = () => {
  const [form, setForm] = useState({
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.id]: e.target.value });
  };

  const validate = () => {
    const newErrors = { email: "", password: "" };
    if (!form.email) newErrors.email = "Email is required";
    if (!form.password) newErrors.password = "Password is required";
    setErrors(newErrors);
    return Object.values(newErrors).every((error) => error === "");
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    if (validate()) {
      e.preventDefault();
    }
  };

  return {
    form,
    errors,
    handleChange,
    handleSubmit,
  };
};

export default useForm;
