package com.pds1.backend_pds1.roles;

public enum UserRole {
  ADMIN("admin"),
  USER("user");

  private String role;

  private UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
