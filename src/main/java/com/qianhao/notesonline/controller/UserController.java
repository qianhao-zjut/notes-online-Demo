package com.qianhao.notesonline.controller;

import com.google.gson.Gson;
import com.qianhao.notesonline.beans.Account;
import com.qianhao.notesonline.beans.User;
import com.qianhao.notesonline.biz.LoginBiz;
import com.qianhao.notesonline.biz.RegisterBiz;
import com.qianhao.notesonline.result.Result;
import com.qianhao.notesonline.service.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    LoginBiz loginBiz;
    @Autowired
    RegisterBiz registerBiz;

    //用户昵称
    private String name;
    @RequestMapping("/user")

    public String getUser(){
        Gson gson=new Gson();
        gson.toJson(Result.success(userService.findById(1)));
        return "error";

    }


    @RequestMapping("/login")
    public String login(String account, String password, HttpServletRequest request, HttpServletResponse response){
        Result result = loginBiz.login(account, password);
        if(result.getCode().equals("0")){
            //return Result.success("登录成功！",userService.findByParams(userInfo).get(0)).toString();
            //登陆的时候，要将用户的昵称存起来，方便后面展示
            User user=(User)result.getData();
            name=user.getName();
            HttpSession session = request.getSession();
            session.setAttribute("name",name);//存放在Session中
            session.setAttribute("uid",user.getUid());
            if(name==null){
                name="用户"+user.getUid();//如果没有设置昵称，则使用默认昵称
            }
            System.out.println("name:"+name);
            return "redirect:lobby.html";
        }else
            //return Result.fail("登录失败！").toString();
            return "redirect:error.html";

    }
    @RequestMapping("/register")
    @ResponseBody
    public String register(User user){
        Result result = registerBiz.register(user);
        return result.toString();
    }

    /**
     * 用来获取json数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getName")
    public @ResponseBody
    Account testResponseJson(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account=new Account();
        account.setName((String)session.getAttribute("name"));
        return account; }
}
