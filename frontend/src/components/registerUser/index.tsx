import React from "react";
import useRegisterForm from "./useRegisterForm";
import FormField from "./formField";

export const RegisterUser: React.FC = () => {
  const formik = useRegisterForm();
  const { values, handleChange, handleBlur, handleSubmit, errors, touched } =
    formik;

  return (
    <div className="register-container">
      <div></div>

      <form onSubmit={handleSubmit} className="form-container">
        <div className="d-flex justify-content-start">
          <h2 className="">Bienvenidos Pacientes</h2>
        </div>

        <FormField
          name="username"
          type="text"
          value={values.username}
          onChange={handleChange}
          onBlur={handleBlur}
          error={touched.username ? errors.username : undefined}
        />

        <FormField
          name="password"
          type="password"
          value={values.password}
          onChange={handleChange}
          onBlur={handleBlur}
          error={touched.password ? errors.password : undefined}
        />

        <FormField
          name="confirmPassword"
          type="password"
          value={values.confirmPassword}
          onChange={handleChange}
          onBlur={handleBlur}
          error={touched.confirmPassword ? errors.confirmPassword : undefined}
        />
        <div className="footer-register">
          <div className="checkbox-footer">
            <input type="checkbox" name="terminos" id="" />{" "}
            <p>
              Acepto los <span>términos y condiciones</span>
            </p>
          </div>
          <div className="checkbox-footer">
            <input type="checkbox" name="terminos" id="" />{" "}
            <p>
              Acepto las <span>políticas de privacidad</span>
            </p>
          </div>
          <div className="btn-footer">
            <button type="submit" className="btn-navbar-prof">
              Continuar
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};
