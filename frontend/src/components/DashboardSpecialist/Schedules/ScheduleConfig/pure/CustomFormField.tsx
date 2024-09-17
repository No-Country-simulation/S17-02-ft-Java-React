
import { format } from 'date-fns';
import { ErrorMessage, FieldProps } from 'formik';
import Form from 'react-bootstrap/Form'; // Usaremos el componente Form de Bootstrap

interface CustomFormField extends FieldProps {
  className?: string;
}

const CustomFormField = ({ className, field, form: { errors, touched }, ...props }: CustomFormField) => {
  const formatTimeValue = (value: string | Date) => {
    if (value instanceof Date) {
      return format(value, 'HH:mm:ss');
    }
    return value;
  };

  return (
    <div className="custom-form-field">
      <Form.Control
        {...field}
        {...props}
        size="lg"
        value={formatTimeValue(field.value)}
        className={className}
      />
      {touched[field.name] && errors[field.name] ? (
        <div className="error-message">
          <ErrorMessage name={field.name} component="div" />
        </div>
      ) : (
        <div className="h-6" />
      )}
    </div>
  );
};

export default CustomFormField;
