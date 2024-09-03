

const Hero = () => {
  return (
    <div className="container mb-5 mt-5  p-5">
        <div className="d-flex flex-column justify-content-center">
       <h1 className="title text-center fs-1"><strong>Cuidamos de tus pacientes</strong></h1>
       <h2 className="subtitle text-center mx-auto fs-2 w-50">Ofrezca a sus pacientes una atención continua, permitiéndoles recibir atención médica desde cualquier punto y cumpliendo con los estándares de seguridad y privacidad más rigurosos.</h2>
        </div>
        <div className="d-flex justify-content-center flex-wrap mt-5 gap-3 gap-md-4">
        <button className="btn btn-primary btn-lg flex-grow-1"><strong>Pediatría</strong></button>
        <button className="btn btn-primary btn-lg flex-grow-1"><strong>Cardióloga</strong></button>
        <button className="btn btn-primary btn-lg flex-grow-1"> <strong>Ginecología</strong></button>
        <button className="btn btn-primary btn-lg flex-grow-1"><strong>Dermatología</strong> </button>
        <button className="btn btn-primary btn-lg flex-grow-1"><strong>Neurología</strong></button>
        </div>
    </div>
  )
}

export default Hero