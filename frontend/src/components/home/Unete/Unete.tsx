import { Link } from "react-router-dom";

const Unete = () => {
  return (
    <section className="unete-container">
      <div className="unete-title">
        <h3>
          Pasa consulta por llamada o videollamada
        </h3>
        <button type="button" className="btn-unete">
          Comenzar ahora
        <Link to="/register" />
        </button>
      </div >
      <div className="legend">
      Gestiona todas tus comunicaciones en un solo lugar. Realiza llamadas de voz o videollamadas con tus pacientes de manera privada y segura, utilizando conexión 3G/Wifi. No es necesario compartir tu número de teléfono, solo conéctate y comienza a hablar cara a cara con un solo clic.
      </div>
    </section>
  );
};

export default Unete;
