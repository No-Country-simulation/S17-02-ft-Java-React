
interface FormButtonsProps {
    onSkip: () => void;
  }

const FormButtons: React.FC<FormButtonsProps> = ({  onSkip }) => {
  return (
    <div className="container form-buttons">
      <button type="button" className=" btn-special" onClick={onSkip}>Omitir</button>
      <button type="submit"className=" btn-special">Guardar</button>
    </div>
  );
};

export default FormButtons;
