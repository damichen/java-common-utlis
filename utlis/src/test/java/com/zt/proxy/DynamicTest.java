package com.zt.proxy;

import com.zt.proxy.interfaces.UserService;
import com.zt.proxy.interfaces.impl.UserServiceImpl;
import com.zt.proxy.interfaces.impl.UserServiceImpl1;
import com.zt.proxy.pojo.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicTest {

    @Test
    public void fun01() {
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

    @Test
    public void funCglib() {
        final UserServiceImpl1 serviceImpl1 = new UserServiceImpl1();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceImpl1.getClass());
        enhancer.setCallback(new net.sf.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                System.out.println("开始记录日志...");
                Object returnValue = method.invoke(serviceImpl1, args);
                System.out.println("记录日志完毕");
                return returnValue;
            }
        });
        UserServiceImpl1 userServiceProxy = (UserServiceImpl1) enhancer.create();
        User user = new User();
        user.setName("疾风剑豪111");
        user.setAge(1322);
        userServiceProxy.save(user);
    }
    @Test
    public void funCglib1() {
        final UserServiceImpl1 serviceImpl1 = new UserServiceImpl1();
        UserServiceImpl1 userServiceProxy = (UserServiceImpl1)  Enhancer.create(serviceImpl1.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("开始记录日志...");
                Object returnValue = method.invoke(serviceImpl1, args);
                System.out.println("记录日志完毕");
                return returnValue;
            }
        });

        User user = new User();
        user.setName("放逐之刃");
        user.setAge(1322);
        userServiceProxy.save(user);
    }
}
