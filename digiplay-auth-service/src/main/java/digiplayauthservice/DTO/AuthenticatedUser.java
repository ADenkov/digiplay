package digiplayauthservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUser {

    private String email;

    private String token;

    private String role;

    private String refreshToken;

}
