package com.only4play.auth.domain.adminuser.dto;

import lombok.Data;

@Data
public abstract class BaseAdminUserCreator {
  private String username;
  private String phone;
}
