import { LoginAuthParams, RefreshTokenParams } from "../auth";
import { HttpService } from "./HttpService";

export class AuthService extends HttpService {
  login(credentials: LoginAuthParams) {
    return this.post("auth/login", credentials);
  }

  refreshToken(refreshToken: RefreshTokenParams) {
    return this.post("auth/refreshToken", refreshToken);
  }
}
