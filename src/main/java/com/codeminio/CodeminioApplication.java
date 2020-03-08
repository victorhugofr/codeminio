package com.codeminio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"com.codeminio.model"})
@ComponentScan(basePackages = {"com.codeminio.*"})
@EnableJpaRepositories(basePackages = {"com.codeminio.repositories"})
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
@Configuration
@EnableWebMvc
public class CodeminioApplication implements WebMvcConfigurer {

	@Override
	public void addCorsMappings( CorsRegistry registry ) {
		registry.addMapping("/**");
	}
	public static void main(String[] args) {
		SpringApplication.run(CodeminioApplication.class, args);
	}

}
