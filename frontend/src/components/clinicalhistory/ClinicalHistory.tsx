import React, { useState } from 'react';
// import { useEffect, useState } from 'react';
// import axios from 'axios';

// Datos hardcodeados para el historial clínico
const clinicalRecordData = [
    {
        clinicalRecordId: '3fa85f64-5717-4562-b3fc-2c963f66afa6',
        visitResolution: 'Dolor de cabeza tratado con Ibuprofeno',
        medicines: 'Ibuprofeno',
        booking: {
            bookingId: '3fa85f64-5717-4562-b3fc-2c963f66afa6',
            state: 'PENDING',
            schedulesDay: '2024-09-17',
            schedulesStart: { hour: 10, minute: 30, second: 0, nano: 0 },
            userId: '3fa85f64-5717-4562-b3fc-2c963f66afa6',
            username: 'Juan Pérez',
            payAmount: 500,
        },
        clinicalHistoryId: '3fa85f64-5717-4562-b3fc-2c963f66afa6',
        historyCode: 'CH1234',
    },
    {
        clinicalRecordId: '3fa85f64-5717-4562-b3fc-2c963f66afa7',
        visitResolution: 'Faringitis tratada con Amoxicilina',
        medicines: 'Amoxicilina',
        booking: {
            bookingId: '3fa85f64-5717-4562-b3fc-2c963f66afa6',
            state: 'PENDING',
            schedulesDay: '2024-08-20',
            schedulesStart: { hour: 11, minute: 0, second: 0, nano: 0 },
            userId: '3fa85f64-5717-4562-b3fc-2c963f66afa7',
            username: 'Carla Gómez',
            payAmount: 350,
        },
        clinicalHistoryId: '3fa85f64-5717-4562-b3fc-2c963f66afa7',
        historyCode: 'CH1235',
    },
    // Más registros pueden agregarse aquí
    ];


type ClinicalRecordForm = {
    diagnosis: string;
    treatment: string;
    medicines: string;
    comments: string;
        };

const recordsPerPage = 3; // Número de registros por página

const ClinicalDashboard: React.FC = () => {
    const [showForm, setShowForm] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [form, setForm] = useState<ClinicalRecordForm>({
        diagnosis: '',
        treatment: '',
        medicines: '',
        comments: '',
    });

  // Calcular los registros a mostrar en función de la página actual
    const startIndex = (currentPage - 1) * recordsPerPage;
    const currentRecords = clinicalRecordData.slice(startIndex, startIndex + recordsPerPage);
    const totalPages = Math.ceil(clinicalRecordData.length / recordsPerPage);

  // useEffect(() => {
//   const fetchClinicalRecords = async () => {
//     try {
//       const response = await axios.get(`/api/clinical-records?page=${currentPage}&size=${recordsPerPage}`);
//       setClinicalRecordData(response.data.content);
//       setTotalPages(response.data.totalPages);
//     } catch (error) {
//       console.error('Error fetching clinical records:', error);
//     }
//   };

//   fetchClinicalRecords();
// }, [currentPage]);

    const handleNextPage = () => {
        if (currentPage < totalPages) setCurrentPage(currentPage + 1);
    };

    const handlePreviousPage = () => {
        if (currentPage > 1) setCurrentPage(currentPage - 1);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log('Nuevo registro clínico:', form);
        // Aquí simularíamos el guardado del registro o una llamada al backend
    };


    return (
        <div>
            <h1>Dashboard del Especialista</h1>
            <button onClick={() => setShowForm(!showForm)}>
                {showForm ? 'Ver Historial Clínico' : 'Agregar Nuevo Registro'}
            </button>
      {/* Mostrar Historial Clínico con Paginación */}
        {!showForm && (
            <div>
                <h2>Historial Clínico</h2>
                {clinicalRecordData.length > 0 ? (
                <div>
                    <ul>
                        {currentRecords.map((record, index) => (
                        <li key={index}>
                        <strong>Fecha de cita:</strong> {record.booking.schedulesDay} <br />
                        <strong>Paciente:</strong> {record.booking.username} <br />
                        <strong>Diagnóstico/Resolución de visita:</strong> {record.visitResolution} <br />
                        <strong>Medicamentos:</strong> {record.medicines} <br />
                        <strong>ID de Historia Clínica:</strong> {record.clinicalHistoryId} <br />
                        <strong>Código de Historia:</strong> {record.historyCode} <br />
                        <hr />
                        </li>
                        ))}
                    </ul>
                {/* Paginación */}
                <div>
                    <button onClick={handlePreviousPage} disabled={currentPage === 1}>
                        Página Anterior
                    </button>
                    <span>
                        Página {currentPage} de {totalPages}
                    </span>
                    <button onClick={handleNextPage} disabled={currentPage === totalPages}>
                        Página Siguiente
                    </button>
                </div>
            </div>
            ) : (
                <p>No hay registros clínicos disponibles.</p>
            )}
        </div>
    )}{showForm && (
        <div>
            <h2>Registrar Información Clínica</h2>
            <form onSubmit={handleSubmit}>
            <div>
                <label>Diagnóstico:</label>
                <input
                    type="text"
                    name="diagnosis"
                    value={form.diagnosis}
                    onChange={handleInputChange}
                    required
                />
            </div>
            <div>
                <label>Tratamiento:</label>
                <textarea
                    name="treatment"
                    value={form.treatment}
                    onChange={handleInputChange}
                    required
                />
            </div>
            <div>
                <label>Medicinas:</label>
                <input
                    type="text"
                    name="medicines"
                    value={form.medicines}
                    onChange={handleInputChange}
                />
            </div>
            <div>
                <label>Comentarios adicionales:</label>
                <textarea
                    name="comments"
                    value={form.comments}
                    onChange={handleInputChange}
            />
            </div>
                <button type="submit">Guardar Registro</button>
            </form>
        </div>
        )}
    </div>
    );
};

export default ClinicalDashboard;
