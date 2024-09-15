import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import { useAuth } from "../../../context/context"; // Ajusta la ruta del contexto según sea necesario

interface ScheduleStart {
  hour: number;
  minute: number;
  second: number;
  nano: number;
}

interface Appointment {
  bookingId: string;
  state: string;
  schedulesDay: string;
  schedulesStart: ScheduleStart;
  userId: string;
  username: string;
  payAmount: number;
}

const ListaCitas: React.FC = () => {
  const { token, roleId, userId } = useAuth(); // Obtén el token, roleId y userId del contexto
  const [appointments, setAppointments] = useState<Appointment[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const response = await axios.get<Appointment[]>("/api/bookings", {
          params: { roleId, userId }, // Ajusta según cómo acepte la API estos parámetros
          headers: {
            Authorization: `Bearer ${token}`, // Incluye el token en el encabezado de autorización
          },
        });
        console.log("API response:", response.data); // Log para verificar el formato de la respuesta
        // Verifica si la respuesta es un array, de lo contrario ajusta el acceso a datos
        setAppointments(Array.isArray(response.data) ? response.data : []);
        setLoading(false);
      } catch (err) {
        if (axios.isAxiosError(err) && err.message) {
          setError(err.message);
        } else {
          setError("An unexpected error occurred");
        }
        setLoading(false);
      }
    };

    fetchAppointments();
  }, [token, roleId, userId]); // Vuelve a obtener los datos si cambia el token, roleId o userId

  const handleVideoCall = (bookingId: string) => {
    // Aquí puedes agregar la lógica para iniciar una videollamada
    console.log(`Iniciar videollamada para la cita ${bookingId}`);
  };

  const handleViewMedicalHistory = (bookingId: string) => {
    // Aquí puedes agregar la lógica para ver el historial clínico
    console.log(`Ver historial clínico para la cita ${bookingId}`);
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error fetching data: {error}</p>;

  return (
    <div className="container p-3">
      <h1 className="text-center mb-4">Listado de Citas</h1>
      <div className="mb-3">
        <div className="mb-3 fs-5 gap-3">
          <label className="form-label">Buscar</label>
          <input
            type="text"
            name=""
            size={50}
            className="form-control"
            placeholder=""
            aria-describedby="helpId"
          />
          <small id="helpId" className="text-muted">
            Buscador de turnos
          </small>
        </div>
      </div>
      <Table striped="columns" className="fs-5">
        <thead>
          <tr>
            <th className="text-center">#</th>
            <th className="text-center">Paciente</th>
            <th className="text-center">Fecha</th>
            <th className="text-center">Horario</th>
            <th className="text-center">Detalles</th>
            <th className="text-center">Videollamada</th>
            <th className="text-center">Historial Clínico</th>
          </tr>
        </thead>
        <tbody>
          {appointments.length > 0 ? (
            appointments.map((appointment, index) => (
              <tr key={appointment.bookingId}>
                <td className="text-center fs-5">{index + 1}</td>
                <td className="text-center fs-5">{appointment.username}</td>
                <td className="text-center fs-5">{appointment.schedulesDay}</td>
                <td className="text-center fs-5">{`${appointment.schedulesStart.hour}:${appointment.schedulesStart.minute}`}</td>
                <td className="text-center fs-5">Detalles de la sesión</td>
                <td className="text-center fs-5">
                  <button
                    className="btn btn-primary fs-5"
                    onClick={() => handleVideoCall(appointment.bookingId)}
                  >
                    Iniciar Videollamada
                  </button>
                </td>
                <td className="text-center fs-5">
                  <button
                    className="btn btn-primary fs-5"
                    onClick={() =>
                      handleViewMedicalHistory(appointment.bookingId)
                    }
                  >
                    Ver Historial Clínico
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={7} className="text-center">
                No hay citas disponibles
              </td>
            </tr>
          )}
        </tbody>
      </Table>
    </div>
  );
};

export default ListaCitas;
