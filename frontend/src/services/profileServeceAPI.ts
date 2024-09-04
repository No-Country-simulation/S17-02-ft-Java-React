import axios from 'axios';
import { User } from '../interfaces/profile';

const API_BASE_URL = 'https://telemedicina-v1-0.onrender.com';

export const getProfile = async (id: string): Promise<User> => {
    const response = await axios.get<User>(`${API_BASE_URL}/profile/${id}`);
    return response.data;
};

export const updateProfile = async (id: string, updatedProfile: Partial<User>): Promise<User> => {
    const response = await axios.put<User>(`${API_BASE_URL}/profiles/update/${id}`, updatedProfile);
    return response.data;
};

export const createProfile = async (newProfile: User): Promise<User> => {
    const response = await axios.post<User>(`${API_BASE_URL}/profile`, newProfile);
    return response.data;
};