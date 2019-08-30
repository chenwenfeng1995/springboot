package com.springboot;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.Statement;
import java.time.Duration;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

@Configuration
public class MyRedisConfig extends  CachingConfigurerSupport{

	   @Autowired
	    private RedisConnectionFactory factory;
	    @Autowired
	    private RedisTemplate<String, Object> redisTemplate;
	    
	    @Bean
	    public CacheManager cacheManager() {
	        //RedisCacheManager cacheManager = RedisCacheManager.builder(factory).build();
	        //return cacheManager;
	        
	        RedisSerializer<String> keySerializer = new StringRedisSerializer();
	        FastJsonRedisSerializer<Object> rs = new FastJsonRedisSerializer<Object>(Object.class);
	        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig();
	        RedisCacheConfiguration rediscacheconf = conf.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer));
	        rediscacheconf.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(rs));
	        //配置注解默认的过期时间
	        rediscacheconf.entryTtl(Duration.ofDays(1));
	        ParserConfig.getGlobalInstance().addAccept("cn.sz.gl");
	        return RedisCacheManager.builder(factory).cacheDefaults(rediscacheconf).build();
	    }
	    
	    @Bean
	    public RedisTemplate<String, Object> redisTemplate(){
	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String,Object>();
	        redisTemplate.setKeySerializer(new StringRedisSerializer(Charset.forName("gbk")));
	        FastJsonRedisSerializer<Object> rs = new FastJsonRedisSerializer<Object>(Object.class);
	        redisTemplate.setValueSerializer(rs);
	        redisTemplate.setHashKeySerializer(rs);
	        redisTemplate.setHashValueSerializer(rs);
	        redisTemplate.setDefaultSerializer(rs);
	        redisTemplate.setConnectionFactory(factory);
	        redisTemplate.afterPropertiesSet();
	        return redisTemplate;
	    }
	    
	    @Bean
	    public KeyGenerator keyGenerrator() {
	        return new KeyGenerator() {
	            @Override
	            public Object generate(Object target, Method method, Object... params) {
	                StringBuffer sb = new StringBuffer();
	             	sb.append(target.getClass().getName());
	             	sb.append(method.getName());
		             for(Object obj:params){
	            	 	sb.append(obj.toString());
		             }
	             	System.out.println("调用Redis生成key："+sb.toString());
	                return sb.toString();
	            }
	        };
	    } 
	}