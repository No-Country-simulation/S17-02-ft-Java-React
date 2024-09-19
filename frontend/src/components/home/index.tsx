import React from "react";
import Hero from "./Hero/index.tsx";
import Brands from "./Brands/index.tsx";
import About from "./About/index.tsx";
import Unete from "./Unete/index.tsx";
import Services from "./Services/index.tsx";
import Contact from "./Contact/index.tsx";

const Home: React.FC = () => {
  return (
    <div>
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
