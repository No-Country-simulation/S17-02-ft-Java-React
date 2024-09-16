import React from "react";

interface TextFieldProps {
  id: string;
  name: string;
  type: string;
  value: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
  onBlur: React.FocusEventHandler<HTMLInputElement>;
  error?: string;
}

const TextField: React.FC<TextFieldProps> = ({
  id,
  name,
  type,
  value,
  onChange,
  onBlur,
  error,
}) => (
  <div className="input-container">
    <input
      type={type}
      id={id}
      name={name}
      value={value}
      onChange={onChange}
      onBlur={onBlur}
      placeholder={name === "username" ? "Nombre de usuario" : "Contraseña"}
      className={error ? "input-register-error-pct" : "input-register-pct"}
      required
    />
    {error && <div className="error-message-pct">{error}</div>}
  </div>
);

export default TextField;
