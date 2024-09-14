import React, { useState, useEffect, ChangeEvent, FormEvent } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context.tsx";
import InputField from "../profileSesion/inputField";
import FileInputWithPreview from "../profileSesion/fileInput";
import FormButtons from "../profileSesion/formButtons";

interface FormData {
  nombre: string;
  apellido: string;
  tipoDocumento: string;
  documentNumber: string;
  imagen: string | null;
  fechaNacimiento: string;
  direccion: string;
  ciudad: string;
  departamento: string;
}

interface City {
  cityId: number;
  cityName: string;
}

interface Department {
  departmentId: number;
  departmentName: string;
  cities: City[];
}

const Formulario: React.FC = () => {
  const { token, roleId, username, password, userId } = useAuth();
  const [formData, setFormData] = useState<FormData>({
    nombre: "",
    apellido: "",
    tipoDocumento: "",
    documentNumber: "",
    imagen: null,
    fechaNacimiento: "",
    direccion: "",
    ciudad: "",
    departamento: "",
  });

  const [cities, setCities] = useState<City[]>([]);
  const [departments, setDepartments] = useState<Department[]>([]);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchCitiesAndDepartments = async () => {
      try {
        const [citiesResponse, departmentsResponse] = await Promise.all([
          axios.get<City[]>("/api/city", {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }),
          axios.get<Department[]>("/api/department", {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }),
        ]);

        setCities(citiesResponse.data);
        setDepartments(departmentsResponse.data);
      } catch (error) {
        console.error("Error al obtener las ciudades o departamentos:", error);
      }
    };

    fetchCitiesAndDepartments();
  }, [token]);

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value, type } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: type === "select-one" ? value : value,
    }));
  };

  const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setFormData((prevData) => ({
        ...prevData,
        imagen: URL.createObjectURL(file),
      }));
    }
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!token || !roleId || !username || !password || !userId) {
      console.error("Faltan el token, roleId, username, password o userId");
      return;
    }

    const selectedDepartment = departments.find(
      (department) => department.departmentName === formData.departamento
    );
    const selectedCity = selectedDepartment?.cities.find(
      (city) => city.cityName === formData.ciudad
    );

    const requestBody = {
      profileName: formData.nombre,
      profileLastname: formData.apellido,
      documentType: formData.tipoDocumento,
      documentNumber: formData.documentNumber,
      avatarUrl: formData.imagen || "",
      birth: formData.fechaNacimiento,
      address: formData.direccion,
      city: selectedCity || { cityId: 0, cityName: formData.ciudad },
      user: {
        userId,
        username,
        password,
        roles: [
          {
            roleId,
            roleName: "string",
            roleDescription: "string",
          },
        ],
      },
    };

    console.log("Enviando solicitud con el siguiente cuerpo:", requestBody);

    try {
      const response = await axios.post("/api/profiles", requestBody, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      console.log("Perfil creado con éxito:", response.data);

      // Redireccionar según el roleId
      if (roleId === "9c765b7d-9eec-421b-85c6-6d53bcd002da") {
        navigate("/dashboardEspecialista");
      } else if (roleId === "2326ec2c-4f97-4007-b52c-ba5561b434b9") {
        navigate("/dashboardCliente");
      } else {
        navigate("/");
      }
    } catch (error) {
      console.error("Error al enviar la solicitud:", error);
      if (axios.isAxiosError(error)) {
        console.error("Detalles del error:", error.response?.data);
      }
    }
  };

  const handleSkip = () => {
    navigate("/");
  };

  return (
    <form onSubmit={handleSubmit} className="form-profile">
      <h3>Información personal</h3>
      <InputField
        label="Nombre"
        type="text"
        name="nombre"
        value={formData.nombre}
        onChange={handleChange}
      />
      <InputField
        label="Apellido"
        type="text"
        name="apellido"
        value={formData.apellido}
        onChange={handleChange}
      />
      <InputField
        label="Tipo de Documento"
        type="text"
        name="tipoDocumento"
        value={formData.tipoDocumento}
        onChange={handleChange}
      />
      <InputField
        label="Número de Documento"
        type="text"
        name="documentNumber"
        value={formData.documentNumber}
        onChange={handleChange}
      />
      <FileInputWithPreview
        onFileChange={handleFileChange}
        imageUrl={formData.imagen}
      />
      <InputField
        label="Fecha de Nacimiento"
        type="date"
        name="fechaNacimiento"
        value={formData.fechaNacimiento}
        onChange={handleChange}
      />
      <InputField
        label="Dirección"
        type="text"
        name="direccion"
        value={formData.direccion}
        onChange={handleChange}
      />
      <div>
        <label>Provincia</label>
        <select
          name="departamento"
          value={formData.departamento}
          onChange={handleChange}
        >
          <option value="">Selecciona un departamento</option>
          {departments.map((department) => (
            <option
              key={department.departmentId}
              value={department.departmentName}
            >
              {department.departmentName}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Ciudad</label>
        <select name="ciudad" value={formData.ciudad} onChange={handleChange}>
          <option value="">Selecciona una ciudad</option>
          {cities
            .filter((city) =>
              departments
                .find(
                  (department) =>
                    department.departmentName === formData.departamento
                )
                ?.cities.some((c) => c.cityId === city.cityId)
            )
            .map((city) => (
              <option key={city.cityId} value={city.cityName}>
                {city.cityName}
              </option>
            ))}
        </select>
      </div>
      <div className="container-fluid form-buttons-cont">
        <FormButtons onSkip={handleSkip} />
      </div>
    </form>
  );
};

export default Formulario;
