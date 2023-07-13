package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

//service注解将当前类的对象交给spring来管理，自动创建对象以及对象的维护
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {

        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername（username）判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        //判断结果集是否不为null则抛出用户名被占用的异常
        if(result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //加密处理的实现：md5
        //（串+password+串） 再将整体交给md5算法进行加密，连续加载三次
        //串叫做盐值 盐值+password+盐值  盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值(随机生成一个盐值)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据：盐值的记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理,忽略原有密码强度提升了数据的安全性
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密之后的密码重新补全设置到User对象终
        user.setPassword(md5Password);


        //补全数据：is_delete=0
        user.setIsDelete(0);

        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

         //执行注册业务功能的实现(rows=1)
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }



    }

    /**
     * 定义一个md5算法的加密处理
     */
    private String getMD5Password(String password, String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        //返回加密之后的密码
        return password;


    }
}
