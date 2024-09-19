import React from "react";
import { useAuth } from "../../context/context.tsx";
import useSpecialistForm from "./UseSpecialistForm/index.ts";
import SpecialistForm from "./SpecialistForm";

const CreateSpecialist: React.FC = () => {
  const { token, roleId } = useAuth();
  const { formData, specialties, error, success, handleChange, handleSubmit } =
    useSpecialistForm(token, roleId);

  return (
    <SpecialistForm
      formData={formData}
      specialties={specialties}
      error={error}
      success={success}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
    />
  );
};

export default CreateSpecialist;
