package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//该注解表示当前的类是测试类，不会随同项目打包发送
@SpringBootTest
//RunWith表示启动这个单元测试类，需要传递参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //idea有检测功能，接口不能直接创建Bean
    @Autowired
    private UserMapper userMapper;
    //单元测试，可以单独运行
    //必须被@test注解所修饰
    //返回值类型必须是void
    //方法的参数列表不能指定任何类型
    //方法的访问修饰符必须是public
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);

    }
    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

}
