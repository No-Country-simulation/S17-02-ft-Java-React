/* eslint-disable @typescript-eslint/no-explicit-any */
import { FormikErrors } from 'formik';
import { Button } from 'react-bootstrap'; // Usaremos Bootstrap para el botón

interface CustomChipProps {
  selectedDays: string[],
  value: { name: string, value: string },
  setFieldValue: (field: string, value: string[], shouldValidate?: boolean) => 
    Promise<void | FormikErrors<any>>,
}

export const CustomChip = ({ setFieldValue, selectedDays, value }: CustomChipProps) => {
  const isSelected = selectedDays.includes(value.value);

  const handleClick = () => {
    const newSelectedDays = isSelected
      ? selectedDays.filter(d => d !== value.value)
      : [...selectedDays, value.value];
    setFieldValue('selectedDays', newSelectedDays);
  };

  return (
    <Button
      className={`custom-chip ${isSelected ? 'selected' : 'unselected'}`}
      onClick={handleClick}
      variant={isSelected ? 'success' : 'light'}
    >
      {value.name}
    </Button>
  );
};
