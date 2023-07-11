import styled from "styled-components";
import { useAuth } from "../auth";
import { Navigate } from "react-router-dom";

export const Home = () => {
  const { token, onLogout } = useAuth();

  if (!token) return <Navigate to="/login" replace />;

  return (
    <HomeContainer>
      Autenticado
      <button onClick={onLogout}>Deslogar</button>
    </HomeContainer>
  );
};

const HomeContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
`;
