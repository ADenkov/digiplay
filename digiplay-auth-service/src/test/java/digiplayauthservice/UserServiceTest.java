package digiplayauthservice;

import digiplayauthservice.DTO.AppUser;
import digiplayauthservice.repository.UserRepository;
import digiplayauthservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;
    private AppUser testUserOne;
    private AppUser testUserTwo;
    private List<AppUser> users;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        users = new ArrayList<>();
        testUserOne = new AppUser(1, "Tino", "Pedala", "kote@gmail.com", "123456", "ADMIN");
        testUserTwo = new AppUser(2, "Tonkata", "Picha", "toni@gmail.com", "123456", "ADMIN");
        repository.save(testUserTwo);
    }

    @AfterEach
    public void tearDown() {
        testUserOne = null;
        testUserTwo = null;
        users = null;
    }

    @Test
    void shouldGetUserByEmail() {
        when(repository.findByEmail(testUserOne.getEmail())).thenReturn(Optional.of(testUserOne));
        AppUser registeredUser = service.getByEmail("kote@gmail.com");
        Assertions.assertEquals(testUserOne, registeredUser);
    }

    @Test
    void shouldRegisterNewUser() {
        AppUser user = new AppUser();
        user.setEmail("test@test.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPassword("654321");
        user.setRole("ADMIN");

        service.register(user);

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        AppUser registeredUser = service.getByEmail("test@test.com");
        Assertions.assertEquals(user, registeredUser);
    }

}
