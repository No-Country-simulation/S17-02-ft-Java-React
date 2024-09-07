import React, { useState, ChangeEvent, FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/context.tsx"; // Ajusta la ruta según sea necesario

interface FormData {
  nombre: string;
  apellido: string;
  tipoDocumento: string;
  imagen: string | null;
  fechaNacimiento: string;
  direccion: string;
  ciudad: string;
}

const Formulario: React.FC = () => {
  const { token, roleId, username, password } = useAuth(); // Obtén roleId, token, username y password del contexto

  // Muestra los valores en la consola para verificación
  console.log("Token:", token);
  console.log("Username:", username);
  console.log("Password:", password);

  const [formData, setFormData] = useState<FormData>({
    nombre: "",
    apellido: "",
    tipoDocumento: "",
    imagen: null,
    fechaNacimiento: "",
    direccion: "",
    ciudad: "",
  });

  const navigate = useNavigate(); // Hook para la navegación

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
    if (!token || !roleId || !username || !password) {
      console.error("Token, roleId, username, or password is missing");
      return;
    }

    // Construye el cuerpo de la solicitud según el formato proporcionado
    const requestBody = {
      profileName: formData.nombre,
      profileLastname: formData.apellido,
      documentType: formData.tipoDocumento,
      documentNumber: "", // Puedes agregar un campo en el formulario si es necesario
      avatarUrl: formData.imagen || "", // Utiliza la URL de la imagen o una cadena vacía
      birth: formData.fechaNacimiento,
      address: formData.direccion,
      city: {
        cityId: 0, // Ajusta según sea necesario
        cityName: formData.ciudad,
      },
      user: {
        userId: roleId,
        username: username,
        password: password,
        roles: [
          {
            roleId,
            roleName: "", // Puedes agregar un campo en el formulario si es necesario
            roleDescription: "", // Puedes agregar un campo en el formulario si es necesario
          },
        ],
      },
    };

    try {
      const response = await fetch("/api/profile", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`, // Incluye el token en el encabezado
        },
        body: JSON.stringify(requestBody),
      });

      if (!response.ok) {
        throw new Error("Error en la solicitud");
      }

      const data = await response.json();
      console.log("Perfil creado con éxito:", data); // Muestra la respuesta del servidor en la consola
      navigate("/"); // Redirige a la ruta principal después de enviar el formulario
    } catch (error) {
      console.error("Error al enviar la solicitud:", error);
    }
  };

  const handleSkip = () => {
    navigate("/"); // Redirige a la ruta principal
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Nombre:</label>
        <input
          type="text"
          name="nombre"
          value={formData.nombre}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Apellido:</label>
        <input
          type="text"
          name="apellido"
          value={formData.apellido}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Tipo de Documento:</label>
        <input
          type="text"
          name="tipoDocumento"
          value={formData.tipoDocumento}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Imagen:</label>
        <input type="file" accept="image/*" onChange={handleFileChange} />
        {formData.imagen && (
          <img
            src={formData.imagen}
            alt="Vista previa"
            style={{ width: "100px", height: "100px" }}
          />
        )}
      </div>
      <div>
        <label>Fecha de Nacimiento:</label>
        <input
          type="date"
          name="fechaNacimiento"
          value={formData.fechaNacimiento}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Dirección:</label>
        <input
          type="text"
          name="direccion"
          value={formData.direccion}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Ciudad:</label>
        <input
          type="text"
          name="ciudad"
          value={formData.ciudad}
          onChange={handleChange}
        />
      </div>
      <button type="submit">Guardar</button>
      <button type="button" onClick={handleSkip}>
        Omitir
      </button>
    </form>
  );
};

export default Formulario;
