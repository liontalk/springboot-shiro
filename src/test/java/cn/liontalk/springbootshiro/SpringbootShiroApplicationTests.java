package cn.liontalk.springbootshiro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroApplicationTests {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }


    @Test
    public void setKey(){
        redisTemplate.opsForValue().set("hello","zhouzhe");
    }


    @Test
    public void getKey(){
       String result =(String) redisTemplate.opsForValue().get("hello");
        System.out.println(result);
    }

}
