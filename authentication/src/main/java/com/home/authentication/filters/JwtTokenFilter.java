package com.home.authentication.filters;

import com.home.authentication.services.JwtTokenService;
import com.home.common.rest.client.UserClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserClient userClient;

    public JwtTokenFilter(JwtTokenService jwtTokenService, UserClient userClient) {
        this.jwtTokenService = jwtTokenService;
        this.userClient = userClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get authorization header and validate
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authorizationHeader.split(" ")[1].trim();
        final String username = jwtTokenService.extractUsername(jwt);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            this.userClient.retrieveUserBy(username)
                    .map(userDetails -> {
//                        if (jwtTokenService.validateToken(jwt, userDetails)) {
//
//                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                                    userDetails, null, userDetails.getAuthorities());
//                            usernamePasswordAuthenticationToken
//                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                        }
//                        return jwt;
                        return null;
                    });
        }


//    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//    if (isEmpty(header) || !header.startsWith("Bearer ")) {
//      chain.doFilter(request, response);
//      return;
//    }
//
//    // Get jwt token and validate
//    final String token = header.split(" ")[1].trim();
//    Optional.of(jwtTokenService.validateToken(token))
//
//    if (!) {
//      chain.doFilter(request, response);
//      return;
//    }
//
//    // Get user identity and set it on the spring security context
//    UserDetails userDetails = userRepository
//      .findByUsername(jwtTokenService.extractUsername(token))
//      .orElse(null);
//
//    UsernamePasswordAuthenticationToken
//      authentication = new UsernamePasswordAuthenticationToken(
//      userDetails, null,
//      userDetails == null ?
//        List.of() : userDetails.getAuthorities()
//    );
//
//    authentication.setDetails(
//      new WebAuthenticationDetailsSource().buildDetails(request)
//    );
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    chain.doFilter(request, response);
    }
}