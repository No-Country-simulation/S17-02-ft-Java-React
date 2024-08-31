import About from "../components/home/About/About";
import Brands from "../components/home/Brands/Brands";
import Contact from "../components/home/Contact/Contact";
import Hero from "../components/home/Hero/Hero";
import Services from "../components/home/Services/Services";
import Unete from "../components/home/Unete/Unete";
import Header from "../components/navbar";


const Home = () => {
  return (
    <>
      <Header />
      <div>
        <Hero />
        <Brands />
        <About />
        <Unete />
        <Services />
        <Contact />
      </div>
    </>
  );
};

export default Home;
