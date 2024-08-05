package com.maple.shine.config;

/**
 * AuthContext
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/08/05 15:36
 */
public class AuthContext {

  private static ThreadLocal<Integer> context = ThreadLocal.withInitial(() -> {
    return 0;
  });

  public static Integer getProjectId() {
    return context.get();
  }

  public static void setContext(Integer projectId) {
    context.set(projectId);
  }
}
