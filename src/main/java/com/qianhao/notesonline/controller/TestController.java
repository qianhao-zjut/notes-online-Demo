package com.qianhao.notesonline.controller;

import com.google.gson.Gson;
import com.qianhao.notesonline.beans.Account;
import com.qianhao.notesonline.biz.FileUploadBiz;
import com.qianhao.notesonline.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller

@RequestMapping("/test")
public class TestController {

    @Autowired
    FileUploadBiz fileUploadBiz;
    @RequestMapping("/uploadHtml")
    @ResponseBody
    public String uploadHtml(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request){
        Result result = fileUploadBiz.uploadHtml(file, request, "1", true,"h5");
        return result.toString();
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request){
        Result result = fileUploadBiz.uploadImage(file, request, "1", "image-20210119101028120.png");
        return result.toString();
    }
    @RequestMapping("/testJson")
    @ResponseBody
    public String jsonTest(){
        Gson gson=new Gson();
        Result fail = Result.fail("失败了");
        String json = gson.toJson(fail);
        return json;
    }
    @RequestMapping("/a")
    @ResponseBody
    public String a(){
        return "aaa";
    }
    /**
     * 测试响应 json 数据
     */
    @RequestMapping("/testResponseJson")
    public @ResponseBody
    Account testResponseJson(@RequestBody Account account) {
        if(account.getId()==1)
            System.out.println(true);
        System.out.println("1111");
        System.out.println("异步请求："+account);
        return account; }


    @RequestMapping("/testSession")
    public String testSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("name","qh");
        return "redirect:success.html";
    }
}
