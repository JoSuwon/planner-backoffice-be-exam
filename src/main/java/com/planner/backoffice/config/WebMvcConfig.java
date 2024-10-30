package com.planner.backoffice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.planner.backoffice.interceptor.AdminAuthenticationInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  private final AdminAuthenticationInterceptor adminAuthenticationInterceptor;

  public WebMvcConfig(AdminAuthenticationInterceptor adminAuthenticationInterceptor) {
    this.adminAuthenticationInterceptor = adminAuthenticationInterceptor;
  }

  @Override
  public void addInterceptors(@NonNull InterceptorRegistry registry) {
    registry.addInterceptor(adminAuthenticationInterceptor)
        .addPathPatterns("/api/**")
        .excludePathPatterns("/api/admin/signin", "/api/admin/signup");
  }
}
