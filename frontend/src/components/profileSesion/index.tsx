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
}

interface City {
  cityId: number;
  cityName: string;
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
  });

  const [cities, setCities] = useState<City[]>([]);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const response = await axios.get<City[]>("/api/city", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setCities(response.data);
      } catch (error) {
        console.error("Error al obtener las ciudades:", error);
      }
    };

    fetchCities();
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

    const requestBody = {
      profileName: formData.nombre,
      profileLastname: formData.apellido,
      documentType: formData.tipoDocumento,
      documentNumber: formData.documentNumber,
      avatarUrl: formData.imagen || "",
      birth: formData.fechaNacimiento,
      address: formData.direccion,
      city: cities.find((city) => city.cityName === formData.ciudad) || {
        cityId: 0,
        cityName: formData.ciudad,
      },
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

    try {
      const response = await axios.post("/api/profiles", requestBody, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      console.log("Perfil creado con éxito:", response.data);
      navigate("/");
    } catch (error) {
      console.error("Error al enviar la solicitud:", error);
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
        <label>Ciudad</label>
        <select name="ciudad" value={formData.ciudad} onChange={handleChange}>
          <option value="">Selecciona una ciudad</option>
          {cities.map((city) => (
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
