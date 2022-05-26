import axios from "axios";
const API_URL = "http://localhost:8080/auth/api/v1/";
const role = "USER";
class AuthService {
  login(email, password) {
    return axios
      .post(API_URL + "login", {
        email,
        password,
      }, {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem("user");
  }
  register(firstName, lastName, email, password) {
    return axios.post(API_URL + "register", {
      firstName,
      lastName,
      email,
      password,
      role,
    }, {
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });
  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}
export default new AuthService();
