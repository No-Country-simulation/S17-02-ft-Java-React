import axios from "axios";
import Swal from "sweetalert2";

interface Role {
  roleId: string;
  roleName: string;
  roleDescription: string;
}

interface UserResponseDTO {
  userId: string;
  username: string;
  password: string;
  roles: Role[];
}

interface LoginResponse {
  userResponseDTO: UserResponseDTO;
  token: string;
}

export const loginUser = async (
  username: string,
  password: string
): Promise<LoginResponse> => {
  try {
    const response = await axios.post<LoginResponse>("/api/auth/login", {
      username,
      password,
    });

    return {
      userResponseDTO: response.data.userResponseDTO,
      token: response.data.token,
    };
  } catch (err) {
    Swal.fire({
      icon: "error",
      title: "Error al Iniciar Sesión",
    });
    throw err;
  }
};
