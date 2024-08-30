import React from "react";

interface FormInputProps {
  id: string;
  label: string;
  type: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  required?: boolean;
}

const FormInput: React.FC<FormInputProps> = ({
  id,
  label,
  type,
  value,
  onChange,
  required,
}) => {
  return (
    <div>
      <label htmlFor={id}>{label}:</label>
      <input
        type={type}
        id={id}
        name={id}
        value={value}
        onChange={onChange}
        required={required}
      />
    </div>
  );
};

export default FormInput;
