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
    <div className="input-container">
      <input
        className={error? "input-register-error":"input-register-pct"}
        type={type}
        id={id}
        name={id}
        value={value}
        onChange={onChange}
        onBlur={onBlur} // Aplicado
        placeholder={name}
      />
      {error && <div className="error-message-pct">{error}</div>}{" "}
      {/* Opcional: Estilo de error */}
    </div>
  );
};

export default FormInput;
