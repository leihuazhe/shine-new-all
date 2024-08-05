package com.maple.shine.controller;

import com.maple.shine.model.FooRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * FooController
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/08/05 15:10
 */
@RestController
public class FooController {

  @PostMapping("/foo")
  public Object foo(@RequestBody FooRequest request) {
    System.out.println(request);
    return request;
  }
}
