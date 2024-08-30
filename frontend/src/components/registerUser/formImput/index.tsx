import React from "react";

interface FormInputProps {
  id: string;
  name: string;
  type: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  error?: string;
}

const FormInput: React.FC<FormInputProps> = ({
  id,
  name,
  type,
  value,
  onChange,
  error,
}) => {
  return (
    <div>
      <label htmlFor={id}>{name}:</label>
      <input className="input-register" type={type} id={id} name={id} value={value} onChange={onChange} />
      {error && <p>{error}</p>}
    </div>
  );
};

export default FormInput;
