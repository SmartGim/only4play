package com.only4play.auth.domain.adminuser.mapper;

import com.only4play.auth.domain.adminuser.AdminUser;
import com.only4play.auth.domain.adminuser.dto.AdminUserCreator;
import com.only4play.auth.domain.adminuser.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminUserMapper {
  AdminUserMapper INSTANCE = Mappers.getMapper(AdminUserMapper.class);

  UserDTO entityToDTO(AdminUser user);

  default AdminUser creatorToEntity(AdminUserCreator creator){
    AdminUser user = new AdminUser();
    user.setPhone(creator.getPhone());
    user.setUsername(creator.getUsername());
    user.setDepartName(creator.getDepartName());
    user.setDepartId(creator.getDepartId());
    return user;
  }
}
