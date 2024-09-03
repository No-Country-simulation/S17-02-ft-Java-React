import { NavbarAdmin } from "../componentsAdmin/navbarAdmin/index.tsx"
import { Footer } from "../components/footer/index.tsx"
import { Navbar } from "../components/navbar/index.tsx"
import { PropsWithChildren } from "react"
import { useAuth } from "../context/context.tsx";


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