// UserContext.tsx
import React, { createContext, useState, ReactNode } from 'react';
import { User, UserContextType } from '../interfaces/profile';

export const UserContext = createContext<UserContextType | undefined>(undefined);

interface UserProviderProps {
    children: ReactNode;
}

export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {
    const [user, setUser] = useState<User>({
        name: 'Juan Ignacio Molina',
        age: 26,
        birthdate: '12/06/1998',
        gender: 'Masculino',
        paymentMethod: null,
        appointments: [],
    });

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
};
