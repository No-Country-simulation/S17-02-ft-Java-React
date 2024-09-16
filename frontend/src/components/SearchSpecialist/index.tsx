import { useState, useEffect } from "react";
import { getSpecialists } from "../../services/specialistsService";

export interface Specialist {
  specialistId: string;
  specialistCode: string;
  specialtyId?: number;
  specialtyName?: string;
  bookingPrice?: number;
  reputation?: number;
  specialistName: string;
  specialistLastname: string;
}

function PerSpecialist({
  specialistLastname,
  specialistName,
  specialtyName,
}: Specialist) {
  return (
    <div className="specialist-card">
      <div className="specialist-header">
        <h2 className="specialist-name">
          {`${specialistName ?? "Nombre"} ${specialistLastname ?? "Apellido"}`}
        </h2>
        <p className="specialty-name">{`${specialtyName ?? "Especialidad"}`}</p>
      </div>
      <div className="specialist-body"></div>
      <div className="specialist-footer">
        <button className="btn">Obtener un turno</button>
      </div>
    </div>
  );
}

export function SearchSpecialist() {
  const [specialists, setSpecialists] = useState<Specialist[]>([]);
  const [searchValue, setSearchValue] = useState("");
  const [filteredSpecialists, setFilteredSpecialists] = useState(specialists);

  const filterProperties: (keyof Specialist)[] = [
    "specialistName",
    "specialistLastname",
    "specialtyName",
  ];

  const obtainSpecialists = async () => {
    const response = await getSpecialists();
    if (response) {
      setSpecialists(response);
    } else {
      console.log("Ha ocurrido un error");
    }
  };

  const handleSearch = (value: string) => {
    const lowercasedValue = value.toLowerCase();
    const filtered = specialists.filter((specialist: Specialist) =>
      filterProperties.some((prop) =>
        specialist[prop]?.toString().toLowerCase().includes(lowercasedValue)
      )
    );
    setFilteredSpecialists(filtered);
  };

  useEffect(() => {
    const intervalId = setInterval(() => {
      if (specialists.length === 0) {
        console.log();
        obtainSpecialists();
      }
    }, 2000);
    return () => clearInterval(intervalId);
  }, [specialists]);

  useEffect(() => {
    handleSearch(searchValue);
  }, [searchValue, specialists, filterProperties]);

  return (
    <div className="search-specialist-container">
      <div className="search-input-container">
        <div className="input-wrapper">
          <input
            type="text"
            placeholder="Buscar especialistas"
            value={searchValue}
            onChange={(e) => setSearchValue(e.target.value)}
          />
          <span className="search-icon">🔍</span>
        </div>
      </div>
      <div className="specialist-list">
        {filteredSpecialists.length > 0 ? (
          filteredSpecialists.map(
            (
              {
                specialtyId,
                specialistCode,
                specialistLastname,
                specialistName,
                specialistId,
                specialtyName,
              },
              index
            ) => (
              <PerSpecialist
                key={index}
                specialistCode={specialistCode}
                specialistLastname={specialistLastname}
                specialistName={specialistName}
                specialtyName={specialtyName}
                specialtyId={specialtyId}
                specialistId={specialistId}
              />
            )
          )
        ) : (
          <p>No se encontraron especialistas</p>
        )}
      </div>
    </div>
  );
}
