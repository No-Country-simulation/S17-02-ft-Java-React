import React, { useState, ChangeEvent, FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context.tsx";
import InputField from "../profileSesion/inputField";
import FileInputWithPreview from "../profileSesion/fileInput";
import FormButtons from "../profileSesion/formButtons";

interface FormData {
  nombre: string;
  apellido: string;
  tipoDocumento: string;
  documentNumber: string; // Add this field
  imagen: string | null;
  fechaNacimiento: string;
  direccion: string;
  ciudad: string;
}

const Formulario: React.FC = () => {
  const { token, roleId, username, password, userId } = useAuth();
  const [formData, setFormData] = useState<FormData>({
    nombre: "",
    apellido: "",
    tipoDocumento: "",
    documentNumber: "", // Initialize this field
    imagen: null,
    fechaNacimiento: "",
    direccion: "",
    ciudad: "",
  });

  const navigate = useNavigate();

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
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
      console.error("Token, roleId, username, password, or userId is missing");
      return;
    }

    const requestBody = {
      profileName: formData.nombre,
      profileLastname: formData.apellido,
      documentType: formData.tipoDocumento,
      documentNumber: formData.documentNumber, // Include documentNumber
      avatarUrl: formData.imagen || "",
      birth: formData.fechaNacimiento,
      address: formData.direccion,
      city: { cityId: 0, cityName: formData.ciudad },
      user: {
        userId,
        username,
        password,
        roles: [
          {
            roleId,
            roleName: "string", // Placeholder, replace with actual role name if available
            roleDescription: "string", // Placeholder, replace with actual role description if available
          },
        ],
      },
    };

    try {
      const response = await fetch("/api/profiles", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(requestBody),
      });

      if (!response.ok) throw new Error("Error en la solicitud");

      const data = await response.json();
      console.log("Perfil creado con éxito:", data);
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
        label="Número de Documento" // Add this input for document number
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
      <InputField
        label="Ciudad"
        type="text"
        name="ciudad"
        value={formData.ciudad}
        onChange={handleChange}
      />
      <div className="container-fluid form-buttons-cont">
        <FormButtons onSkip={handleSkip} />
      </div>
    </form>
  );
};

export default Formulario;
