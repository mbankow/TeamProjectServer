package com.team.project.auth.security.jwt;

import com.team.project.auth.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);
            if(token != null){
                String email = jwtUtils.getEmailFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,
                                                                                        null,
                                                                                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch(Exception e){
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest req) {
        String headerAuth = req.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer "))
            return headerAuth.replace("Bearer ", "");
        return null;
    }
}
