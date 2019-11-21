package com.only4play.auth.service;

import com.only4play.auth.domain.adminuser.dto.UserDTO;

public interface IAdminUserService {

  Long saveUser(UserDTO userDTO);

}
