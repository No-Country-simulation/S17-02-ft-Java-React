import { NavbarAdmin } from "../../componentsAdmin/navbarAdmin"
import { Footer } from "../footer"
import { PropsWithChildren } from "react"
import NavBar from "../navbar/index.tsx";
import { useAuth } from "../context/index.tsx";


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