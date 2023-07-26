import { createContext, useContext, useEffect, useState } from "react";
import { AuthService } from "../services";

export const useAuth = () => {
  return useContext(AuthContext);
};

export type LoginAuthParams = {
  username: string;
  password: string;
};

export type RefreshTokenParams = {
  refreshToken: string;
};

type AuthProviderParams = {
  children: JSX.Element;
};

type AuthValueParams = {
  userSession: UserSessionParams | null;
  onLogin: (user: LoginAuthParams) => void;
  onLogout: () => void;
};

type UserSessionParams = {
  id: number;
  refreshToken: string;
  username: string;
  expiresIn: string;
};

export const AuthContext = createContext<AuthValueParams>(
  {} as AuthValueParams
);

const service = new AuthService();

export const AuthProvider = ({ children }: AuthProviderParams) => {
  const [userSession, setUserSession] = useState<UserSessionParams | null>(
    null
  );

  const handleLogin = ({ username, password }: LoginAuthParams) => {
    service
      .login({ username, password })
      .then(({ data }) => {
        localStorage.setItem("user", JSON.stringify(data));
        setUserSession(data);
      })
      .catch((err) => {
        console.error(err);
        if ([403]) alert("Usuário não encontrado");
      });
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUserSession(null);
  };

  const value: AuthValueParams = {
    userSession,
    onLogin: handleLogin,
    onLogout: handleLogout,
  };

  // ao atualizar pagina a autenticacao é modificada
  useEffect(() => {
    const userToken: UserSessionParams = JSON.parse(
      localStorage.getItem("user") || "{}"
    );

    if (userToken) {
      service
        .refreshToken({ refreshToken: userToken.refreshToken })
        .then(({ data }) => {
          console.log(data);
        })
        .catch((err) => {
          localStorage.removeItem("user");
        });
    }
  }, []);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
