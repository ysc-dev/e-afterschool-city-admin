package com.ysc.afterschool.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * 화면 레이아웃 설정
 * 
 * @author hgko
 *
 */
@Configuration
public class TilesConfig {

	@Bean
	public TilesConfigurer tilesConfigurer() {
		
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles.xml" });
		
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(1);
		
		return resolver;
	}
}
