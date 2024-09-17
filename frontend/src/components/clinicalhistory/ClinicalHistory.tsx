import React, { useState } from 'react';

// Datos hardcodeados para el historial clínico
const clinicalHistoryData = [
  {
    date: '2024-09-17',
    diagnosis: 'Dolor de cabeza',
    treatment: 'Ibuprofeno',
    comments: 'Recomendado reposo',
  },
  {
    date: '2024-08-20',
    diagnosis: 'Faringitis',
    treatment: 'Amoxicilina',
    comments: 'Evitar bebidas frías',
  },
];

type ClinicalRecordForm = {
  diagnosis: string;
  treatment: string;
  medicines: string;
  comments: string;
};

const ClinicalDashboard: React.FC = () => {
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState<ClinicalRecordForm>({
    diagnosis: '',
    treatment: '',
    medicines: '',
    comments: '',
  });

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

      {/* Botón para alternar entre historial y formulario */}
      <button onClick={() => setShowForm(!showForm)}>
        {showForm ? 'Ver Historial Clínico' : 'Agregar Nuevo Registro'}
      </button>

      {/* Mostrar Historial Clínico */}
      {!showForm && (
        <div>
          <h2>Historial Clínico</h2>
          {clinicalHistoryData.length > 0 ? (
            <ul>
              {clinicalHistoryData.map((entry, index) => (
                <li key={index}>
                  <strong>Fecha:</strong> {entry.date} <br />
                  <strong>Diagnóstico:</strong> {entry.diagnosis} <br />
                  <strong>Tratamiento:</strong> {entry.treatment} <br />
                  <strong>Comentarios:</strong> {entry.comments} <br />
                  <hr />
                </li>
              ))}
            </ul>
          ) : (
            <p>No hay historial disponible.</p>
          )}
        </div>
      )}

      {/* Mostrar Formulario para Nuevo Registro */}
      {showForm && (
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
