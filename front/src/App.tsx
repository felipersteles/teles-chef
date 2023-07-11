import { RouterProvider } from "react-router-dom";
import styled from "styled-components";
import { router } from "./router";

function App() {
  return (
    <AppContainer>
      <RouterProvider router={router} />
    </AppContainer>
  );
}

const AppContainer = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: gray;
  color: white;
`;

export default App;
