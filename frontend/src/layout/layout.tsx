import { Footer } from "../components/footer";
import { PropsWithChildren } from "react";
import NavBar from "../components/navbar";

const Layout = ({ children }: PropsWithChildren) => {
  return (
    <>
      <NavBar />
      {children}
      <Footer />
    </>
  );
};

export default Layout;
