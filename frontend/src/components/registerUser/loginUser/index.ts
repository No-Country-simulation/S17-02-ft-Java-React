import axios from "axios";
import { handleError } from "../handleError";

export const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post("/api/auth/login", {
      username,
      password,
    });
    return { token: response.data.token, role: response.data.roleId };
  } catch (err) {
    handleError(err, "Error al Iniciar Sesión");
    throw err;
  }
};
