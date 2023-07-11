import { createBrowserRouter } from "react-router-dom";
import { Navigation } from "./Navigation";
import { Home, Login } from "../pages";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: Navigation,
  },
  {
    path: "/login",
    Component: Login,
  },
  { path: "home", Component: Home },
]);
