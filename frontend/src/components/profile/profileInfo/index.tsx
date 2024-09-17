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
    const cachedProfile = localStorage.getItem("profileData");
  
    if (cachedProfile) {
      setProfile(JSON.parse(cachedProfile));
      setLoading(false);
      return;
    }
  
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
        localStorage.setItem("profileData", JSON.stringify(response.data)); // Cache profile data
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

  const calculateAge = (birthDateString: string): number => {
    const today = new Date();
    const birthDate = new Date(birthDateString);
    let age = today.getFullYear() - birthDate.getFullYear();

    const monthDifference = today.getMonth() - birthDate.getMonth();

    // Check if the current date is before the birthday in the current year
    if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birthDate.getDate())) {
      age -= 1;
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

  if (loading) {
    return (
      <div className="bar-card">
        <p>Loading...</p>
      </div>
    );
  }
  if (error) {
    return (
      <div className="bar-card">
        <p>{error}</p>
      </div>
    );
  }
  if (!profile) {
    return (
      <div className="bar-card">
        <p>No profile data available</p>
      </div>
    );
  }
  console.log("imagen" + profile.avatarUrl)
  return (
    <div className="bar-card">
      <div className="img-container">
        {profile.avatarUrl ? <img src={profile.avatarUrl} alt="Profile Avatar" /> : <i className="bi bi-person-circle"></i>}
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
