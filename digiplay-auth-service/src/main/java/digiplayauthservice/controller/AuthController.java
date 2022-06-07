package digiplayauthservice.controller;


import digiplayauthservice.DTO.AppUser;
import digiplayauthservice.DTO.AuthenticatedUser;
import digiplayauthservice.DTO.UserCredentials;
import digiplayauthservice.service.JwtService;
import digiplayauthservice.service.UserService;
import digiplayauthservice.util.Base64Util;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {

    public static final String BASE_URL = "/api/v1";

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        AppUser registeredUser = userService.register(user);

        if (registeredUser == null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("User successfully registered with email " + registeredUser.getEmail(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials credentials) {
        boolean areCredentialsValid = userService.login(credentials);

        if (areCredentialsValid) {
            String token = jwtService.generateToken(credentials.getEmail());
            AppUser authorizedUser = userService.getByEmail(credentials.getEmail());


            String stringToEncode = authorizedUser.getEmail() + ",has role," + authorizedUser.getRole();
            String refreshToken = Base64.encodeBase64String(stringToEncode.getBytes());

            AuthenticatedUser request = new AuthenticatedUser(authorizedUser.getId(), credentials.getEmail(), token, authorizedUser.getRole(), refreshToken);

            return new ResponseEntity<>(request, HttpStatus.OK);
        }

        return new ResponseEntity<>("Wrong Credentials", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getByEmail(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody AuthenticatedUser request) {

        try {
            boolean isTokenValid = jwtService.validateToken(request.getToken(), request.getEmail());
            return isTokenValid ? new ResponseEntity<>("Valid Token", HttpStatus.OK)
                    : new ResponseEntity<>("Invalid Token", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid Token", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestParam String refreshToken) {

        // Get decoded bytes
        byte[] decodedBytes = Base64.decodeBase64(refreshToken.getBytes());

        // Convert to String
        String[] decodedToken = Base64Util.decodeBytes(decodedBytes).split(",");

        String email = decodedToken[0];

        AppUser user = userService.getByEmail(email);

        if (user != null && user.getRole().equals(decodedToken[2])) {
            // Generate new JWT
            String newJwt = jwtService.generateToken(email);
            AuthenticatedUser dto = new AuthenticatedUser(user.getId(), email, newJwt, user.getRole(), refreshToken);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }


        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {

        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);

    }
}
