package com.only4play.auth.service;

import com.only4play.auth.domain.adminuser.dto.AdminUserCreator;
import com.only4play.auth.domain.adminuser.dto.AdminUserQuery;
import com.only4play.auth.domain.adminuser.dto.AdminUserUpdater;
import com.only4play.auth.domain.adminuser.vo.AdminUserVo;
import com.only4play.common.model.PageRequestWrapper;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface IAdminUserService {

  Optional<Long> saveUser(AdminUserCreator userCreator);

  void update(AdminUserUpdater updater);

  Page<AdminUserVo> pageAdminUser(PageRequestWrapper<AdminUserQuery> wrapper);

  List<AdminUserVo> findAll(AdminUserQuery query);

  void valid(Long id);

  void invalid(Long id);
}
