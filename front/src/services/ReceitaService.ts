import { HttpService } from "./HttpService";

export class ReceitaService extends HttpService {
  getAllReceitas() {
    return this.get("receitas/listar");
  }

  getChef() {
    return this.get("receitas/chef");
  }
}
