package com.only4play.auth.domain.adminuser;

import com.only4play.codegen.mapper.GenMapper;
import com.only4play.codegen.vo.GenVo;
import com.only4play.codegen.vo.VoType;
import com.only4play.common.constants.ValidStatus;
import com.only4play.jdbc.support.BaseAggregate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author gim
 */
@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@Table(value = "auth_admin_user")
@GenMapper(pkgName = "com.only4play.auth.model")
@GenVo(pkgName = "com.only4play.auth.model")
public class AdminUser extends BaseAggregate {
  private String username;
  private String password;
  @Column(value = "depart_id")
  private Long departId;
  @Column(value = "depart_name")
  private String departName;
  private String phone;
  @Column(value = "valid_status")
  @VoType(toType = String.class)
  private ValidStatus validStatus;

  public void init() {
    this.setValidStatus(ValidStatus.VALID);
  }

  public void invalid() {
    setValidStatus(ValidStatus.INVALID);
  }

  public void valid() {
    setValidStatus(ValidStatus.VALID);
  }

}
