const Contact = () => {
  return (
    <section className="container row">
      {/*  */}
      <div className="col p-5 col-md-6 col-12">
        {/* Logo */}
        <div className=" p-5">
          <p>HeyDoc!</p>
        </div>
        <div className="p-5">
          <p className="subtitle fs-3">Contactenos</p>
          <h3 className="title mt-3 fs-2"><strong>Consulta tus dudas</strong></h3>
          <p className="mt-3 fs-4">Figma ipsum component variant main layer. Underline comment style strikethrough editor effect. Invite ipsum image italic fill comment. Group object variant ellipse variant flatten fill. Draft group hand prototype auto community community italic. Inspect link layer frame image underline. Link layout follower object frame. </p>
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
          <button type="submit" className=" btn btn-primary btn-lg fs-3">
            Enviar Mensaje
          </button>

            </div>
        </form>
      </div>
    </section>
  );
};

export default Contact;
