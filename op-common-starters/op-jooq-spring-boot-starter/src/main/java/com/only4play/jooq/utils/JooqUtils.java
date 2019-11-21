package com.only4play.jooq.utils;

import com.only4play.jooq.ext.ConditionWrapper;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.jooq.Condition;

/**
 * @author gim
 * 函数式拼接条件查询
 */
public final class JooqUtils {

  private JooqUtils() {
  }

  public <T> void and(ConditionWrapper wrapper, T t, Predicate<T> filter, Supplier<Condition> supplier) {
    Optional.ofNullable(t).filter(filter).ifPresent(c -> {
      wrapper.buildCondition(() -> wrapper.getCondition().and(supplier.get()));
    });
  }

  public <T> void or(ConditionWrapper wrapper, T t, Predicate<T> filter, Supplier<Condition> supplier) {
    Optional.ofNullable(t).filter(filter).ifPresent(c -> {
      wrapper.buildCondition(() -> wrapper.getCondition().or(supplier.get()));
    });
  }

}
