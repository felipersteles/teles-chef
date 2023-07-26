import { useAuth } from "../auth/AuthProvider";
import { Navigate } from "react-router-dom";

export const Navigation = (): JSX.Element => {
  const { userSession } = useAuth();

  if (!userSession) return <Navigate to="/login" replace />;

  return <Navigate to="/home" replace />;
};
