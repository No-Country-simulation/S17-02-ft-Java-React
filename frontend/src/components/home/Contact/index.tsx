const Contact = () => {
  return (
    <section className="container form row">
      {/*  */}
      <div className="col p-5 col-md-6 col-12">
        {/* Logo */}
        <div className=" p-5">
          <p className="badge">HeyDoc!</p>
        </div>
        <div className="p-5">
          <p className="title-contact fs-3">Escribenos</p>
          <h3 className="title mt-3 fs-2"><strong>Consulta tus dudas</strong></h3>
          <p className="mt-3 fs-4">Completa tus datos o envíanos un correo electrónico a info@heydoc.com para comenzar a utilizar las soluciones de Telemedicina más completas e innovadoras. </p>
        </div>
      </div>

      {/* Formulario de contacto */}
      <div className="col p-5 col-md-6 col-12 p-5">
        <form className="contact-form p-5 ">
          <div className="form-group mb-3">
            <label className="fs-3">Nombre *</label>
            <input
              type="text"
              className="form-control fs-3"
              id="InputName"
              name="name"
            />
          </div>
          <div className="form-group mb-3">
            <label className="fs-3">Empresa - Opcional</label>
            <input
              type="text"
              className="form-control fs-3"
              id="exampleInputEmpresa1"
              name="company"
            />
          </div>
          <div className="form-group mb-3">
            <label className="fs-3">Correo Electronico *</label>
            <input
              type="Correo"
              className="form-control fs-3"
              id="InputCorreoElectronico"
              name="email"
            />
          </div>
          <div className="form-group mb-3">
            <label className="fs-3">Teléfono *</label>
            <input type="text" className="form-control fs-3" id="InputTelefono" />
          </div>
          <div className="form-group mb-3">
            <label className="fs-3">Tu Consulta</label>
            <textarea
              className="form-control fs-3"
             id="InputConsulta"
            ></textarea>
          </div>

            <div className="form-group mb-3 d-flex justify-content-end">
          <button type="submit" className=" btn-navbar-prof btn-lg fs-3">
            Enviar
          </button>

            </div>
        </form>
      </div>
    </section>
  );
};

export default Contact;
