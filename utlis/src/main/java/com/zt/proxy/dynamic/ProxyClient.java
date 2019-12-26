package com.zt.proxy.dynamic;

import com.zt.proxy.interfaces.impl.UserServiceImpl;
import com.zt.proxy.pojo.User;

public class ProxyClient {
    public static void main(String[] args) {
        UserServiceProxy proxy = new UserServiceProxy();
        UserServiceImpl userService = new UserServiceImpl();
        proxy.setUserService(userService);
        User user = new User();
        user.setAge(19);
        user.setName("提莫队长");
        proxy.save(user);
    }
}
