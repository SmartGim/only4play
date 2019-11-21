package com.only4play.jooq.ext;

import java.util.function.Supplier;
import lombok.Data;
import org.jooq.Condition;

@Data
public class ConditionWrapper {

  private Condition condition;

  public ConditionWrapper(Condition condition) {
    this.condition = condition;
  }

  public Condition buildCondition(Supplier<Condition> sp) {
    this.condition = sp.get();
    return condition;
  }
}
