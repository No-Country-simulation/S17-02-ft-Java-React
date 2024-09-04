

const Hero = () => {
  return (
    <div className="container-hero p-5">
        <div className="d-flex flex-column justify-content-center">
       <h1 className=" main-hero text-center"> Conecta inmediatamente con los especialistas</h1>
       <h2 className="subtitle-hero text-center mx-auto fs-2 w-75">Ofrezca a sus pacientes una atención continua, permitiéndoles recibir atención médica desde cualquier punto y cumpliendo con los estándares de seguridad y privacidad más rigurosos.</h2>
        </div>
        <div className="d-flex justify-content-center flex-wrap mt-5 gap-3 gap-md-4">
        <button className="btn btn-hero btn-lg flex-grow-1"><strong>Pediatría</strong></button>
        <button className="btn btn-hero btn-lg flex-grow-1"><strong>Cardiología</strong></button>
        <button className="btn btn-hero btn-lg flex-grow-1"> <strong>Ginecología</strong></button>
        <button className="btn btn-hero btn-lg flex-grow-1"><strong>Dermatología</strong> </button>
        <button className="btn btn-hero btn-lg flex-grow-1"><strong>Neurología</strong></button>
        </div>
    </div>
  )
}

export default Hero