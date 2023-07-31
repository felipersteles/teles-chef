import styled from "styled-components";
import { useAuth } from "../auth";
import { Navigate } from "react-router-dom";
import { ReceitaService } from "../services";
import { useCallback, useEffect, useState } from "react";

type ReceitaDTO = {
  id: number;
  nome: string;
  desc: string;
  porcoes: number;
  modoPreparo: string;
  chef: string;
};

const receitaService = new ReceitaService();

export const Home = () => {
  const { userSession, onLogout } = useAuth();

  const [receitas, setReceitas] = useState<ReceitaDTO[]>();
  const [resp, setResp] = useState<boolean>(false);

  const getReceitasFromAPI = useCallback(() => {
    if (!userSession) return;

    receitaService
      .getAllReceitas()
      .then(({ data }) => {
        setReceitas(data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, [userSession]);

  const handleClick = () => {
    receitaService
      .getChef()
      .then(() => {
        setResp(true);
      })
      .catch(() => {
        setResp(false);
      });
  };

  useEffect(getReceitasFromAPI, [getReceitasFromAPI]);

  if (!userSession) return <Navigate to="/login" replace />;

  return (
    <HomeContainer>
      <Container>
        <Card>
          <button onClick={handleClick}>Sou um chef?</button>
          <span>{resp ? "sim" : "nao"}</span>
        </Card>
        <Card>
          <CardContent>
            <h3>Lista de receitas</h3>
            <ul>
              {receitas &&
                receitas.map((rec, key) => (
                  <Space key={key}>
                    <span>{rec.nome}</span>
                    <span>{rec.desc}</span>
                    <span>{rec.porcoes}</span>
                    <span>{rec.modoPreparo}</span>
                    <span>{rec.chef}</span>
                  </Space>
                ))}
            </ul>
          </CardContent>
        </Card>
      </Container>

      <button onClick={onLogout}>Deslogar</button>
    </HomeContainer>
  );
};

const HomeContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
`;

const Container = styled.div`
  border-radius: 3px;
  background-color: white;
  color: black;
  height: 450px;
  width: 450px;
  padding: 50px;
`;

const Card = styled.div`
  border-radius: 5px;
  border: 1px solid gray;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
`;

const CardContent = styled.div`
  display: flex;
  flex-direction: column;
`;

const Space = styled.li`
  display: flex;
  gap: 20px;
`;
