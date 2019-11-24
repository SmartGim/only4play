package com.only4play.web.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(value = {SwaggerConfiguration.class,CustomMvcConfiguration.class})
public class OpWebAutoConfiguration {

}
