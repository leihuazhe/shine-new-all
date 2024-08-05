package com.maple.shine.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * AuthUtil 的初始化
 *
 * @author chenxiaoming
 * @version 1.0.0
 */
@Slf4j
public class WebAuthInterceptor implements HandlerInterceptor {



  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    try {
      Integer projectId = Integer.valueOf(request.getHeader("project"));
      AuthContext.setContext(projectId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }


}
