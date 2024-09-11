import React from "react";
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

  return (
    <div>
      {roleId === "9c765b7d-9eec-421b-85c6-6d53bcd002da" && <Sidebar />}
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
