import { NavbarAdmin } from "../../componentsAdmin/navbarAdmin"
import { Footer } from "../footer"
import { Navbar } from "../navbar"
import { PropsWithChildren } from "react"
import { useAuth } from "../context/index.tsx";


const Layout = ({children} : PropsWithChildren) => {
  const { role } = useAuth();
  return (
    
    <>
    {role === "admin" ? <NavbarAdmin /> : <Navbar />}
    {children}
    <Footer />
    </>
  )
}
export default Layout;