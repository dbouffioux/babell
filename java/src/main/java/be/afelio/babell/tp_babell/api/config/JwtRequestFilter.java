package be.afelio.babell.tp_babell.api.config;


import be.afelio.babell.tp_babell.api.dto.jwt.UserDetailsDto;
import be.afelio.babell.tp_babell.api.jwt.model.JwtRequest;
import be.afelio.babell.tp_babell.api.service.JwtUserDetailsService;
import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        if (username == null && request.getParameter("btoa") != null) {
            String btoa = request.getParameter("btoa");
            byte[] decodedBtoa = Base64.getDecoder().decode(btoa);
            String btoaString = new String(decodedBtoa);

            String[] parts = btoaString.split(":");
            username = parts[0];
            String password = parts[1];
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            jwtToken = jwtTokenUtil.generateToken(userDetails);
            UserDetailsDto user = new UserDetailsDto(username, password);

            if (jwtTokenUtil.validateToken(jwtToken, user)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                JwtRequest jwtRequest = new JwtRequest(username, password);
                request.setAttribute("jwtRequest",jwtRequest);
            }

        }
        chain.doFilter(request, response);
    }
}
