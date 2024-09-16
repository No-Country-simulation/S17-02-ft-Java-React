import agenda from '../../../assets/Icons/Services/Agenda2.svg'
import recetas from '../../../assets/Icons/Services/recetas.svg'
import informes from '../../../assets/Icons/Services/Informes.svg'
import profile from '../../../assets/Icons/Services/profile.svg'

export const ServicesData = [
    {
        id: 1,
        title: 'Historial Clínico',
        descripcion : 'Facilite a los profesionales la gestión de historiales electrónicos de pacientes con una plataforma de registro clínico simplificado.',    
        icon: agenda
    },
    {
        id: 2,
        title: 'Recetas médicas electrónicas',
        descripcion : 'Emite recetas médicas electrónicas a tus pacientes que recibirán por email y serán notificados por chat',
        icon: recetas
       
    },
    {
        id: 3,
        title: 'Informes de salud',
        descripcion : 'Crea informes de salud tras finalizar la cita y que el paciente recibirá. Quedarán en su historial clínico.',
        icon: informes
    },
    {
        id:4,
        title: 'Seguimiento del paciente',
        descripcion:'Programa mensajes en una fecha concreta para hacer seguimientos de salud, HeyDoc! lo recordará con una alerta temprana, a ti y al paciente',
        icon: profile

    }
]