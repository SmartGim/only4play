package com.only4play.test;

import com.only4play.auth.domain.adminuser.dto.AdminUserUpdater;
import java.util.Optional;
import org.junit.Test;

public class OptionalTest {

  @Test
  public void test(){
    Optional<String> str = Optional.ofNullable("");
    System.out.println(str.isPresent());
  }

  @Test
  public void testUpdater(){
    AdminUserUpdater updater = new AdminUserUpdater();
    Optional.ofNullable(updater.getUsername()).ifPresent(v -> System.out.println(v));
    Optional.ofNullable(updater.getPhone()).ifPresent(v -> System.out.println(v));
    Optional.ofNullable(updater.getAge()).ifPresent(v -> System.out.println(v));
    Optional.ofNullable(updater.getSecret()).ifPresent(v -> System.out.println(v));
  }
}
