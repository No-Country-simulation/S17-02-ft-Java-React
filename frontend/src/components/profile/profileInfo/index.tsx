import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../../context/context.tsx";
import { useNavigate } from "react-router-dom";

interface ProfileData {
  profileId: string;
  profileName: string;
  profileLastname: string;
  documentType: string;
  documentNumber: string;
  avatarUrl?: string; // Optional field
  birth: string;
  address: string;
  cityName: string;
  email: string;
  userId: string;
}

const ProfileInfo: React.FC = () => {
  const { token } = useAuth(); // Get token from AuthContext
  const [profile, setProfile] = useState<ProfileData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate(); // Initialize useNavigate

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
        console.log("Response data:", response.data); // Log response data
        setProfile(response.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching profile data:", err); // Log any errors
        setError("Failed to fetch profile data");
        setLoading(false);
      });
  }, [token]);

  const handleEditProfile = () => {
    navigate("/updateprofile"); // Navigate to the update profile page
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;
  if (!profile) return <p>No profile data available</p>;

  return (
    <div>
      <h2>
        {profile.profileName} {profile.profileLastname}
      </h2>
      <p>
        {profile.documentType} • {profile.documentNumber}
      </p>
      <p>Fecha de nacimiento: {profile.birth}</p>
      <p>Direccion: {profile.address}</p>
      <p>Departamento: {profile.cityName}</p>
      <p>Email: {profile.email}</p>
      {profile.avatarUrl ? (
        <img
          src={profile.avatarUrl}
          alt="Foto de Perfil"
          style={{ width: 100, height: 100, borderRadius: "50%" }}
        />
      ) : (
        <img
          src="/path/to/placeholder-image.png"
          alt="Placeholder Avatar"
          style={{ width: 100, height: 100, borderRadius: "50%" }}
        />
      )}
      <button onClick={handleEditProfile}>Editar perfil</button>
    </div>
  );
};

export default React.memo(ProfileInfo);
