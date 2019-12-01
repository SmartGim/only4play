package com.only4play.web.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@ImportAutoConfiguration(value = {SwaggerConfiguration.class,CustomMvcConfiguration.class})
public class OpWebAutoConfiguration {

  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter
        = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeHeaders(true);
    filter.setIncludeClientInfo(true);
    filter.setAfterMessagePrefix("ONLY REQUEST : ");
    return filter;
  }

}
