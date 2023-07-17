package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}


