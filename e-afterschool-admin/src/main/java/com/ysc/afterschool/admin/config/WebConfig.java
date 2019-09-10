package com.ysc.afterschool.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	
	private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static/";

	@Value("${resource.uploads.root}")
	private String uploadsRoot;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:///" + uploadsRoot);
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		
//		registry.addResourceHandler("/css/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "css/").setCachePeriod(31536000);
//		registry.addResourceHandler("/images/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "images/").setCachePeriod(31536000);
//		registry.addResourceHandler("/js/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "js/").setCachePeriod(31536000);
//		registry.addResourceHandler("/limitless/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "limitless/").setCachePeriod(31536000);
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
