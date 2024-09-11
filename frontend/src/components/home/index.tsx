import Hero from "./Hero/Hero.tsx";
import Brands from "./Brands/Brands.tsx";
import About from "./About/About.tsx";
import Unete from "./Unete/Unete.tsx";
import Services from "./Services/Services.tsx";
import Contact from "./Contact/Contact.tsx";

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
