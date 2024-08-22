import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./components/home/home.tsx";
import Login from "./components/login/login.tsx";
import VideoCall from "./components/videoCall/videocall.tsx";
import "./App.css";

const App: React.FC = () => {
  const [isVideoCallOpen, setIsVideoCallOpen] = useState(false);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route
          path="/video-call"
          element={
            <VideoCall
              isOpen={isVideoCallOpen}
              onClose={() => setIsVideoCallOpen(false)}
            />
          }
        />
      </Routes>
      <button onClick={() => setIsVideoCallOpen(true)}>Open Video Call</button>
    </Router>
  );
};

export default App;
