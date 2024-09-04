import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { AuthProvider } from "./context/context.tsx";
import { UserProvider } from "./context/userContext.tsx";
import App from "./App";
import "../src/css/index.scss";

const rootElement = document.getElementById("root")!;
const root = createRoot(rootElement);

root.render(
  <StrictMode>
    <AuthProvider>
      <UserProvider>
      <App />
      </UserProvider>
    </AuthProvider>
  </StrictMode>
);
