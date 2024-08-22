import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./components/home/home.tsx";
import Login from "./components/login/login.tsx";
import "./App.css";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
