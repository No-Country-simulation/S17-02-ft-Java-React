import React, { useContext } from 'react';
import { UserContext } from '../../context/userContext';

const ProfileActions: React.FC = () => {
    const context = useContext(UserContext);

    if (!context) {
        throw new Error("ProfileActions must be used within a UserProvider");
    }

    const { user } = context;

    return (
        <div>
            <h3>Mis turnos</h3>
            {user.appointments.length > 0 ? (
            <ul>
                {user.appointments.map((appointment, index) => (
                <li key={index}>{appointment}</li>
                ))}
            </ul>
            ) : (
            <p>No tienes turnos reservados.</p>
            )}
            <button>Ver más</button>
        </div>
    );
};

export default React.memo(ProfileActions);
