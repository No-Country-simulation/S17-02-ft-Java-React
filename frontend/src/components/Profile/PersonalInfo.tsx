import React from 'react';

const PersonalInfo: React.FC = () => {
    return (
    <section>
        <h2>Información Personal</h2>
        <form>
            <label>
                Nombre:
                <input type="text" name="name" />
            </label>
            <label>
                Correo Electrónico:
                <input type="email" name="email" />
            </label>
            <label>
                Número de Teléfono:
                <input type="tel" name="phone" />
            </label>
            <label>
                Dirección:
                <input type="text" name="address" />
            </label>
            <label>
                Fecha de Nacimiento:
                <input type="date" name="dob" />
            </label>
            <label>
                Género:
                <select name="gender">
                    <option value="male">Masculino</option>
                    <option value="female">Femenino</option>
                </select>
            </label>
            <button type="submit">Guardar</button>
        </form>
    </section>
    );
};

export default PersonalInfo;