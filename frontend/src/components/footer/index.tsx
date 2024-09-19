
export const Footer = () => {
  return (
    <footer className="footer">
      <div> 
      <a href="/" className="badge">HeyDoc!
        </a>  
      </div>
      <div className="footer-col d-flex flex-column">
        <a href="/"> acerca de <span>HeyDoc!</span></a>
        <a href="/"> Videoconsultas</a>
        <a href="/"> Innovación</a>
        <a href="/"> Ventajas y Beneficios</a>
      </div>
      <div className="footer-col d-flex flex-column">
        <a href="/">Opciones de Servicio</a>
        <a href="/">Contacto</a>
      </div>
      <div className="footer-col d-flex flex-column">
        <a href="mailto:info@heydoc.com">info@heydoc.com</a>
        <i className="bi bi-instagram"> HeyDoc</i>
      </div>
    </footer>
  );
};
 