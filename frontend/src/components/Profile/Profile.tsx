import React, { useEffect, useState } from 'react';
import { getProfile, updateProfile } from '../../services/profileServeceAPI';
import { Profile } from '../interfaces/profile';

const ProfileComponent: React.FC = () => {
  const [profile, setProfile] = useState<Profile | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const userId = '6097656c-e788-45cb-a41f-73d4e031ee60';

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const userProfile = await getProfile(userId);
        setProfile(userProfile);
      } catch (err) {
        setError('Error al cargar el perfil');
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [userId]);

  const handleUpdateProfile = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!profile) return;

    try {
      const updatedProfile = await updateProfile(userId, profile);
      setProfile(updatedProfile);
      alert('Perfil actualizado con éxito');
    } catch (err) {
      setError('Error al actualizar el perfil');
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    if (profile) {
      setProfile({
        ...profile,
        [e.target.name]: e.target.value,
      });
    }
  };

  if (loading) return <div>Cargando...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h1>Perfil del Usuario</h1>
      {profile && (
        <form onSubmit={handleUpdateProfile}>
          <div>
            <label>Nombre:</label>
            <input
              type="text"
              name="profileName"
              value={profile.profileName}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Apellido:</label>
            <input
              type="text"
              name="profileLastname"
              value={profile.profileLastname}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Tipo de Documento:</label>
            <select
              name="documentType"
              value={profile.documentType}
              onChange={handleInputChange}
            >
              <option value="DNI">DNI</option>
              <option value="Pasaporte">Pasaporte</option>
            </select>
          </div>
          <div>
            <label>Número de Documento:</label>
            <input
              type="text"
              name="documentNumber"
              value={profile.documentNumber}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Fecha de Nacimiento:</label>
            <input
              type="date"
              name="birth"
              value={profile.birth}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Dirección:</label>
            <input
              type="text"
              name="address"
              value={profile.address}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Distrito:</label>
            <input
              type="text"
              name="districtName"
              value={profile.district.districtName}
              onChange={(e) =>
                setProfile({
                  ...profile,
                  district: { ...profile.district, districtName: e.target.value },
                })
              }
            />
          </div>
          <div>
            <label>Avatar URL:</label>
            <input
              type="text"
              name="avatarUrl"
              value={profile.avatarUrl}
              onChange={handleInputChange}
            />
          </div>
          <button type="submit">Guardar Cambios</button>
        </form>
      )}
    </div>
  );
};

export default ProfileComponent;
