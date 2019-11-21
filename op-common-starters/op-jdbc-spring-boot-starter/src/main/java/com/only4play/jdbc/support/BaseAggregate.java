package com.only4play.jdbc.support;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class BaseAggregate extends AbstractAggregateRoot<BaseAggregate> {
  @Id
  private Long id;
  @Column(value = "version")
  private Integer version;
  @Column(value = "create_time")
  @CreatedDate
  private LocalDateTime createTime;
  @Column(value = "update_time")
  @LastModifiedDate
  private LocalDateTime updateTime;

}