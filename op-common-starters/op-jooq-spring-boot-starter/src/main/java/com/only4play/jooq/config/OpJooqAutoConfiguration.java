package com.only4play.jooq.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
@ConditionalOnSingleCandidate(DataSource.class)
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpJooqAutoConfiguration {

  private final DataSource dataSource;

  @Bean
  public DataSourceConnectionProvider connectionProvider() {
    return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
  }

  @Bean
  DefaultDSLContext dsl() {
    return new DefaultDSLContext(configuration());
  }

  public DefaultConfiguration configuration() {
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
    jooqConfiguration.set(new Settings().withRenderSchema(false));
    jooqConfiguration.set(connectionProvider());
    jooqConfiguration.set(SQLDialect.MYSQL);
    return jooqConfiguration;
  }
}
