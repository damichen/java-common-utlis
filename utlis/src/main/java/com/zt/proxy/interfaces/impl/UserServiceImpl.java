package com.zt.proxy.interfaces.impl;

import com.zt.proxy.interfaces.UserService;
import com.zt.proxy.pojo.User;

public class UserServiceImpl implements UserService {
    @Override
    public boolean save(User user) {
        System.out.println(this.getClass().getSimpleName());
        System.out.println(user);
        return false;
    }
}
