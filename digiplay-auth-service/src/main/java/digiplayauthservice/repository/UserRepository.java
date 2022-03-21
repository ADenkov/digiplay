package digiplayauthservice.repository;

import digiplayauthservice.DTO.AppUser;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser,Long>{
    Optional<AppUser> findByEmail(String email);
}
