import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Login } from "./components/login/index.tsx";
import { PaymentGateway } from "./components/paymentGateway/index.tsx";
import { RegisterEspecialist } from "./components/registerEspecialist/index.tsx";
import { RegisterUser } from "./components/registerUser/index.tsx";
import ProfileSesion from "./components/profileSesion/index.tsx";
import axios from "axios";
import "../src/css/App.scss";
import Layout from "./layout/layout.tsx";
import Home from "./components/home/index.tsx";
import MercadoPago from "./components/mercadoPago/MercadoPago.tsx";
import ProfileComponent from "./Pages/Profile.tsx";
import Booking from "./Pages/Booking.tsx";
import DashboardLayout from "./layout/DashboardLayout";
import DashboardHome from "./components/DashboardSpecialist/DashboardHome";
import NuevaCita from "./components/DashboardSpecialist/NuevaCita.tsx";
import ListaCitas from "./components/DashboardSpecialist/ListaCitas.tsx";
import Profile from "./Pages/Profile.tsx";
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

          {/* Rutas del dashboard */} 
      <Route path="/dashboard" element={<DashboardLayout />}>
        <Route index element={<DashboardHome />} />
        <Route path="home" element={<DashboardHome />} />
        <Route path="perfil" element={<Profile />} />
        <Route path="nueva-cita" element={<NuevaCita />} />
        <Route path="ver-citas" element={<ListaCitas />} />
      </Route>

          <Route path="/registerespecialist" element={<RegisterEspecialist />} />
          <Route path="/registeruser" element={<RegisterUser />} />
          <Route path="/profilesesion" element={<ProfileSesion />} />
        </Routes>
      </Layout>
    </Router>
  );
};

export default App;
