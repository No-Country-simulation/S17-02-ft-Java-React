import React, { useEffect, useState } from "react";
import axios from "axios";

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
    }, [page]);

    const handleNextPage = () => {
        if (page < totalPage - 1) {
            setPage(page + 1);
        }
    };

    const handlePreviosPage = () => {
        if (page > 0) {
            setPage(page - 1);
        }
    }

    if (loading) return <div>Cargado registro clínicos...</div>;
    if (error) return <div>{error}</div>;
    if (clinicalRecords.length === 0) return <div>No hay registros clínicos disponibles.</div>;

return (
    <div>
        <h2>Historial clínicos</h2>
        <ul>
            {clinicalRecords.map((record) => (
                <li key={record.clinicalRecordId}>
                    <strong>Código de historial:</strong> {record.historyCode} <br />
                    <strong>Resolución de la visita:</strong> {record.visitResolution} <br />
                    <strong>Medicamentos:</strong> {record.medicine} <br />
                    <strong>Estado de la reserva:</strong> {record.booking.state} <br />
                    <strong>Fecha de la cita</strong> {record.booking.scheduelesDay}
                </li>
            ))}
        </ul>
        <div>
            <button onClick={handlePreviosPage} disabled={page === 0}>
                Página anterior
            </button>
            <button onClick={handleNextPage} disabled={page >= totalPage - 1}>
                Página siguiente
            </button>
        </div>
    </div>
);
};

export default ClinicalHistory;
