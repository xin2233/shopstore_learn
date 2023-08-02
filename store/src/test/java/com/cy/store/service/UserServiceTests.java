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
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("test3");
            user.setPassword("123");
            userService.reg(user);
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
            User user = userService.login(username, password);
            System.out.println("登录成功！" + user);
        } catch (ServiceException e) {
            System.out.println("登录失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePassword() {
        try {
            Integer uid = 5;
            String username = "lower";
            String oldPassword = "123456";
            String newPassword = "888888";
            userService.changePassword(uid, username, oldPassword, newPassword);
            System.out.println("密码修改成功！");
        } catch (ServiceException e) {
            System.out.println("密码修改失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void getByUid() {
        try {
            Integer uid = 20;
            User user = userService.getByUid(uid);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo() {
        try {
            Integer uid = 20;
            String username = "数据管理员";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeInfo(uid, username, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeAvatar() {
        try {
            Integer uid = 5;
            String username = "头像管理员";
            String avatar = "/upload/avatar.png";
            userService.changeAvatar(uid, username, avatar);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
