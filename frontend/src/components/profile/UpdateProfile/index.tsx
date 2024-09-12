import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../../context/context.tsx";

interface City {
  cityId: number;
  cityName: string;
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
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  useEffect(() => {
    if (!token) {
      setError("Authentication token is not available");
      setLoading(false);
      return;
    }

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
  }, [token]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleCityChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      city: {
        ...prev.city,
        [name]: name === "cityId" ? Number(value) : value,
      },
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
      <h2>Update Profile</h2>
      {success && <p>{success}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            First Name:
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
            Last Name:
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
            Document Type:
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
            Document Number:
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
            Birth Date:
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
            Address:
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
            City ID:
            <input
              type="number"
              name="cityId"
              value={formData.city.cityId || ""}
              onChange={handleCityChange}
            />
          </label>
        </div>
        <div>
          <label>
            City Name:
            <input
              type="text"
              name="cityName"
              value={formData.city.cityName || ""}
              onChange={handleCityChange}
            />
          </label>
        </div>
        <div>
          <label>
            Avatar URL:
            <input
              type="text"
              name="avatarUrl"
              value={formData.avatarUrl}
              onChange={handleChange}
            />
          </label>
        </div>
        <button type="submit">Update Profile</button>
      </form>
    </div>
  );
};

export default React.memo(ProfileUpdate);
