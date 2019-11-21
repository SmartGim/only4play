package com.only4play.common.constants;

import java.util.Optional;

/**
 * @author gim
 */

public enum CodeEnum implements BaseEnum<CodeEnum> {
  /**
   * 整个系统通用编码 xx_xx_xxxx (服务标识_业务_错误编号，便于错误快速定位
   */
  Success(1, "操作成功"),
  Fail(0, "操作失败"),


  NotFindError(20000001, "未查询到信息"),
  SaveError(20000002, "保存信息失败"),
  UpdateError(20000003, "更新信息失败"),
  ValidateError(20000004, "数据检验失败"),
  TokenExpired(20000005, "令牌过期"),
  StatusHasValid(2000006, "状态已经被启用"),
  StatusHasInvalid(2000007, "状态已经被禁用"),

  SystemError(10000001, "系统异常"),
  BusinessError(10000002, "业务异常"),
  ParamSetIllegal(10000003, "参数设置非法"),
  TransferStatusError(10000004, "当前状态不正确，请勿重复提交");
  private Integer code;
  private String msg;

  CodeEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getName() {
    return this.msg;
  }

  public static Optional<CodeEnum> of(Integer code) {
    return Optional.ofNullable(BaseEnum.parseByCode(CodeEnum.class, code));
  }
}
