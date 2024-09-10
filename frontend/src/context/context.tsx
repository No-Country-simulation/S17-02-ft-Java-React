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
  username: string | null;
  password: string | null;
  userId: string | null; // Add userId here
  setToken: (token: string | null) => void;
  setRole: (role: string | null) => void;
  setRoleId: (roleId: string | null) => void;
  setUsername: (username: string | null) => void;
  setPassword: (password: string | null) => void;
  setUserId: (userId: string | null) => void; // Add setUserId here
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
  const [username, setUsernameState] = useState<string | null>(
    localStorage.getItem("username") || null
  );
  const [password, setPasswordState] = useState<string | null>(
    localStorage.getItem("password") || null
  );
  const [userId, setUserIdState] = useState<string | null>( // Add userId state here
    localStorage.getItem("userId") || null
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

  const setUsername = (username: string | null) => {
    localStorage.setItem("username", username || "");
    setUsernameState(username);
  };

  const setPassword = (password: string | null) => {
    localStorage.setItem("password", password || "");
    setPasswordState(password);
  };

  const setUserId = (userId: string | null) => {
    // Add setUserId function here
    localStorage.setItem("userId", userId || "");
    setUserIdState(userId);
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("roleId");
    localStorage.removeItem("username");
    localStorage.removeItem("password");
    localStorage.removeItem("userId"); // Remove userId from localStorage
    setTokenState(null);
    setRoleState(null);
    setRoleIdState(null);
    setUsernameState(null);
    setPasswordState(null);
    setUserIdState(null); // Clear userId state
  };

  useEffect(() => {
    setTokenState(localStorage.getItem("token") || null);
    setRoleState(localStorage.getItem("role") || null);
    setRoleIdState(localStorage.getItem("roleId") || null);
    setUsernameState(localStorage.getItem("username") || null);
    setPasswordState(localStorage.getItem("password") || null);
    setUserIdState(localStorage.getItem("userId") || null); // Initialize userId state
  }, []);

  return (
    <AuthContext.Provider
      value={{
        token,
        role,
        roleId,
        username,
        password,
        userId, // Provide userId
        setToken,
        setRole,
        setRoleId,
        setUsername,
        setPassword,
        setUserId, // Provide setUserId
        logout,
      }}
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
