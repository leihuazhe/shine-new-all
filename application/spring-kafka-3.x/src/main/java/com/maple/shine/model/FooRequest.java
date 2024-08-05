package com.maple.shine.model;

import lombok.Data;

/**
 * FooRequest
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/08/05 15:10
 */
@Data
public class FooRequest {

  private Integer projectId;

  private String name;

  private String displayName;
}
