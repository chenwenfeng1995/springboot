package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.util.LoginIntercepter;

@Configuration
public class MyIntercepters implements WebMvcConfigurer {

	@Autowired
	private LoginIntercepter loginIntercepter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginIntercepter).addPathPatterns("/**");
	}
}
