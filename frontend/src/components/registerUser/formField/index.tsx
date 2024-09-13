import React from "react";

interface FormFieldProps {
  name: string;
  type: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur: (e: React.FocusEvent<HTMLInputElement, Element>) => void;
  error?: string;
}

const FormField: React.FC<FormFieldProps> = ({
  name,
  type,
  value,
  onChange,
  onBlur,
  error,
}) => (
  <div className="register-specialist">
    
        <input
        placeholder={name}
        type={type}
        id={name}
        name={name}
        value={value}
        onChange={onChange}
        onBlur={onBlur}
        aria-required="true"
        className={error? "input-register-error": "input-register"}
      />
    
    {error && <div className="error-message">{error}</div>}
  </div>
);

export default FormField;
