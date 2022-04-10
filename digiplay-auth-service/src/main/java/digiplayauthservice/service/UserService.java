package digiplayauthservice.service;

import digiplayauthservice.DTO.AppUser;
import digiplayauthservice.DTO.UserCredentials;
import digiplayauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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

    public boolean login(UserCredentials credentials){
        AppUser user = repository.findByEmail(credentials.getEmail()).orElse(null);
        if(user != null){
            // Check if the passwords are matching
            return encoder.matches(credentials.getPassword(),user.getPassword());
        }
        return false;
    }

    public AppUser getByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }
}
