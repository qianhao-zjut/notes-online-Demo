package com.qianhao.notesonline.biz;

import com.qianhao.notesonline.beans.User;
import com.qianhao.notesonline.result.Result;
import com.qianhao.notesonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能1：登录验证功能
 */

@Component
public class LoginBiz {
    @Autowired
    private UserService userService;

    /**
     *
     * @param account   账号
     * @param password  密码
     * @return          登录验证信息
     */
    public Result login(String account,String password){
        Map<String,Object> userInfo=new HashMap<>();
        userInfo.put("account",account);
        userInfo.put("password",password);
        User user=userService.findByParams(userInfo).get(0);
        if(user!=null){
            return Result.success("登录成功！",user);
        }else

            return Result.fail("登录失败！");
    }
}
