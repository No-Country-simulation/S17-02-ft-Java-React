import React from 'react';

const Logout: React.FC = () => {
    return (
        <div className='bar-card config'>
            <h3>Cerrar sesión</h3>
            <p>Cierre sesión de manera segura</p>
        </div>
    );
};

export default React.memo(Logout);
