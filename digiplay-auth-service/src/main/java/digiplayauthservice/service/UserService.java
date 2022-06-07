package digiplayauthservice.service;

import digiplayauthservice.DTO.AppUser;
import digiplayauthservice.DTO.UserCredentials;
import digiplayauthservice.repository.UserRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    private final KafkaTemplate<String, Long> template;

    public UserService(KafkaTemplate<String, Long> template) {
        this.template = template;
    }


    public AppUser register(AppUser user) {
        try {
            String encodedPassword = encoder.encode(user.getPassword());

            AppUser newUser = new AppUser(0, user.getFirstName(), user.getLastName(), user.getEmail(), encodedPassword, user.getRole());
            return repository.save(newUser);
        } catch (Exception e) {
            return null; // define custom exception
        }
    }

    public boolean login(UserCredentials credentials) {
        AppUser user = repository.findByEmail(credentials.getEmail()).orElse(null);
        if (user != null) {
            // Check if the passwords are matching
            return encoder.matches(credentials.getPassword(), user.getPassword());
        }
        return false;
    }


    public boolean deleteUser(Long userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            try {
                template.send("deleteSubscription", userId);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }
            return true;
        }
        return false;
    }

    public AppUser getByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}
