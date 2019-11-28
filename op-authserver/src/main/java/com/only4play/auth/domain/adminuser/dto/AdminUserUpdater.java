package com.only4play.auth.domain.adminuser.dto;

import com.only4play.auth.domain.adminuser.AdminUser;
import java.util.Optional;
import lombok.Data;

@Data
public class AdminUserUpdater {
  private String username;
  private String phone;
  private int age;
  private Long secret;

  public void handleAdminUser(AdminUser user){
    Optional.ofNullable(getUsername()).ifPresent(v -> user.setUsername(v));
    Optional.ofNullable(getPhone()).ifPresent(v -> user.setPhone(v));
  }



}
