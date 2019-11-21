package com.only4play.auth.service.impl;

import com.only4play.auth.domain.adminuser.AdminUser;
import com.only4play.auth.domain.adminuser.dto.UserDTO;
import com.only4play.auth.repository.AdminUserRepository;
import com.only4play.auth.service.IAdminUserService;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jdbc.support.BaseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends BaseService implements IAdminUserService {

  private final AdminUserRepository adminUserRepository;

  @Autowired
  public AdminUserServiceImpl(AdminUserRepository adminUserRepository) {
    this.adminUserRepository = adminUserRepository;
  }

  @Override
  public Long saveUser(UserDTO userDTO) {
    AdminUser adminUser = new AdminUser();
    adminUser.setDepartId(3L);
    adminUser.setDepartName(userDTO.getDepartName());
    adminUser.setUsername(userDTO.getUsername());
    adminUser.setPhone(userDTO.getPhone());
    adminUser.setPassword(userDTO.getPassword());
    adminUser.setValidStatus(ValidStatus.VALID);
    Optional<AdminUser> result = doCreate(adminUserRepository)
        .create(AdminUser::new)
        .update(u -> u.init())
        .execute();
    if(result.isPresent()){
      return result.get().getId();
    }else {
      return null;
    }
  }
}
