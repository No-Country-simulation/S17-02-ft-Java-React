import FormInput from "./formInPut/index.tsx";
import useForm from "./useForm/index.tsx";

export const RegisterClinic = () => {
  const { formData, handleChange, handleSubmit } = useForm();

  return (
    <div>
      <h1>Registro de Clínica</h1>
      <form onSubmit={handleSubmit}>
        <FormInput
          id="nombre"
          label="Nombre de la Clínica"
          type="text"
          value={formData.nombre}
          onChange={handleChange}
          required
        />
        <FormInput
          id="correo"
          label="Correo Electrónico"
          type="email"
          value={formData.correo}
          onChange={handleChange}
          required
        />
        <FormInput
          id="telefono"
          label="Teléfono"
          type="tel"
          value={formData.telefono}
          onChange={handleChange}
          required
        />
        <FormInput
          id="direccion"
          label="Dirección"
          type="text"
          value={formData.direccion}
          onChange={handleChange}
          required
        />
        <button type="submit">Registrar Clínica</button>
      </form>
    </div>
  );
};
