package com.only4play.auth.service.impl;

import com.only4play.auth.domain.adminuser.AdminUser;
import com.only4play.auth.domain.adminuser.dto.AdminUserCreator;
import com.only4play.auth.domain.adminuser.dto.AdminUserQuery;
import com.only4play.auth.domain.adminuser.dto.AdminUserUpdater;
import com.only4play.auth.domain.adminuser.mapper.AdminUserMapper;
import com.only4play.auth.domain.adminuser.vo.AdminUserVo;
import com.only4play.auth.repository.AdminUserRepository;
import com.only4play.auth.service.IAdminUserService;
import com.only4play.common.model.PageRequestWrapper;
import com.only4play.jdbc.support.BaseService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends BaseService implements IAdminUserService {

  private final AdminUserRepository adminUserRepository;

  @Autowired
  public AdminUserServiceImpl(AdminUserRepository adminUserRepository) {
    this.adminUserRepository = adminUserRepository;
  }

  @Override
  public Optional<Long> saveUser(AdminUserCreator userCreator) {
    Optional<AdminUser> user = doCreate(adminUserRepository)
        .create(() -> AdminUserMapper.INSTANCE.creatorToEntity(userCreator))
        .init(adminUser -> adminUser.init())
        .execute();
    return user.map(u -> u.getId());
  }

  @Override
  public void update(AdminUserUpdater updater) {

  }

  @Override
  public Page<AdminUserVo> pageAdminUser(PageRequestWrapper<AdminUserQuery> wrapper) {
    return null;
  }

  @Override
  public List<AdminUserVo> findAll(AdminUserQuery query) {
    return null;
  }

  @Override
  public void valid(Long id) {

  }

  @Override
  public void invalid(Long id) {

  }

//  @Override
//  public Long saveUser(UserDTO userDTO) {
//    AdminUser adminUser = new AdminUser();
//    adminUser.setDepartId(3L);
//    adminUser.setDepartName(userDTO.getDepartName());
//    adminUser.setUsername(userDTO.getUsername());
//    adminUser.setPhone(userDTO.getPhone());
//    adminUser.setPassword(userDTO.getPassword());
//    adminUser.setValidStatus(ValidStatus.VALID);
//    Optional<AdminUser> result = doCreate(adminUserRepository)
//        .create(AdminUser::new)
//        .update(u -> u.init())
//        .execute();
//    if(result.isPresent()){
//      return result.get().getId();
//    }else {
//      return null;
//    }
//  }
}
