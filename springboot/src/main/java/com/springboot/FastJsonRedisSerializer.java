package com.springboot;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private ObjectMapper objectManager = new ObjectMapper();
    
    private Class<T> cls;
    
    public FastJsonRedisSerializer(Class<T> cls) {
        this.cls = cls;
    }
    
    @Override
    public T deserialize(byte[] bs) throws SerializationException {
        if(bs==null||bs.length<=0) {
            return null;
        }
        String str = new String(bs,Charset.forName("gbk"));
        return JSON.parseObject(str,cls);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t==null) {
            return new byte[0];
        }
        return JSON.toJSONString(t,SerializerFeature.WriteClassName).getBytes(Charset.forName("gbk"));
    }

    public void setObjectManager(ObjectMapper objectManager) {
        this.objectManager = objectManager;
    }
    
    public JavaType getJavaType(Class cls) {
        return TypeFactory.defaultInstance().constructType(cls);
    }
}
