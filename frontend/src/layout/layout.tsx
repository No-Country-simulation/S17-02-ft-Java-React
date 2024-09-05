import { NavbarAdmin } from "../componentsAdmin/navbarAdmin/index.tsx";
import { Footer } from "../components/footer"
import { PropsWithChildren } from "react"
import NavBar from "../components/navbar";
import { useAuth } from "../context/context.tsx";


const Layout = ({children} : PropsWithChildren) => {
  const { role } = useAuth();
  return (
    
    <>
    {role === "admin" ? <NavbarAdmin /> : <NavBar />}
    {children}
    <Footer />
    </>
  )
}
export default Layout;