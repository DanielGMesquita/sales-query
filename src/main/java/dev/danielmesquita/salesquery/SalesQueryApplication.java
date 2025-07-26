package dev.danielmesquita.salesquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(
    pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class SalesQueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesQueryApplication.class, args);
  }
}
