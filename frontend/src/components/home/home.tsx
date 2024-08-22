import React from "react";
import Header from "../header/header.tsx";
import Footer from "../footer/footer.tsx";
import NavBar from "../navbar/navbar.tsx";

const Home: React.FC = () => {
  return (
    <div>
      <Header />
      <NavBar />
      <Footer />
    </div>
  );
};

export default Home;
