package com.callbus.restapi.core.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final SecurityUserDetailService securityUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        JSONObject requestUserDetails = getRequestUserDetails(request);

        try {

            UserDetailsModel user = (UserDetailsModel) securityUserDetailService.loadUserByUsername(requestUserDetails.getString("account_id"));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

            authenticationToken.setDetails(user);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch(Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);

    }

    private JSONObject getRequestUserDetails( HttpServletRequest request ) {

        JSONObject jsonObject = new JSONObject();

        String authentication = request.getHeader("Authentication");

        jsonObject.put("account_id", authentication);
        jsonObject.put("account_type", authentication.substring(0, 7).toUpperCase().trim());
        jsonObject.put("nickname", request.getHeader("Nickname"));

        return jsonObject;

    }

}
