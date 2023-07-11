import { createContext, useContext, useEffect, useState } from "react";

export const useAuth = () => {
  return useContext(AuthContext);
};

export type LoginAuthParams = {
  username: string;
  password: string;
};

type AuthProviderParams = {
  children: JSX.Element;
};

type AuthValueParams = {
  token: string | null;
  onLogin: (user: LoginAuthParams) => void;
  onLogout: () => void;
};

export const AuthContext = createContext<AuthValueParams>(
  {} as AuthValueParams
);

export const AuthProvider = ({ children }: AuthProviderParams) => {
  const [token, setToken] = useState<string | null>(null);

  const handleLogin = ({ username, password }: LoginAuthParams) => {
    console.log("tentando login com", username, "and", password);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setToken(null);
  };

  const value: AuthValueParams = {
    token,
    onLogin: handleLogin,
    onLogout: handleLogout,
  };

  useEffect(() => {
    const userToken = localStorage.getItem("token");
    if (userToken) {
      setToken(userToken);
    }
  }, []);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
