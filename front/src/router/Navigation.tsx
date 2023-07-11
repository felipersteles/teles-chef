import { useAuth } from "../auth/AuthProvider";
import { Navigate } from "react-router-dom";

export const Navigation = (): JSX.Element => {
  const { token } = useAuth();

  if (!token) return <Navigate to="/login" replace />;

  return <Navigate to="/home" replace />;
};
