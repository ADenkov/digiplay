package digiplaygateway.service;

import digiplaygateway.model.AppUser;
import digiplaygateway.model.AuthorizedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService{
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String url = String.format("http://localhost:8081/api/auth/%s", email);
        AppUser user = webClientBuilder
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(AppUser.class)
                .block();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());

    }

    public Optional<AuthorizedDto> refreshToken(String refreshToken) {

        // Refresh the token from auth-service
        String url = String.format("http://localhost:8082/api/v1/refreshToken?refreshToken=%s", refreshToken);
        AuthorizedDto dto = webClientBuilder
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(AuthorizedDto.class)
                .block();

        return dto == null ? Optional.empty() : Optional.of(dto);
    }
}
