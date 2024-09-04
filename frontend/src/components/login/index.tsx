import { useState, FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();

    try {
      const response = await axios.post("/api/auth/login", {
        username,
        password,
      });

      console.log("Login successful:", response.data);

      // If your login API returns a token or some other data, you can use it here.
      // Example:
      // const { token } = response.data;
      // localStorage.setItem('token', token);

      navigate("/"); // Navigate to the home page or any other page after successful login
    } catch (err) {
      console.error("Login failed:", err);
      setError("Login failed. Please check your credentials and try again.");
    }
  };

  return (
    <div style={{ padding: "20px", maxWidth: "400px", margin: "auto" }}>
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
    </div>
  );
};
