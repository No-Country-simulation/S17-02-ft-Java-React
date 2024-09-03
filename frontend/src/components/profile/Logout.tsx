import React from 'react';

const Logout: React.FC = () => {
    return (
        <div>
            <h3>Cerrar sesión</h3>
            <button>Cierre sesión de manera segura</button>
        </div>
    );
};

export default React.memo(Logout);
