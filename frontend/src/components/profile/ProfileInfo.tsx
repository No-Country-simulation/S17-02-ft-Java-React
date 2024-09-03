import React, { useContext } from 'react';
import { UserContext } from '../../context/userContext';

const ProfileInfo: React.FC = () => {
    const context = useContext(UserContext);

    if (!context) {
        throw new Error("ProfileInfo must be used within a UserProvider");
    }

    const { user } = context;

    return (
        <div>
            <h2>{user.name}</h2>
            <p>{user.gender} • {user.age} años ({user.birthdate})</p>
            <button>Editar perfil</button>
        </div>
    );
};

export default React.memo(ProfileInfo);
