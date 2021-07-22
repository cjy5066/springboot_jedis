package com.jedis;

import com.jedis.domian.User;
import com.jedis.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class SpringbootJedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")     //当一个接口有多个实现的时候，为了指名具体调用哪个类的实现   点名点姓
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;
    @Test
    void contextLoads() {

        redisTemplate.opsForValue().set("name","cjy");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }



    /**
     * 测试添加对象，先不使用序列化，添加是否成功
     *
     * 先将Qualifier注释，随后将redisConfig.java注释
     *      测试后，添加失败。提示要序列化
     *
     *      取消注释，添加成功
     */
    @Test
    public void addUser(){
        User user = new User("陈家宇", "男");
        redisTemplate.opsForValue().set("user",user);
        Object user1 = redisTemplate.opsForValue().get("user");
        System.out.println(user1);
    }

    /**
     * 测试使用工具包，
     */
    @Test
    public void Testutils(){
        User user = new User("陈家宇", "男");
        redisUtil.set("user",user);
        System.out.println(redisUtil.get("user"));
    }

}
