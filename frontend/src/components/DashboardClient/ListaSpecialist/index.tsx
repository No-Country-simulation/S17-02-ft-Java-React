import React, { useState, useEffect } from "react";
import { Form, InputGroup, Table } from "react-bootstrap";
import axios from "axios";
import { useAuth } from "../../../context/context.tsx";

interface Specialist {
  specialistId: number;
  specialistName: string;
  specialistLastname: string;
  specialistCode: string;
  specialtyName: string;
}

const ListaEspecialist = () => {
  const { token } = useAuth();
  const [specialists, setSpecialists] = useState<Specialist[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [searchQuery, setSearchQuery] = useState<string>("");

  useEffect(() => {
    const fetchSpecialists = async () => {
      try {
        setLoading(true);
        const response = await axios.get("/api/specialist", {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        console.log("API Response:", response.data);

        const data = response.data.content;
        if (Array.isArray(data)) {
          setSpecialists(data);
        } else {
          console.error("Unexpected data format:", response.data);
          setError("Unexpected data format");
        }
      } catch (error) {
        console.error("Error fetching specialists:", error);
        setError("Error fetching specialists");
      } finally {
        setLoading(false);
      }
    };

    fetchSpecialists();
  }, [token]);

  const filteredSpecialists = specialists.filter((specialist) =>
    specialist.specialtyName.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(event.target.value);
  };

  if (loading) return <p>Loading specialists...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h1 className="text-center">Lista de especialistas</h1>

      <div className="mb-3">
        <Form.Label htmlFor="search-input">
          Buscar especialistas por especialidad
        </Form.Label>
        <InputGroup className="mb-3">
          <InputGroup.Text id="basic-addon3">🔎</InputGroup.Text>
          <Form.Control
            id="search-input"
            aria-describedby="basic-addon3"
            value={searchQuery}
            onChange={handleSearchChange}
            placeholder="Buscar por especialidad"
          />
        </InputGroup>
      </div>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th className="text-center">#</th>
            <th className="text-center">Nombre especialista</th>
            <th className="text-center">Matricula</th>
            <th className="text-center">Especialidad</th>
            <th className="text-center">Horarios</th>
          </tr>
        </thead>
        <tbody>
          {filteredSpecialists.length > 0 ? (
            filteredSpecialists.map((specialist, index) => (
              <tr key={specialist.specialistId}>
                <td className="text-center">{index + 1}</td>
                <td className="text-center">
                  {specialist.specialistName} {specialist.specialistLastname}
                </td>
                <td className="text-center">{specialist.specialistCode}</td>
                <td className="text-center">{specialist.specialtyName}</td>
                <td className="text-center">
                  <button className="btn-navbar-pct">
                    Ver horarios disponibles
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={5} className="text-center">
                No se encontraron especialistas
              </td>
            </tr>
          )}
        </tbody>
      </Table>
    </div>
  );
};

export default ListaEspecialist;
