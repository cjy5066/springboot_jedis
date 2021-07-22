package com.jedis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

//序列化
@Configuration
public class RedisConfig {
    //自己定义一个ReidsTemplate
    @Bean
    @SuppressWarnings("all")    //去警告
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        //我们为了自己开发方便，一般直接使用<string，Object>
        RedisTemplate<String, Object> template= new RedisTemplate<String,Object>();
        template.setConnectionFactory(factory);
        System.out.println("========================");
        //json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        //String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key采用string的序列化
        template.setKeySerializer(stringRedisSerializer);

        //hash的key采用string的序列化
        template.setHashKeySerializer(stringRedisSerializer);

        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);

        //hash的valuse序列化方式采用jackson
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }
}
