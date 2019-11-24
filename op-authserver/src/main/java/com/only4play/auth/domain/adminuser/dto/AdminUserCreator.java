package com.only4play.auth.domain.adminuser.dto;

import lombok.Data;

@Data
public class AdminUserCreator {

  private String phone;
  private String username;
  private Long departId;
  private String departName;
}
