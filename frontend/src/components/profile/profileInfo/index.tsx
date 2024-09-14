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

  const calculateAge = (birthDate: string) => {
    const today = new Date();
    const birth = new Date(birthDate);
    let age = today.getFullYear() - birth.getFullYear();
    const monthDiff = today.getMonth() - birth.getMonth();

    // Check if the current date is before the birthday in the current year
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
      age--;
    }

    return age;
  };

  const formatBirthDate = (birthDate: string) => {
    const date = new Date(birthDate);
    const day = date.getDate().toString().padStart(2, '0'); // Add leading zero
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-indexed, so we add 1
    const year = date.getFullYear();
    return `${day}-${month}-${year}`;
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;
  if (!profile) return <p>No profile data available</p>;
console.log(profile )
  return (
    <div className="bar-card">
      <div className="img-container">
        {profile.avatarUrl && <img src={profile.avatarUrl} alt="Profile Avatar" />}
      </div>
      <div className="info-container">
        <h2>
          {profile.profileName} {profile.profileLastname}
        </h2>
        
        <p>
          {calculateAge(profile.birth)} años ( {formatBirthDate(profile.birth)})
        </p>
      </div>
        <button className="edit-profile" onClick={handleEditProfile}>Editar perfil</button>
    </div>
  );
};

export default React.memo(ProfileInfo);