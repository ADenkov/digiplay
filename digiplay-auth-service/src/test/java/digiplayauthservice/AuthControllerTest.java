//package digiplayauthservice;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import digiplayauthservice.DTO.AppUser;
//import digiplayauthservice.DTO.UserCredentials;
//import digiplayauthservice.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AuthControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository repository;
//
//    private AppUser testUser;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    private final String URL = "/api/v1/";
//
//    @BeforeEach
//    void setup() {
//        testUser = new AppUser(1, "Test", "Test", "test@test.com", "123456", "ADMIN");
//        repository.save(testUser);
//        repository.save(new AppUser(2, "Tonkata", "Picha", "tonkata@gmail.com", "$2a$12$3TUliMKvh/UWU2BX8/EseOOrhMx23Mabv4Z150UJn1hBLKqjWPbra", "ADMIN"));
//    }
//
//    @AfterEach
//    void delete() {
//        repository.deleteAll();
//    }
//
//    private String toJSONString(Object obj) throws JsonProcessingException {
//        return mapper.writeValueAsString(obj);
//    }
//
//    @Test
//    @Order(1)
//    public void shouldRegisterNewUserAndReturnStatus200() throws Exception {
//        AppUser newUser = new AppUser(0, "TestName", "LastName", "testuser@gmail.com", "12345678", "ADMIN");
//        String requestBody = toJSONString(newUser);
//
//        this.mockMvc.perform(post(URL+"/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    public void shouldNotRegisterNewUserAndReturnStatus409() throws Exception {
////        AppUser newUser = new AppUser(0, "TestName", "LastName", "testuser@gmail.com", "12345678", "ADMIN");
////        String requestBody = toJSONString(newUser);
////
////        this.mockMvc.perform(post(URL+"/register")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(requestBody))
////                .andDo(print())
////                .andExpect(status().isConflict())
////                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
////                .andExpect(content().string("User already exists"));
////    }
//
//    @Test
//    public void shouldCheckCorrectCredentialsAndReturnStatus200() throws Exception {
//        UserCredentials credentials = new UserCredentials("tonkata@gmail.com", "123456");
//        String requestBody2 = toJSONString(credentials);
//
//        this.mockMvc.perform(post(URL+"/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody2))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.email", is(credentials.getEmail())))
//                .andExpect(jsonPath("$.role", is(testUser.getRole())))
//                .andExpect(jsonPath("$.token", startsWith("ey")));
//    }
//}
