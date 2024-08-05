package com.maple.shine.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * WebInterceptor
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/08/05 15:20
 */
public class WebInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String parameter = request.getParameter("parameter");
    if (parameter == "somevalue") {
      request.setAttribute("customAttribute", "value");
    }

    return true;
  }

}
