import { useState } from "react";
import { LoginAuthParams, useAuth } from "../auth";
import { Navigate } from "react-router-dom";
import styled from "styled-components";

export const Login = () => {
  const [formData, setFormData] = useState<LoginAuthParams>({
    username: "",
    password: "",
  });

  const { token, onLogin } = useAuth();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const tryLogin = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onLogin(formData);
  };

  if (token) return <Navigate to="/home" replace />;

  return (
    <LoginContainer>
      <LoginForm onSubmit={tryLogin}>
        <input
          name="username"
          value={formData.username}
          type="text"
          placeholder="Usuário"
          onChange={handleInputChange}
        />
        <input
          name="password"
          value={formData.password}
          type="password"
          placeholder="Senha"
          onChange={handleInputChange}
        />
        <button>Entrar</button>
      </LoginForm>
    </LoginContainer>
  );
};

const LoginContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
`;
