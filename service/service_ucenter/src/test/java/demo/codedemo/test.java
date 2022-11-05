package demo.codedemo;

import com.atguigu.educenter.UcenterApplication;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.educenter.util.redisconstant;
import com.atguigu.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.unit.DataUnit;

import java.sql.Date;

/**
 * @ClassName test
 * @Description TODO
 * @Date 2022/11/4 21:35
 */
@SpringBootTest(classes = UcenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class test {
    @Autowired
    UcenterMemberService service;
    @Test
    public void test(){
        LambdaQueryWrapper<UcenterMember> wq = new LambdaQueryWrapper<>();

    }
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test2(){
        String key = redisconstant.createloginkey();
        System.out.println(redisTemplate.opsForValue().increment(key));

    }
}
