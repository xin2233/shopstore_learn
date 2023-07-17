package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(): 表示启动这个单元测试类（单元测试类是补丁够运行的），需要传递一个类，必须是springrunner的实例类
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    /**
     * 丹云测试方法：就可以单独运行，不用启动整个项目
     * 1.必须被@Test注解修饰
     * 2.返回值必须是void
     * 3.方法参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */

//    Could not autowire. No beans of 'UserMapper' type found.
//    idea有检测的功能，接口是不能够直接创建Bean的（动态代理计数来解决）
    @Autowired
    private IUserService iUserService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("test3");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("插入ok");
        } catch (ServiceException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            String username = "test3";
            String password = "123";
            User user = iUserService.login(username, password);
            System.out.println("登录成功！" + user);
        } catch (ServiceException e) {
            System.out.println("登录失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
