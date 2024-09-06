import React, { createContext, useState, useContext, ReactNode } from "react";

interface AuthContextType {
  token: string | null;
  role: string | null;
  roleId: string | null; // Agrega roleId al contexto
  setToken: (token: string | null) => void;
  setRole: (role: string | null) => void;
  setRoleId: (roleId: string | null) => void; // Agrega setRoleId
  logout: () => void; // Agrega logout
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [token, setToken] = useState<string | null>(null);
  const [role, setRole] = useState<string | null>(null);
  const [roleId, setRoleId] = useState<string | null>(null);

  const logout = () => {
    setToken(null);
    setRole(null);
    setRoleId(null);
    // Aquí podrías hacer una llamada a la API para cerrar sesión, si es necesario
  };

  return (
    <AuthContext.Provider
      value={{ token, role, roleId, setToken, setRole, setRoleId, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
