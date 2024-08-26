import React from 'react';

const Preferences: React.FC = () => {
    return (
        <section>
            <h2>Preferencias</h2>
            <form>
                <label>
                    Notificaciones por correo:
                    <input type="checkbox" name="emailNotifications" />
                </label>
                <label>
                    Notificaciones por SMS:
                    <input type="checkbox" name="smsNotifications" />
                </label>
                <label>
                    Idioma:
                    <select name="language">
                        <option value="es">Español</option>
                        <option value="en">Inglés</option>
                    </select>
                </label>
                <button type="submit">Guardar</button>
            </form>
        </section>
    );
};

export default Preferences;