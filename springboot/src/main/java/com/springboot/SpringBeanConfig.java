package com.springboot;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class SpringBeanConfig {

	@Autowired
    private SqlSessionFactory sqlSessionFactory;
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		 MapperScannerConfigurer msc = new MapperScannerConfigurer();
		 msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		 msc.setBasePackage("com.user.dao");
		 return msc;
	}
}
