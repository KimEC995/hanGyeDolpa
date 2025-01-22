package com.koreait.hanGyeDolpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.koreait.hanGyeDolpa.repository")
public class HanGyeDolpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanGyeDolpaApplication.class, args);
	}
}