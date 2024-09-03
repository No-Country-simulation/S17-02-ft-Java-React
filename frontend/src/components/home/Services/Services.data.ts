import agenda from '../../../assets/Icons/Services/agenda.svg'
import historialClinico from '../../../assets/Icons/Services/historialClinico.svg'
import videoLlamadas from '../../../assets/Icons/Services/videoLlamadas.svg'

export const ServicesData = [
    {
        id: 1,
        title: 'Historial Clínico',
        descripcion : 'Facilite a los profesionales la gestión de historiales electrónicos de pacientes con una plataforma de registro clínico simplificado.',    
        icon: historialClinico
    },
    {
        id: 2,
        title: 'Agenda',
        descripcion : 'Gestione eficientemente las agendas de los profesionales y permita a los pacientes realizar citas médicas mediante un portal web.',
        icon: agenda
       
    },
    {
        id: 3,
        title: 'Videoconsulta',
        descripcion : 'El paciente obtiene detalles y un enlace para acceder a la consulta virtual directamente desde cualquier navegador, sin instalar aplicaciones.',
        icon: videoLlamadas
    },
]