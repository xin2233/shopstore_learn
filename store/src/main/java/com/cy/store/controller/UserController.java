package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        // 调用业务对象执行注册
        userService.reg(user);
        // 返回
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username,password);

        //登录成功后，将uid和username存入到HttpSession中
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        System.out.println("Session中的uid=" + getUidFromSession(session));
        System.out.println("Session中的username=" + getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }
    /**
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        // 调用session.getAttribute("")获取uid和username
        // 调用业务对象执行修改密码
        // 返回成功

        return null;
    }
    */

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        // 调用session.getAttribute("")获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改密码
        userService.changePassword(uid, username, oldPassword, newPassword);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    /**
    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        // 从HttpSession对象中获取uid
        // 调用业务对象执行获取数据
        // 响应成功和数据
        return null;
    }
     */

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        // 从HttpSession对象中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行获取数据
        User data = userService.getByUid(uid);
        // 响应成功和数据
        return new JsonResult<User>(OK, data);
    }


    /**
     * @RequestMapping("change_info")
     * public JsonResult<Void> changeInfo(User user, HttpSession session) {
     *     // 从HttpSession对象中获取uid和username
     *     // 调用业务对象执行修改用户资料
     *     // 响应成功
     *     return null;
     * }
     */

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改用户资料
        userService.changeInfo(uid, username, user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }
}


