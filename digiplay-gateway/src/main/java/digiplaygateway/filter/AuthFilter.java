package digiplaygateway.filter;

import digiplaygateway.model.WhitelistedRoute;
import digiplaygateway.service.JwtUserDetailsService;
import digiplaygateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtUserDetailsService service;

    /**
     * This method constructs list of unprotected routes
     *
     * @return whitelist List<String>
     */
    private List<WhitelistedRoute> getWhitelistedRoutes() {
        List<WhitelistedRoute> whitelist = new ArrayList<>();
        whitelist.add(new WhitelistedRoute("/auth/api/v1/*", "POST"));
        return whitelist;
    }

    /**
     * This method checks if a route is whitelisted
     *
     * @param url    request url
     * @param method request method
     * @return boolean
     */
    private boolean checkRoute(String url, String method) {
        for (WhitelistedRoute route : getWhitelistedRoutes()) {
            if (url.contains(route.getUrlMatcher()) && route.getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = null;
            String email = null;

            if (authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                try {
                    email = jwtUtil.extractEmail(token);
                } catch (Exception e) {
                    response.sendError(401);
                }
            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = service.loadUserByUsername(email);

                if (jwtUtil.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }

        }
//        else {
//            // If there is no token, check if the route is whitelisted
//            String requestURI = request.getRequestURI();
//            String requestMethod = request.getMethod();
//            if (!checkRoute(requestURI, requestMethod)) {
//                response.sendError(401);
//            }
//
//        }
        filterChain.doFilter(request, response);
    }
}
