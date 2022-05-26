import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/movies/api/v1/";
const PUBLIC_API_URL = "http://localhost:8080/auth/api/v1/";

class GatewayService {
  getPublicContent() {
    return axios.get(PUBLIC_API_URL + "register");
  }
  async getMovie(id) {
    return axios.get(API_URL + "movie/" + id, { headers: authHeader() });
  }
  getHomepage() {
    return axios.get(API_URL + "all", { headers: authHeader() });
  }
  getUserBoard() {
    return axios.get(API_URL + "user", { headers: authHeader() });
  }
  getAdminBoard() {
    return axios.get(API_URL + "admin", { headers: authHeader() });
  }
}
export default new GatewayService();
