package digiplayauthservice.service;

import digiplayauthservice.DTO.AppUser;
import digiplayauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    public AppUser register(AppUser user){
        try {
            String encodedPassword = encoder.encode(user.getPassword());

            AppUser newUser = new AppUser(0, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
            return repository.save(newUser);
        }
        catch(Exception e) {
            return null; // define custom exception
        }
    }
}
