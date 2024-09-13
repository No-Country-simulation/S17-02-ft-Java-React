import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Login } from "./components/login/index.tsx";
import { PaymentGateway } from "./components/paymentGateway/index.tsx";
import { RegisterEspecialist } from "./components/registerEspecialist/index.tsx";
import { RegisterUser } from "./components/registerUser/index.tsx";
import ProfileSesion from "./components/profileSesion/index.tsx";
import axios from "axios";
import "../src/css/App.scss";
import Layout from "./layout/index.tsx";
import Home from "./components/home/index.tsx";
import MercadoPago from "./components/mercadoPago/index.tsx";
import ProfileComponent from "./Pages/Profile/index.tsx";
import Booking from "./Pages/Booking/index.tsx";
import DashboardLayout from "./layout/DashboardLayout/index.tsx";
import DashboardHome from "./components/DashboardSpecialist/Dashboard/index.tsx";
import NuevaCita from "./components/DashboardSpecialist/NuevaCita/index.tsx";
import ListaCitas from "./components/DashboardSpecialist/ListaCitas/index.tsx";
import Profile from "./Pages/Profile/index.tsx";
import UpdateProfile from "./components/profile/UpdateProfile/index.tsx";
import HomeCliente from "./components/DashboardClient/HomeClient/index.tsx";
import ListaEspecialist from "./components/DashboardClient/ListaSpecialist/index.tsx";
import ListaCitasCliente from "./components/DashboardClient/ListaCitasCliente/index.tsx";
// import DashboardProfile from "./components/DashboardSpecialist/Profile";
// import NuevaCita from "./components/DashboardSpecialist/NuevaCita";
// import VerCitas from "./components/DashboardSpecialist/VerCitas";

axios.defaults.baseURL = "https://telemedicina-v1-0.onrender.com";

const App: React.FC = () => {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/payment" element={<PaymentGateway />} />
          <Route path="/prueba-mp" element={<MercadoPago />} />
          <Route path="/profile" element={<ProfileComponent />} />
          <Route path="/reservas" element={<Booking />} />

          {/* Rutas del dashboard especialista */}
          <Route path="/dashboardEspecialista" element={<DashboardLayout />}>
            <Route index element={<DashboardHome />} />
            <Route path="home" element={<DashboardHome />} />
            <Route path="perfil" element={<Profile />} />
            <Route path="nueva-cita" element={<NuevaCita />} />
            <Route path="ver-citas" element={<ListaCitas />} />
          </Route>
          <Route path="/updateprofile" element={<UpdateProfile />} />

          {/* Rutas del dashboard cliente */}
          <Route path="/dashboardCliente" element={<DashboardLayout />}>
            <Route index element={<DashboardHome />} />
            <Route path="home-cliente" element={<HomeCliente />} />
            <Route path="perfil-cliente" element={<Profile />} />
            <Route path="nueva-cita-cliente" element={<Booking />} />
            <Route path="ver-citas-cliente" element={<ListaCitasCliente />} />
            <Route path="lista-especialistas" element={<ListaEspecialist />} />
          </Route>
          <Route path="/updateprofile" element={<UpdateProfile />} />


          <Route
            path="/registerespecialist"
            element={<RegisterEspecialist />}
          />
          <Route path="/registeruser" element={<RegisterUser />} />
          <Route path="/profilesesion" element={<ProfileSesion />} />
        </Routes>
      </Layout>
    </Router>
  );
};

export default App;
