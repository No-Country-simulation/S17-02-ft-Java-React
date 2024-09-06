import { useEffect } from "react";
import { Footer } from "../components/footer";
import NavBar from "../components/navbar";
import NavbarPacientes from "../components/patientsNavbar/index.tsx";
import NavbarEspecialista from "../components/specialistsNavbar/index.tsx";
import { useAuth } from "../context/context.tsx";
import { PropsWithChildren } from "react";

const Layout = ({ children }: PropsWithChildren) => {
  const { roleId } = useAuth();

  useEffect(() => {
    if (roleId) {
      console.log("Role ID guardado en el contexto:", roleId);
    } else {
      console.log("No se encontró Role ID en el contexto.");
    }
  }, [roleId]);

  let NavbarComponent;

  if (roleId === "2326ec2c-4f97-4007-b52c-ba5561b434b9") {
    NavbarComponent = NavbarPacientes;
  } else if (roleId === "9c765b7d-9eec-421b-85c6-6d53bcd002da") {
    NavbarComponent = NavbarEspecialista;
  } else {
    NavbarComponent = NavBar;
  }

  return (
    <>
      <NavbarComponent />
      {children}
      <Footer />
    </>
  );
};

export default Layout;
