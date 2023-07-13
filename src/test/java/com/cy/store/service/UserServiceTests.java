package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



//该注解表示当前的类是测试类，不会随同项目打包发送
@SpringBootTest
//RunWith表示启动这个单元测试类，需要传递参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTests {
    //idea有检测功能，接口不能直接创建Bean
    @Autowired
    private IUserService userService;
    //单元测试，可以单独运行
    //必须被@test注解所修饰
    //返回值类型必须是void
    //方法的参数列表不能指定任何类型
    //方法的访问修饰符必须是public
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("yuanxin02");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            //获取异常类的对象和名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }

    }


}
