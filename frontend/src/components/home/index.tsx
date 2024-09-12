import React from "react";
import { useLocation } from "react-router-dom";
import Hero from "./Hero/index.tsx";
import Brands from "./Brands/index.tsx";
import About from "./About/index.tsx";
import Unete from "./Unete/index.tsx";
import Services from "./Services/index.tsx";
import Contact from "./Contact/index.tsx";
import Sidebar from "../DashboardSpecialist/SideBar/index.tsx";
import { useAuth } from "../../context/context.tsx";

const Home: React.FC = () => {
  const { roleId } = useAuth();
  const location = useLocation();

  // Check if the current path is '/dashboard/home'
  const isDashboardHome = location.pathname === "/dashboard/home";

  return (
    <div>
      {!isDashboardHome &&
        roleId === "9c765b7d-9eec-421b-85c6-6d53bcd002da" && <Sidebar />}
      <Hero />
      <Brands />
      <About />
      <Unete />
      <Services />
      <Contact />
    </div>
  );
};

export default Home;
