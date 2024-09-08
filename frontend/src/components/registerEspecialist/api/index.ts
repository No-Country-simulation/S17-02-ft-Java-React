import axios from "axios";
import Swal from "sweetalert2";

export const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post("/api/auth/login", {
      username,
      password,
    });
    return {
      token: response.data.token,
      role: response.data.roleId,
    };
  } catch (err) {
    Swal.fire({
      icon: "error",
      title: "Error al Iniciar Sesión",
    });
    throw err;
  }
};
