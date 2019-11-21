package com.only4play.auth.repository;

import com.only4play.auth.domain.adminuser.vo.AdminUserVo;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;

public class CustomAdminUserRepositoryImpl implements CustomAdminUserRepository{
  private final DSLContext jooq;

  public CustomAdminUserRepositoryImpl(DSLContext jooq) {
    this.jooq = jooq;
  }


  @Override
  public Page<AdminUserVo> findUserByPage() {
    return null;
  }
}
