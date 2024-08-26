import React from 'react';
import PersonalInfo from './PersonalInfo';
import ClinicalHistory from './ClinicalHistory';
import Appointments from './Appointments';
import Reservations from './Reservations';
import Payments from './Payments';
import Preferences from './Preferences';
import { Link } from "react-router-dom";

const Profile: React.FC = () => {
    return (
    <div>
        <h1>Perfil del Usuario</h1>
        <PersonalInfo />
        <ClinicalHistory />
        <Appointments />
        <Reservations />
        <Payments />
        <Preferences />
        <li>
            <Link to="/">Volver al Inicio</Link>
        </li>
    </div>
    );
};

export default Profile;