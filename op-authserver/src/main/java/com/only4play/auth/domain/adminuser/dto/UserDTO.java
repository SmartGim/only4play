package com.only4play.auth.domain.adminuser.dto;

import lombok.Data;

@Data
public class UserDTO {
  private String username;
  private String phone;
  private String password;
  private String departName;
}
