package com.springboot;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterRegistryBean {

	@Autowired
    private Filter myFilter;
    
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(){
        FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<Filter>();
        frb.setFilter(myFilter);
        frb.addUrlPatterns("/*");
        frb.setName("myfilter");
        return frb;
	}
}
