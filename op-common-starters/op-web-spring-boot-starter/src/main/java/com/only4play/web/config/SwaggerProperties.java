package com.only4play.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "op.web")
@Data
public class SwaggerProperties {
  private String description = "description";
  private String title = "title";
  private String basePackage = "";
  private String version = "version 1.0.0";
}
