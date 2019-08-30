package com.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Service;

@Service
public class MyFilter implements Filter {

	 @Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("前过滤器");
		chain.doFilter(request, response);
        System.out.println("后过滤器");
	}
	
	@Override
    public void destroy() {
    }

}
