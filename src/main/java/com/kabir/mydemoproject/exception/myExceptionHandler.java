package com.kabir.mydemoproject.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class myExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", accessDeniedException.getMessage());
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public void handleBadCredentialException(HttpServletRequest request, HttpServletResponse response, BadCredentialsException badCredentialsException) throws IOException{
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_CONFLICT);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_CONFLICT);
        body.put("error", "Bad Credentials");
        body.put("message", badCredentialsException.getMessage());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }

}
