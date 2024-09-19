import medicoClinico from '/Specialists/medicoClinico.png'
import pediatrics from '/Specialists/pediatria1.png'
import psycology from '/Specialists/Psychologist1.png'
import dermatologia from '/Specialists/Dermatologia.png'
import cardiologia from '/Specialists/Cardiologist1.png'
const Hero = () => {
  return (
    <div className="container-hero p-5">
        
       <h1 className=" main-hero mx-auto text-center"> Conecta inmediatamente con los especialistas</h1>
       
        <div className="card-container-hero">
          <div className="card clinics">
            <img src={medicoClinico} alt="favSpecialities" />
            <p className="spec-name">Médico Clínico</p>
          </div>       
          <div className="card pediatrics">
            <img src={pediatrics} alt="favSpecialities" />
            <p className="spec-name">Pediatría</p>
          </div>       
          <div className="card psych">
            <img src={psycology} alt="favSpecialities" />
            <p className="spec-name">Psicología</p>
          </div>       
          <div className="card dermat">
            <img src={dermatologia} alt="favSpecialities" />
            <p className="spec-name">Dermatología</p>
          </div>       
          <div className="card cardio">
            <img src={cardiologia} alt="favSpecialities" />
            <p className="spec-name">Cardiología</p>
          </div>       
        </div>
    </div>
  )
}

export default Hero