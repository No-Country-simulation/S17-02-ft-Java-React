import axios from 'axios';
import { Profile } from '../components/interfaces/profile';

const API_BASE_URL = 'https://telemedicina-v1-0.onrender.com';

export const getProfile = async (id: string): Promise<Profile> => {
    const response = await axios.get<Profile>(`${API_BASE_URL}/profile/${id}`);
    return response.data;
};

export const updateProfile = async (id: string, updatedProfile: Partial<Profile>): Promise<Profile> => {
    const response = await axios.put<Profile>(`${API_BASE_URL}/profiles/update/${id}`, updateProfile);
    return response.data;
};

export const createProfile = async (newProfile: Profile): Promise<Profile> => {
    const response = await axios.post<Profile>(`${API_BASE_URL}/profile`, newProfile);
    return response.data;
};