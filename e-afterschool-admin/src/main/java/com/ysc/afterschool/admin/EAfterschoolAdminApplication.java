package com.ysc.afterschool.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EAfterschoolAdminApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EAfterschoolAdminApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EAfterschoolAdminApplication.class, args);
	}
}
