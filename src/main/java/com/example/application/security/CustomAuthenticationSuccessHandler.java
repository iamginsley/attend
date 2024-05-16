package com.example.application.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectURL = "/";

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println(authority.getAuthority());
            String role = authority.getAuthority();
            redirectURL = switch (role.toLowerCase()) {
                case "admin" -> redirectURL + "admin-view";
                case "student" -> redirectURL + "student-view";
                case "lecturer" -> redirectURL + "teacher-view";
                default -> redirectURL;
            };
        }

        System.out.println("Redirecting to: " + redirectURL);

        response.sendRedirect(redirectURL);
    }
}
