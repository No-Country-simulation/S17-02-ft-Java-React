import { useState } from "react";
import { Link } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css";
import { useAuth } from "../../../context/context";
const SidebarClient = () => {
  const {logout} = useAuth();
  const [isCitasOpen, setIsCitasOpen] = useState(false);

  const toggleCitas = () => setIsCitasOpen(!isCitasOpen);

  return (
    <div className="sidebar-container col-auto col-md-3 min-vh-100 d-flex flex-column">
      <Link
        className="text-decoration-none d-flex align-items-center gap-1 text-white mt-3 d-none d-sm-inline"
        to="/dashboardCliente/home-cliente"
      >
        <i className="fs-4 bi bi-speedometer"></i>
        <span className="ms-1 fs-4">HeyDoc!</span>
      </Link>

      <nav className="nav nav-pills flex-column gap-3 mt-4 flex-grow-1">
        <Link
          className="nav-link text-white d-flex align-items-center fs-4 gap-1"
          to="/dashboardCliente/home-cliente"
        >
          <i className="fs-4 bi bi-house"></i>
          <span className="ms-3 d-none d-sm-inline fs-4">Inicio</span>
        </Link>

        <Link
          className="nav-link text-white fs-4 d-flex align-items-center gap-1"
          to="/dashboardCliente/perfil-cliente"
        >
          <i className="fs-4 bi bi-person"></i>
          <span className="ms-3 d-none d-sm-inline fs-4">Mi Perfil</span>
        </Link>

        <Link
          className="nav-link text-white fs-4 d-flex align-items-center gap-1"
          to="/dashboardCliente/lista-especialistas"
        >
          <i className="fs-4 bi bi-person"></i>
          <span className="ms-3 d-none d-sm-inline fs-4">Especialistas</span>
        </Link>

        <div className="nav-item">
          <a
            className="nav-link text-white fs-4 d-flex align-items-center gap-1"
            href="#"
            onClick={toggleCitas}
            aria-expanded={isCitasOpen}
            aria-controls="citas-collapse"
          >
            <i className="fs-4 bi bi-clipboard"></i>
            <span className="ms-3 d-none d-sm-inline fs-4">Mis Citas</span>
            <i
              className={`ms-auto bi ${
                isCitasOpen ? "bi-chevron-up" : "bi-chevron-down"
              }`}
            ></i>
          </a>

          <div
            className={`collapse ${isCitasOpen ? "show" : ""}`}
            id="citas-collapse"
          >
            <nav className="nav flex-column ms-3 mt-2">
              <Link
                className="nav-link text-white fs-5"
                to="/dashboardCliente/nueva-cita-cliente"
              >
                <i className="bi bi-plus-circle"></i>
                <span className="ms-3 d-none d-sm-inline fs-4">Nueva Cita</span>
              </Link>
              <Link
                className="nav-link text-white fs-5"
                to="/dashboardCliente/ver-citas-cliente"
              >
                <i className="bi bi-list-ul"></i>
                <span className="ms-3 d-none d-sm-inline fs-4">Ver Citas</span>
              </Link>
            </nav>
          </div>
        </div>
      </nav>

      <div className="mt-auto mb-3">
        <button
          className="nav-link text-white fs-4 d-flex align-items-center gap-1"
         onClick={()=>logout()}
        >
          <i className="fs-4 bi bi-box-arrow-right"></i>
          <span className="ms-1 fs-4">Cerrar Sesión</span>
        </button>
      </div>
    </div>
  );
};

export default SidebarClient;