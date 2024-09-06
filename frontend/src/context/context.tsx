import React, {
  createContext,
  useState,
  useContext,
  ReactNode,
  useEffect,
} from "react";

interface AuthContextType {
  token: string | null;
  role: string | null;
  roleId: string | null;
  setToken: (token: string | null) => void;
  setRole: (role: string | null) => void;
  setRoleId: (roleId: string | null) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [token, setTokenState] = useState<string | null>(
    localStorage.getItem("token") || null
  );
  const [role, setRoleState] = useState<string | null>(
    localStorage.getItem("role") || null
  );
  const [roleId, setRoleIdState] = useState<string | null>(
    localStorage.getItem("roleId") || null
  );

  const setToken = (token: string | null) => {
    localStorage.setItem("token", token || "");
    setTokenState(token);
  };

  const setRole = (role: string | null) => {
    localStorage.setItem("role", role || "");
    setRoleState(role);
  };

  const setRoleId = (roleId: string | null) => {
    localStorage.setItem("roleId", roleId || "");
    setRoleIdState(roleId);
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("roleId");
    setTokenState(null);
    setRoleState(null);
    setRoleIdState(null);
  };

  useEffect(() => {
    setTokenState(localStorage.getItem("token") || null);
    setRoleState(localStorage.getItem("role") || null);
    setRoleIdState(localStorage.getItem("roleId") || null);
  }, []);

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
