package com.homework.simpleidus.api.configuration.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.homework.simpleidus.api.controller.constant.SessionConstant.LOGIN_USER;

@Slf4j
@Component
public class AuthUserFilter implements Filter {
    private static final Map<String, List<HttpMethod>> PERMISSION_REQUIRED_URLS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("/order", Arrays.asList(HttpMethod.POST))
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (hasPermissionRequiredUrl(httpRequest.getRequestURI(), httpRequest.getMethod())) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(LOGIN_USER.getValue()) == null) {
                httpResponse.setStatus(401);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean hasPermissionRequiredUrl(String requestedUrl, String requiredHttpMethod) {
        String permissionRequiredUrl = PERMISSION_REQUIRED_URLS.keySet().stream()
                .filter(url -> requestedUrl.contains(url))
                .findFirst()
                .orElse(null);

        if (permissionRequiredUrl != null) {
            return PERMISSION_REQUIRED_URLS.get(permissionRequiredUrl).stream()
                    .anyMatch(httpMethod -> httpMethod.name().equals(requiredHttpMethod));
        }

        return false;
    }
}
