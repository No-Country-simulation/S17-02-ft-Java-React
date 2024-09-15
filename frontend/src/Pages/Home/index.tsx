import About from "../../components/home/About";
import Brands from "../../components/home/Brands";
import Contact from "../../components/home/Contact";
import Hero from "../../components/home/Hero";
import Services from "../../components/home/Services";
import Unete from "../../components/home/Unete";
import Header from "../../components/navbar";

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
