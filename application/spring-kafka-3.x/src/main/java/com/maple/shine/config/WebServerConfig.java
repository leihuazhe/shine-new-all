package com.maple.shine.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class WebServerConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration interceptorRegistration = registry.addInterceptor(new WebInterceptor());
    InterceptorRegistration interceptorRegistration2 = registry.addInterceptor(new WebAuthInterceptor());
    interceptorRegistration.addPathPatterns("/**");
    interceptorRegistration2.addPathPatterns("/**");
  }

  /**
   * 重写 MessageConverter, 兼容将返回结果统一转换为  Result 后,不报错的问题
   */
  @Override
  public void configureMessageConverters(@Nullable List<HttpMessageConverter<?>> converters) {
    if (converters == null) {
      converters = new ArrayList<>();
    }
    WebMvcConfigurer.super.configureMessageConverters(converters);
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    // 设置 json 序列化和反序列化
    converter.setObjectMapper(createObjectMapper()
        // openapi 规范了日期的处理，这里是配合 openapi 进行调整
        // 参考：http://gitlab.internal.sensorsdata.cn/sensors-product/sensors-apis/-/merge_requests/1315
        .registerModule(new JavaTimeModule())
        .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy()));
    converters.add(0, converter);
  }

  /**
   * 这个mapper是从 MapperHolder.getApi() 实现中拷贝来的，并且设置了: .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
   * 必须每次都创建一个新的mapper出来，不能直接像之前那样去修改 MapperHolder.getApi()的返回值。
   * 因为这是一个全局对象，不能直接去修改它的行为。
   *
   * @return
   */
  private JsonMapper createObjectMapper() {
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
    return JsonMapper.builder()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .defaultTimeZone(TimeZone.getDefault())
        .addModule(simpleModule)
        .build();
  }
}
