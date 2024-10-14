package com.crms.hrms_backend.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crms.hrms_backend.Service.ServiceImpl.UserDetailServiceImpl;
import com.crms.hrms_backend.Utils.JwtUtills;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtills jwtUtills;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwtToken = extractTokenFromRequest(request);
            if (StringUtils.hasText(jwtToken) && jwtUtills.validateToken(jwtToken)) {
                String username = jwtUtills.extractUsername(jwtToken);
                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (ExpiredJwtException e) {
            log.info("JWT Token has expired");
            log.error("JWT Token has expired. Additional details", e.getMessage());
        } catch (Exception e) {
            log.info("Error in token validation");

        }
        filterChain.doFilter(request, response);

    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String tokenWithBearer = request.getHeader("Authorization");
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
            return tokenWithBearer.substring(7, tokenWithBearer.length());
        }
        return null;
    }

}
