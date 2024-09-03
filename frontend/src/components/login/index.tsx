import { useState, FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useAuth } from "../context.tsx";
import Modal from "react-modal";

Modal.setAppElement("#root");

export const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [modalIsOpen, setModalIsOpen] = useState(true);
  const { setToken, setRole } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();

    try {
      const response = await axios.post("/api/auth/login", {
        username,
        password,
      });

      console.log("Login successful:", response.data);

      const { token, userResponseDTO } = response.data;
      setToken(token);

      const role = userResponseDTO.roles[0]?.roleId;
      let roleName = "unknown";

      if (role === 1) {
        roleName = "user";
      } else if (role === 2) {
        roleName = "admin";
      }

      setRole(roleName);
      console.log("User role:", roleName);

      navigate("/");
      closeModal();
    } catch (err) {
      console.error("Login failed:", err);
      setError("Login failed. Please check your credentials and try again.");
    }
  };

  const closeModal = () => {
    setModalIsOpen(false);
    navigate("/");
  };

  return (
    <Modal
      isOpen={modalIsOpen}
      onRequestClose={closeModal}
      contentLabel="Login Modal"
    >
      <button onClick={closeModal} style={{ marginTop: "10px" }}>
        Cerrar
      </button>
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="username">Nombre de usuario:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Contraseña:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Iniciar sesión</button>
      </form>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </Modal>
  );
};
