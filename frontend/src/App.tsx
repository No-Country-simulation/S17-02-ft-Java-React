import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./components/home/index.tsx";
import Login from "./components/login/index.tsx";
import VideoCall from "./components/videoCall/index.tsx";
import "./App.css";
import Profile from "./components/Profile/Profile.tsx";

const App: React.FC = () => {
  const [isVideoCallOpen, setIsVideoCallOpen] = useState(false);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/profile" element={<Profile />} />
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
