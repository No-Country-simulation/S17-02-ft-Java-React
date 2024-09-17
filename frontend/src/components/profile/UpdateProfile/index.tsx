import React, { useEffect, useState, useCallback, ChangeEvent } from "react";
import axios from "axios";
import { useAuth } from "../../../context/context";
import { useNavigate } from "react-router-dom";
import InputField from "../../profileSesion/inputField";
import { Form } from 'react-bootstrap';
import FileInputWithPreview from "../../profileSesion/fileInput";

// Definir las interfaces para los tipos utilizados
interface City {
  cityId: number;
  cityName: string;
}

interface Department {
  departmentId: number;
  departmentName: string;
  cities: City[];
}

interface User {
  userId: string;
  username: string;
  password: string;
  roles: {
    roleId: string;
    roleName: string;
    roleDescription: string;
  }[];
}

interface ProfileData {
  profileName: string;
  profileLastname: string;
  documentType: string;
  documentNumber: string;
  avatarUrl: string;
  birth: string;
  address: string;
  city: City;
  user: User;
}

const ProfileUpdate: React.FC = () => {
  const { token, roleId } = useAuth(); 
  const [profile, setProfile] = useState<ProfileData | null>(null);
  const [formData, setFormData] = useState<ProfileData>({
    profileName: "",
    profileLastname: "",
    documentType: "",
    documentNumber: "",
    avatarUrl: "",
    birth: "",
    address: "",
    city: {
      cityId: 1,
      cityName: "",
    },
    user: {
      userId: "",
      username: "",
      password: "",
      roles: [],
    },
  });
  const [cities, setCities] = useState<City[]>([]);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [filteredCities, setFilteredCities] = useState<City[]>([]);
  const [selectedDepartment, setSelectedDepartment] = useState<string>("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);
  const navigate = useNavigate();

  const fetchProfileData = useCallback(async () => {
    if (!token) {
      setError("Authentication token is not available");
      setLoading(false);
      return;
    }

    try {
      const response = await axios.get<ProfileData>("/api/profiles/my-profile", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data = response.data;
      setProfile(data);
      setFormData({
        ...data,
        city: data.city || { cityId: 1, cityName: "" },
        user: data.user || {
          userId: "",
          username: "",
          avatarUrl: "",
          password: "",
          roles: [],
        },
      });
    } catch {
      setError("Failed to fetch profile data");
    } finally {
      setLoading(false);
    }
  }, [token]);

  const fetchCitiesAndDepartments = useCallback(async () => {
    if (!token) {
      setError("Authentication token is not available");
      return;
    }

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
    } catch {
      setError("Failed to fetch cities or departments");
    }
  }, [token]);

  useEffect(() => {
    if (!profile) {
      fetchProfileData();
      fetchCitiesAndDepartments();
    }
  }, [profile, fetchProfileData, fetchCitiesAndDepartments]);

  useEffect(() => {
    const selectedDept = departments.find(
      (dept) => dept.departmentName === selectedDepartment
    );
    setFilteredCities(selectedDept ? selectedDept.cities : []);
  }, [selectedDepartment, departments]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleDepartmentChange = (e: ChangeEvent<HTMLSelectElement>) => {
    const departmentName = e.target.value;
    setSelectedDepartment(departmentName);
    setFormData((prev) => ({
      ...prev,
      city: { cityId: 1, cityName: "" },
    }));
  };

  const handleCityChange = (e: ChangeEvent<HTMLSelectElement>) => {
    const cityId = parseInt(e.target.value, 10);
    const selectedCity = filteredCities.find((city) => city.cityId === cityId);
    setFormData((prev) => ({
      ...prev,
      city: selectedCity || { cityId: 1, cityName: "" },
    }));
  };

  const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setFormData((prevData) => ({
        ...prevData,
        avatarUrl: URL.createObjectURL(file),
      }));
    }
  };

  const validateForm = () => {
    if (
      !formData.profileName ||
      !formData.profileLastname ||
      !formData.documentType ||
      !formData.documentNumber ||
      !formData.address ||
      !formData.city.cityId
    ) {
      setError("Please fill out all required fields");
      return false;
    }
    setError(null);
    return true;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!token) {
      setError("Authentication token is not available");
      return;
    }

    if (!validateForm()) {
      return;
    }

    const payload = {
      profileName: formData.profileName || undefined,
      profileLastname: formData.profileLastname || undefined,
      documentType: formData.documentType || undefined,
      documentNumber: formData.documentNumber || undefined,
      avatarUrl: formData.avatarUrl || undefined,
      birth: formData.birth || undefined,
      address: formData.address || undefined,
      city: formData.city.cityId ? { cityId: formData.city.cityId } : undefined,
    };

    axios
      .put("/api/profiles/update-profile", payload, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        setSuccess("Profile updated successfully");
        if (roleId === "9c765b7d-9eec-421b-85c6-6d53bcd002da") {
          navigate("/dashboardEspecialista/perfil");
        } else if (roleId === "2326ec2c-4f97-4007-b52c-ba5561b434b9") {
          navigate("/dashboardCliente/perfil-cliente");
        } else {
          navigate("/dashboard/perfil");
        }
      })
      .catch(() => {
        setError("Failed to update profile data");
      });
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;
  if (!profile) return <p>No profile data available</p>;

  return (
    <form className="form-profile" onSubmit={handleSubmit}>
      <InputField
        label="Nombre"
        type="text"
        name="profileName"
        value={formData.profileName}
        onChange={handleChange}
      />
      <InputField
        label="Apellido"
        type="text"
        name="profileLastname"
        value={formData.profileLastname}
        onChange={handleChange}
      />
      <InputField
        label="Tipo de Documento"
        type="text"
        name="documentType"
        value={formData.documentType}
        onChange={handleChange}
      />
      <InputField
        label="N° de Documento"
        type="text"
        name="documentNumber"
        value={formData.documentNumber}
        onChange={handleChange}
      />
      <InputField
        label="Fecha de Nacimiento"
        type="date"
        name="birth"
        value={formData.birth}
        onChange={handleChange}
      />
      <InputField
        label="Dirección"
        type="text"
        name="address"
        value={formData.address}
        onChange={handleChange}
      />

<div>
  <Form.Group controlId="departmentSelect" className="mb-3">
    <Form.Select
      value={selectedDepartment}
      onChange={handleDepartmentChange}
      aria-placeholder="Selecciona una Provincia"
      className="input-profile"
    >
      <option value="">Selecciona una Provincia</option>
      {departments.map((department) => (
        <option key={department.departmentId} value={department.departmentName}>
          {department.departmentName}
        </option>
      ))}
    </Form.Select>
  </Form.Group>

  <Form.Group controlId="citySelect" className="mb-3">
    <Form.Select
      value={formData.city.cityId}
      onChange={handleCityChange}
      aria-placeholder="Selecciona una Ciudad"
      className="input-profile"
    >
      <option value="">Selecciona una Ciudad</option>
      {filteredCities.map((city) => (
        <option key={city.cityId} value={city.cityId}>
          {city.cityName}
        </option>
      ))}
    </Form.Select>
  </Form.Group>
</div>


<FileInputWithPreview
        onFileChange={handleFileChange}
        imageUrl={profile.avatarUrl}
      />
        <button  className="btn-special" type="submit">Actualizar</button>
      </form>
    
  );
};

export default React.memo(ProfileUpdate);

