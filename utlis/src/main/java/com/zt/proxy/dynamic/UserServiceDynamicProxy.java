package com.zt.proxy.dynamic;

import com.zt.proxy.interfaces.UserService;
import com.zt.proxy.interfaces.impl.UserServiceImpl;
import com.zt.proxy.pojo.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceDynamicProxy {

    public static void main(String[] args) {
        final UserServiceImpl userService = new UserServiceImpl();

        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始记录日志...");
                        Object returnValue = method.invoke(userService, args);
                        System.out.println("记录日志完毕");
                        return returnValue;
                    }
                });
        User user = new User();
        user.setName("疾风剑豪");
        user.setAge(13);
        userServiceProxy.save(user);
    }

}
