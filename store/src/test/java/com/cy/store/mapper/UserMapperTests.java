package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @RunWith(): 表示启动这个单元测试类（单元测试类是补丁够运行的），需要传递一个类，必须是springrunner的实例类
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
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
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        String username = "test1";
        User result = userMapper.findByUsername(username);
        System.out.println(result);
    }

    @Test
    public void print() {
        System.out.println("============");
        System.out.println("1111");
    }

    @Test
    public void updatePasswordByUid() {
        Integer uid = 7;
        String password = "123456";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(20);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender(1);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }


    @Test
    public void updateAvatarByUid() {
        Integer uid = 20;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }
}
