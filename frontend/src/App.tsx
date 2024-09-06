import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Login } from "./components/login/index.tsx";
import { PaymentGateway } from "./components/paymentGateway/index.tsx";
import { RegisterEspecialist } from "./components/registerEspecialist/index.tsx";
import { RegisterUser } from "./components/registerUser/index.tsx";
import axios from "axios";
import "../src/css/App.scss";
import Layout from "./layout/layout.tsx";
import Home from "./components/home/index.tsx";
import MercadoPago from "./components/mercadoPago/MercadoPago.tsx";
import ProfileComponent from "./Pages/Profile.tsx";
import ClinicalHistory from "./components/clinicalhistory/ClinicalHistory.tsx";

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
          <Route path="/clinicalhistory" element={<ClinicalHistory />} />
          <Route
            path="/registerespecialist"
            element={<RegisterEspecialist />}
          />
          <Route path="/registeruser" element={<RegisterUser />} />
        </Routes>
      </Layout>
    </Router>
  );
};

export default App;
