package com.springboot;



import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class MyBatisConfig {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driverClassName}")
	private String driver;
	
	@Autowired
	private BasicDataSource dataSource;
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/pojo/*.xml"));
		return sqlSessionFactory.getObject();
		
	}
}
