package com.planner.backoffice.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.planner.backoffice.annotation.RequireAdminToken;
import com.planner.backoffice.util.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {
  private final JwtTokenProvider jwtTokenProvider;

  public AdminAuthenticationInterceptor(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    if (!handlerMethod.hasMethodAnnotation(RequireAdminToken.class)) {
      return true;
    }

    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      sendErrorResponse(response, "접근 할 수 없습니다. (유효하지 않은 토큰)");
      return false;
    }

    String token = authHeader.substring(7);

    if (!jwtTokenProvider.validateToken(token)) {
      sendErrorResponse(response, "접근 할 수 없습니다. (유효하지 않은 토큰)");
      return false;
    }

    Long adminId = jwtTokenProvider.getAdminIdFromToken(token);
    request.setAttribute("adminId", adminId);

    return true;
  }

  private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String jsonResponse = String.format(
        "{\"status\": %d, \"message\": \"%s\"}",
        HttpServletResponse.SC_UNAUTHORIZED,
        message);

    response.getWriter().write(jsonResponse);
  }
}
