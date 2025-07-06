package sn.zeitune.olive_insurance_pricing.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);
        try {
            final Claims claims = jwtService.extractAllClaims(token);
            
            if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String userIdStr = claims.getSubject();
                String role = claims.get("role", String.class);
                String userType = claims.get("userType", String.class);
                
                // Support both "role" and "userType" fields
                String effectiveRole = role != null ? role : userType;
                
                if (userIdStr != null && effectiveRole != null) {
                    // Handle both UUID and email subjects
                    String username = userIdStr;
                    try {
                        UUID userId = UUID.fromString(userIdStr);
                        username = userId.toString();
                    } catch (IllegalArgumentException e) {
                        // userIdStr is not a UUID, use as is (probably an email)
                        username = userIdStr;
                    }
                    
                    UserDetails userDetails = User.builder()
                            .username(username)
                            .password("")
                            .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + effectiveRole)))
                            .build();
                    
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (JwtException e) {
            log.error("JWT validation error: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }


}
