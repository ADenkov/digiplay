package digiplaygateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WhitelistedRoute {

    private String urlMatcher;

    private String method;
}
