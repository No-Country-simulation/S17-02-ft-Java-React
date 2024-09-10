import image from '../../../assets/images/About/img_about.png'

const About = () => {
  return (
    <section className="d-flex justify-content-center flex-wrap mt-5 gap-3 gap-md-4">
    <div className="row about-container">
      <div className="col-md-6 col-12 p-5">
        <h2 className="title mt-3 fs-2">Sobre Nosotros</h2>
        <span className="subtitle mt-3 fs-2"><strong>HeyDoc es una plataforma que te ayuda a mejorar tu salud</strong></span>
        <p className="mt-3 fs-3">
          Pasa consulta online por chat y videollamada, emite recetas e informes,
          envía citas y solicita cobros a tus pacientes. Todo desde una misma
          plataforma.
        </p>
      </div>
      <div className="col-md-6 col-12 p-5">
        <div className="d-flex justify-content-center">
          <img src={image} alt="images" className="w-75" />
        </div>
      </div>
    </div>
  </section>
  
  )
}

export default About