package com.zt.proxy.dynamic;

import com.zt.proxy.interfaces.UserService;
import com.zt.proxy.pojo.User;

public class UserServiceProxy implements UserService{

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean save(User user) {
        System.out.println("开始执行静态代理...");
        userService.save(user);
        System.out.println("结束执行静态代理...");
        return false;
    }
}
