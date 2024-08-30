import FormInput from "./formImput/index.tsx";
import useForm from "./useForm/index.tsx";

export const RegisterUser = () => {
  const { form, errors, handleChange, handleSubmit } = useForm();

  return (
    <div className="register-user">
      <h2>Registro de Usuarios</h2>
      {Object.keys(errors).length > 0 && (
        <ul>
          {Object.entries(errors).map(([key, error]) => (
            <li key={key}>{error}</li>
          ))}
        </ul>
      )}
      <form onSubmit={handleSubmit}>
        <FormInput
          id="email"
          name="Email"
          type="email"
          value={form.email}
          onChange={handleChange}
          error={errors.email}
        />
        <FormInput
          id="password"
          name="Contraseña"
          type="password"
          value={form.password}
          onChange={handleChange}
          error={errors.password}
        />
        <button className="btn btn-secondary" type="submit">Regístrate</button>
      </form>
    </div>
  );
};
