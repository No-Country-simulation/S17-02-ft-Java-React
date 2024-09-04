

const Hero = () => {
  return (
    <div className="container-hero p-5">
        
       <h1 className=" main-hero mx-auto text-center"> Conecta inmediatamente con los especialistas</h1>
       
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