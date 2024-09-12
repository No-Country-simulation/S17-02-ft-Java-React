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
  const { token } = useAuth(); // Get token from AuthContext
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
      cityId: 0,
      cityName: "",
    },
    user: {
      userId: "",
      username: "",
      password: "",
      roles: [
        {
          roleId: "",
          roleName: "",
          roleDescription: "",
        },
      ],
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
        setProfile(response.data);
        setFormData(response.data); // Initialize formData with profile data
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

  const handleNestedChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    section: keyof ProfileData
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [section]: {
        ...(prev[section] as any), // Ensure prev[section] is treated as an object
        [name]: value,
      },
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!token) {
      setError("Authentication token is not available");
      return;
    }

    axios
      .put("/api/profiles/update-profile", formData, {
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
              value={formData.city?.cityId || ""} // Ensure cityId is defined
              onChange={(e) => handleNestedChange(e, "city")}
            />
          </label>
        </div>
        <div>
          <label>
            City Name:
            <input
              type="text"
              name="cityName"
              value={formData.city?.cityName || ""} // Ensure cityName is defined
              onChange={(e) => handleNestedChange(e, "city")}
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
        <div>
          <label>
            Username:
            <input
              type="text"
              name="username"
              value={formData.user?.username || ""} // Ensure username is defined
              onChange={(e) => handleNestedChange(e, "user")}
            />
          </label>
        </div>
        <div>
          <label>
            Password:
            <input
              type="password"
              name="password"
              value={formData.user?.password || ""} // Ensure password is defined
              onChange={(e) => handleNestedChange(e, "user")}
            />
          </label>
        </div>
        {/* Add additional user fields and role handling if necessary */}
        <button type="submit">Update Profile</button>
      </form>
    </div>
  );
};

export default React.memo(ProfileUpdate);
