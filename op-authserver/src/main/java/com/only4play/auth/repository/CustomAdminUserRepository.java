package com.only4play.auth.repository;

import com.only4play.auth.domain.adminuser.vo.AdminUserVo;
import org.springframework.data.domain.Page;

public interface CustomAdminUserRepository {
  Page<AdminUserVo> findUserByPage();
}
