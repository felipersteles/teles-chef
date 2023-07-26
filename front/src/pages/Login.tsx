import { useState } from "react";
import { LoginAuthParams, useAuth } from "../auth";
import { Navigate } from "react-router-dom";
import styled from "styled-components";

export const Login = () => {
  const [formData, setFormData] = useState<LoginAuthParams>({
    username: "",
    password: "",
  });

  const { userSession, onLogin } = useAuth();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const tryLogin = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onLogin(formData);
  };

  if (userSession) return <Navigate to="/home" replace />;

  return (
    <LoginContainer>
      <LoginForm onSubmit={tryLogin}>
        <LoginFormInput
          name="username"
          value={formData.username}
          type="text"
          placeholder="Usuário"
          onChange={handleInputChange}
        />
        <LoginFormInput
          name="password"
          value={formData.password}
          type="password"
          placeholder="Senha"
          onChange={handleInputChange}
        />
        <LoginFormSubmit type="submit">Entrar</LoginFormSubmit>
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
  background-color: white;
  gap: 10px;
  border-radius: 7px;
  padding: 20px 15px;
`;

const LoginFormInput = styled.input`
  border: 1px solid gray;
  border-radius: 3px;
  height: 40px;
  width: 300px;
`;

const LoginFormSubmit = styled.button`
  border: none;
  border-radius: 3px;
  height: 40px;
  cursor: pointer;

  &:hover {
    background-color: #6750a4;
  }
`;
