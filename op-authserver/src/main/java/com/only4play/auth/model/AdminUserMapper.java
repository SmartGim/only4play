package com.only4play.auth.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminUserMapper {
  AdminUserMapper INSTANCE = Mappers.getMapper(AdminUserMapper.class);
}
