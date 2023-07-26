import axios from "axios";

export class HttpService {
  private axios;

  constructor() {
    this.axios = axios.create({
      withCredentials: true,
      baseURL: "http://localhost:8080/api/",
    });

    axios.defaults.withCredentials = true;
  }

  post(url: string, data: any) {
    return this.axios.post(url, data);
  }

  get(url: string) {
    return this.axios.get(url);
  }
}
