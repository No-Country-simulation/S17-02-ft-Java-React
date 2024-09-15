
import { Link } from 'react-router-dom'
import { ServicesData } from './Services.data'

const Services = () => {
  return (
    <section className=' mt-5'>
        <div className='row d-flex justify-content-center mb-5'>
            <h2 className='title-service text-center'>Optimiza tu trabajo</h2>
        </div>
        <div className='service-container'>
            {ServicesData.map(service => (
                 <div key={service.id} className='service'>
                    <img className='mx-auto' src={service.icon} alt={service.title} />
                    <h4 className='text-center p-2 '>{service.title}</h4>
                    <p className='text-center p-2 '>{service.descripcion}</p>
                </div>
            ))}
        </div>
        <div className='contact-professional'>
              <h2>¿eres un profesional?</h2>
              <Link to={'/registerespecialist'}>
              <input type="text" className='contact-register' value="registrate aquí"/>
              </Link>
        </div>
    </section>
  )
}

export default Services