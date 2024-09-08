import axios from "axios";
import Swal from "sweetalert2";

export const handleError = (error: any, defaultMessage: string) => {
  let errorTitle = defaultMessage;
  if (axios.isAxiosError(error)) {
    errorTitle = "Error al Registrar";
  }
  Swal.fire({
    icon: "error",
    title: errorTitle,
    text: "",
  });
};
