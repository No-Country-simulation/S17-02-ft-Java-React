import React, { useEffect, useState } from "react";
import axios from "axios";

//TO-DO Puedes utilizar este componente con datos hardcodeados para mostrar una versión preliminar de la pantalla. 
//Luego, cuando tu compañero tenga lista la conexión al backend, solo tendrán que reemplazar la parte de la llamada a la API.

interface Booking {
    bookingId: string;
    state: string;
    scheduelesDay: string;
    scheduelesStart: {
        hour: number;
        minute: number;
        second: number;
        nano: number;
    };
    userId: string;
    username: string;
    payAmount: number;
}

interface ClinicalRecord {
    clinicalRecordId: string;
    visitResolution: string;
    medicine: string;
    booking: Booking;
    clinicalHistoryId: string;
    historyCode: string;
}
/*
const ClinicalHistory: React.FC = () => {
    const [clinicalRecords, setClinicalRecords] = useState<ClinicalRecord[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [page, setPage] = useState<number>(0);
    const [totalPage, setTotalPages] = useState<number>(1);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchClinicalRecords = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await axios.get("/api/clinical-records", {
                    params: { page, size:10 },
                });
                setClinicalRecords(response.data);
                setTotalPages(response.data.totalPages);
            } catch (err) {
                setError("Error fetching clinical records");
            } finally {
                setLoading(false);
            }
        };

        fetchClinicalRecords();
    }, [page]);  */

    const ClinicalHistory: React.FC = () => {
        // Simulamos una respuesta de datos hardcodeada
        const clinicalRecords: ClinicalRecord[] = [
            {
            clinicalRecordId: "1",
            visitResolution: "Resolución de la visita 1",
            medicines: "Paracetamol, Ibuprofeno",
            booking: {
                bookingId: "1",
                state: "PENDING",
                schedulesDay: "2024-09-05",
                    schedulesStart: {
                    hour: 10,
                    minute: 0,
                    second: 0,
                    nano: 0,
                },
                userId: "123",
                username: "Juan Perez",
                payAmount: 50,
            },
            clinicalHistoryId: "HIST001",
            historyCode: "HC001",
        },
        {
            clinicalRecordId: "2",
            visitResolution: "Resolución de la visita 2",
            medicines: "Amoxicilina, Vitamina C",
            booking: {
                bookingId: "2",
                state: "COMPLETED",
                schedulesDay: "2024-08-15",
                schedulesStart: {
                    hour: 14,
                    minute: 30,
                    second: 0,
                    nano: 0,
                },
                userId: "124",
                username: "Maria Gomez",
                payAmount: 70,
            },
            clinicalHistoryId: "HIST002",
            historyCode: "HC002",
            },
        ];

        const [page, setPage] = useState<number>(0);
        const recordsPerPage = 1; // Mostramos solo 1 registro por página para testear la paginación
        const totalPages = Math.ceil(clinicalRecords.length / recordsPerPage);

        const handleNextPage = () => {
            if (page < totalPages - 1) {
                setPage(page + 1);
            }
        };

        const handlePreviousPage = () => {
            if (page > 0) {
                setPage(page - 1);
            }
        };

        const paginatedRecords = clinicalRecords.slice(
          page * recordsPerPage,
          (page + 1) * recordsPerPage
        );

        return (
          <div>
            <h2>Historial Clínico</h2>
            {paginatedRecords.map((record) => (
              <div key={record.clinicalRecordId}>
                <strong>Código de historial:</strong> {record.historyCode} <br />
                <strong>Resolución de la visita:</strong> {record.visitResolution} <br />
                <strong>Medicamentos:</strong> {record.medicines} <br />
                <strong>Estado de la reserva:</strong> {record.booking.state} <br />
                <strong>Fecha de la cita:</strong> {record.booking.schedulesDay} <br />
                <strong>Usuario:</strong> {record.booking.username} <br />
                <strong>Monto de pago:</strong> ${record.booking.payAmount} <br />
              </div>
            ))}
            <div>
              <button onClick={handlePreviousPage} disabled={page === 0}>
                Página anterior
              </button>
              <button onClick={handleNextPage} disabled={page >= totalPages - 1}>
                Página siguiente
              </button>
            </div>
          </div>
        );
      };
      
      export default ClinicalHistory;
