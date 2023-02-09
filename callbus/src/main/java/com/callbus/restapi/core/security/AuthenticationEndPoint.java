package com.callbus.restapi.core.security;

import com.callbus.restapi.core.model.ResponseApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEndPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        final ResponseApi responseApi = new ResponseApi();
        final ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        responseApi.setResponseCode(HttpStatus.UNAUTHORIZED.value());
        responseApi.setResponseMessage(HttpStatus.UNAUTHORIZED.name());
        responseApi.setResponseData(new Object());

        objectMapper.writeValue(response.getOutputStream(), responseApi);

    }

}
