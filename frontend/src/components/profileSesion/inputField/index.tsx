import React from "react";

interface InputFieldProps {
  label: string;
  type: string;
  name: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const InputField: React.FC<InputFieldProps> = ({ label, type, name, value, onChange }) => {
  return (
    <div className="input-container">
      <input className=" input-profile" type={type} name={name} placeholder={label} value={value} onChange={onChange} />
    </div>
  );
};

export default InputField;
