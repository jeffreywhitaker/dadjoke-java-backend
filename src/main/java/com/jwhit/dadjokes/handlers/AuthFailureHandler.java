package com.jwhit.dadjokes.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwhit.dadjokes.models.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFailureHandler implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail("You must be logged in to access this.");
        errorDetail.setDeveloperMessage("invalid token");

        OutputStream out = response.getOutputStream();
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorDetail);
        out.flush();
    }
}

