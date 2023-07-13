package com.cy.store.mapper;

import com.cy.store.entity.User;

//用户模块的持久层接口

public interface UserMapper {
    //返回受影响的行数
    Integer insert(User user);

    //返回用户的数据或者null
    User findByUsername(String username);



}
