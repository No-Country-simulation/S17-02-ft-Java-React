import React from "react";

interface FormInputProps {
  id: string;
  name: string;
  type: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur?: (e: React.FocusEvent<HTMLInputElement, Element>) => void; // Agregado
  error?: string;
}

const FormInput: React.FC<FormInputProps> = ({
  id,
  name,
  type,
  value,
  onChange,
  onBlur, // Desestructurado
  error,
}) => {
  return (
    <div>
      <label htmlFor={id}>{name}:</label>
      <input
        className="input-register"
        type={type}
        id={id}
        name={id}
        value={value}
        onChange={onChange}
        onBlur={onBlur} // Aplicado
      />
      {error && <p style={{ color: "red" }}>{error}</p>}{" "}
      {/* Opcional: Estilo de error */}
    </div>
  );
};

export default FormInput;
