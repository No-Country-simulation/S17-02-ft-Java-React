

const Hero = () => {
  return (
    <div className="container-hero p-5">
        
       <h1 className=" main-hero mx-auto text-center"> Conecta inmediatamente con los especialistas</h1>
       
        <div className="d-flex justify-content-center flex-wrap mt-5 gap-3 gap-md-4">
          <div className="card clinics">
            <p className="spec-name">Médico Clínico</p>
          </div>       
          <div className="card pediatrics">
            <p className="spec-name">Pediatría</p>
          </div>       
          <div className="card psych">
            <p className="spec-name">Psicología</p>
          </div>       
          <div className="card dermat">
            <p className="spec-name">Dermatología</p>
          </div>       
          <div className="card cardio">
            <p className="spec-name">Cardiología</p>
          </div>       
        </div>
    </div>
  )
}

export default Hero