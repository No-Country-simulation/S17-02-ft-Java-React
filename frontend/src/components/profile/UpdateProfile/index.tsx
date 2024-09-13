import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../../context/context.tsx";
import { useNavigate } from "react-router-dom";

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
  const { token } = useAuth();
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
      cityId: 1, // Default value for cityId
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

  useEffect(() => {
    if (!token) {
      setError("Authentication token is not available");
      setLoading(false);
      return;
    }

    // Fetch profile data
    axios
      .get("/api/profiles/my-profile", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const data = response.data;
        setProfile(data);
        setFormData({
          ...data,
          city: data.city || { cityId: 1, cityName: "" },
          user: data.user || {
            userId: "",
            username: "",
            password: "",
            roles: [],
          },
        });
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to fetch profile data");
        setLoading(false);
      });

    // Fetch departments and cities
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
        setError("Failed to fetch cities or departments");
      }
    };

    fetchCitiesAndDepartments();
  }, [token]);

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

  const handleDepartmentChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const departmentName = e.target.value;
    setSelectedDepartment(departmentName);
    setFormData((prev) => ({
      ...prev,
      city: { cityId: 1, cityName: "" },
    }));
  };

  const handleCityChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const cityId = Number(e.target.value);
    const selectedCity = cities.find((city) => city.cityId === cityId);
    setFormData((prev) => ({
      ...prev,
      city: selectedCity || { cityId: 1, cityName: "" },
    }));
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

    // Prepare payload ensuring that optional fields can be empty
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
      .then((response) => {
        setSuccess("Profile updated successfully");
        setProfile(response.data);
        navigate("/dashboard/perfil"); // Redirect to /dashboard/perfil
      })
      .catch(() => {
        setError("Failed to update profile data");
      });
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;
  if (!profile) return <p>No profile data available</p>;

  return (
    <div>
      <h2>Actualizar Perfil</h2>
      {success && <p>{success}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Nombre:
            <input
              type="text"
              name="profileName"
              value={formData.profileName}
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <label>
            Apellido:
            <input
              type="text"
              name="profileLastname"
              value={formData.profileLastname}
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <label>
            Tipo de Documento:
            <input
              type="text"
              name="documentType"
              value={formData.documentType}
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <label>
            N° de Documento:
            <input
              type="text"
              name="documentNumber"
              value={formData.documentNumber}
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <label>
            Fecha de Nacimiento:
            <input
              type="date"
              name="birth"
              value={formData.birth}
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <label>
            Direccion:
            <input
              type="text"
              name="address"
              value={formData.address}
              onChange={handleChange}
            />
          </label>
        </div>
        <div>
          <label>
            Provincia:
            <select
              name="department"
              value={selectedDepartment}
              onChange={handleDepartmentChange}
            >
              <option value="">Selecciona una Provincia</option>
              {departments.map((department) => (
                <option
                  key={department.departmentId}
                  value={department.departmentName}
                >
                  {department.departmentName}
                </option>
              ))}
            </select>
          </label>
        </div>
        <div>
          <label>
            Ciudad:
            <select
              name="cityId"
              value={formData.city.cityId}
              onChange={handleCityChange}
            >
              <option value="">Selecciona una ciudad</option>
              {filteredCities.map((city) => (
                <option key={city.cityId} value={city.cityId}>
                  {city.cityName}
                </option>
              ))}
            </select>
          </label>
        </div>
        <div>
          <label>
            Imagen de Perfil:
            <input
              type="text"
              name="avatarUrl"
              value={formData.avatarUrl}
              onChange={handleChange}
            />
          </label>
        </div>
        <button type="submit">Actualizar</button>
      </form>
    </div>
  );
};

export default React.memo(ProfileUpdate);
