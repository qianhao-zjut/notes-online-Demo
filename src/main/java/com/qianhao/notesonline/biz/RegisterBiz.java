package com.qianhao.notesonline.biz;

import com.qianhao.notesonline.beans.User;
import com.qianhao.notesonline.result.Result;
import com.qianhao.notesonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能2：注册功能
 */
@Component
public class RegisterBiz {
    @Autowired
    UserService userService;
    public Result register(User user){
        user.setHeadPicture("1.png");//默认使用这个头像
        boolean flag = userService.insertUser(user);
        if(flag)
            return Result.success("注册成功！",user);
        else
            return Result.fail("注册失败！");
    }
}
