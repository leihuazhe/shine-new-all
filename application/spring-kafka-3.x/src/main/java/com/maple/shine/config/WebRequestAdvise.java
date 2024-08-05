package com.maple.shine.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * WebRequestAdvise
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/08/05 15:28
 */
@RestControllerAdvice
@Slf4j
public class WebRequestAdvise implements RequestBodyAdvice {

  private static final String API_CLASS_NAME_PREFIX = "com.maple.shine.controller";

  @Override
  public boolean supports(MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    String apiClassName = parameter.getContainingClass().getName();
    if (StringUtils.startsWith(apiClassName, API_CLASS_NAME_PREFIX)) {
      return true;
    }
    return false;
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
    log.info("In beforeBodyRead() method of {}", getClass().getSimpleName());
    return inputMessage;
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    log.info("In afterBodyRead() method of {}", getClass().getSimpleName());

    Field projectIdField;
    try {
      projectIdField = body.getClass().getDeclaredField("projectId");
      projectIdField.setAccessible(true);
      projectIdField.set(body, AuthContext.getProjectId());

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return body;
  }

  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }
}
