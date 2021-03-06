package com.only4play.auth.web;

import com.only4play.auth.domain.adminuser.dto.AdminUserCreator;
import com.only4play.auth.domain.adminuser.dto.AdminUserDTO;
import com.only4play.auth.domain.adminuser.dto.UserDTO;
import com.only4play.auth.service.IAdminUserService;
import com.only4play.common.model.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "adminUser")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminUserController {

  private final IAdminUserService adminUserService;

  @GetMapping(value = "test")
  public void test(){
    UserDTO dto = new UserDTO();
    dto.setDepartName("ggg");
    dto.setUsername("dgds");
    dto.setPhone("3333");
    dto.setDepartName("及地点");
//    adminUserService.saveUser(dto);
  }

  @PostMapping(value = "create")
  public JsonObject<Long> create(@RequestBody AdminUserCreator dto){
    return JsonObject.success(adminUserService.saveUser(dto).orElse(null));
  }
}
