package com.t1m0.todo;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.t1m0.todo")
public class AppConfig extends RepositoryRestMvcConfiguration {
  private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

  public static void main(String[] args) {
    SpringApplication.run(AppConfig.class, args);
  }

  @Override
  protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    super.configureRepositoryRestConfiguration(config);
    try {
      config.setBaseUri(new URI("/rest"));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

}
