import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { AuthProvider } from "../src/components/context/index.tsx";
import App from "./App";
import "../src/css/index.scss";

const rootElement = document.getElementById("root")!;
const root = createRoot(rootElement);

root.render(
  <StrictMode>
    <AuthProvider>
      <App />
    </AuthProvider>
  </StrictMode>
);
