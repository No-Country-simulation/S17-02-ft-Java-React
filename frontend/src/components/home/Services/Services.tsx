
import { ServicesData } from './Services.data'

const Services = () => {
  return (
    <section className='container mt-5'>
        <div className='row d-flex justify-content-center mb-5'>
            <h2 className='text-center'>Optimiza tu trabajo</h2>
        </div>
        <div className='row mb-5'>
            {ServicesData.map(service => (
                 <div key={service.id} className='col-md-4 col-12 d-flex flex-column align-items-center p-5 gap-3'>
                    <img className='w-25 mx-auto' src={service.icon} alt={service.title} />
                    <h2 className='text-center p-4 fs-2'>{service.title}</h2>
                    <p className='text-center p-5 fs-3'>{service.descripcion}</p>
                </div>
            ))}
        </div>
        
    </section>
  )
}

export default Services