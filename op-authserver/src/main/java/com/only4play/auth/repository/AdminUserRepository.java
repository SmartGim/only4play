package com.only4play.auth.repository;

import com.only4play.auth.domain.adminuser.AdminUser;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser,Long> {

}
