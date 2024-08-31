import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./components/home/index.tsx";
import { Login } from "./components/login/index.tsx";
import VideoCall from "./components/videoCall/index.tsx";
import { PaymentGateway } from "./components/paymentGateway/index.tsx";
import { RegisterEspecialist } from "./components/registerEspecialist/index.tsx";
import "../src/css/App.scss";

export const App: React.FC = () => {
  const [isVideoCallOpen, setIsVideoCallOpen] = useState(false);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/payment" element={<PaymentGateway />} />
        {/* <Route
          path="/video-call"
          element={
            <VideoCall
              isOpen={isVideoCallOpen}
              onClose={() => setIsVideoCallOpen(false)}
            />
          }
        /> */}
        <Route path="/registerespecialist" element={<RegisterEspecialist />} />
      </Routes>
    </Router>
  );
};

export default App;
