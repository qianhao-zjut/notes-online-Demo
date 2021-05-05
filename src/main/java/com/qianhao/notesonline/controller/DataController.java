package com.qianhao.notesonline.controller;

import com.google.gson.Gson;
import com.qianhao.notesonline.beans.Account;
import com.qianhao.notesonline.beans.Knowledge;
import com.qianhao.notesonline.biz.KnowledgeDetailsBiz;
import com.qianhao.notesonline.result.Result;
import com.qianhao.notesonline.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来给前端页面返回数据
 */
@Controller
public class DataController {
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private KnowledgeDetailsBiz knowledgeDetailsBiz;
    @RequestMapping("/ks_data")
    public  @ResponseBody
    Result getKnowledge(HttpServletRequest request, HttpServletResponse response){
        System.out.println("yes");
        HttpSession session = request.getSession();
        int page;
        if(session.getAttribute("page")==null){
            page=0;
            session.setAttribute("page",page);
            //将查询参数存放在session中
            Map<String, Object> params = new HashMap<>();
            params.put("page", page);
            params.put("search",null);
            session.setAttribute("params",params);
        }else{
            page=(Integer) session.getAttribute("page");
        }
        List<Knowledge> knowledges;
        if(session.getAttribute("k_data")==null) {//第一次时，直接从数据库里面获取，后面直接从session中拿数据
            Map<String, Object> params = (Map<String, Object>)session.getAttribute("params");
            /*params.put("page", page);*/
            knowledges = knowledgeService.limitQueue(params);
            session.setAttribute("k_data",knowledges);
        }else{
            knowledges=(List<Knowledge>)session.getAttribute("k_data");
        }
        Result result;
        if(knowledges==null||knowledges.size()==0){
             result= Result.fail("没有更多了！");
        }else
            result = Result.success("数据获取成功！", knowledges);
        return result;
    }
    //上一页
    @RequestMapping("/ks_last")
    public
    String getKnowledgeLast(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        int page;
        page=(Integer) session.getAttribute("page")-7;
        if(page<0)
            page=0;
        session.setAttribute("page",page);
        Map<String, Object> params = (Map<String, Object>)session.getAttribute("params");
        params.replace("page",page);
        session.setAttribute("params",params);

        List<Knowledge> knowledges = knowledgeService.limitQueue(params);
        session.setAttribute("k_data",knowledges);
        return "redirect:lobby.html";
    }
    //下一页
    @RequestMapping("/ks_next")
    public
    String getKnowledgeNext(HttpServletRequest request, HttpServletResponse response){
        System.out.println("next");
        HttpSession session = request.getSession();
        int page;
        page=(Integer) session.getAttribute("page")+7;
        session.setAttribute("page",page);
        Map<String, Object> params = (Map<String,Object>)session.getAttribute("params");
        params.replace("page", page);
        List<Knowledge> knowledges = knowledgeService.limitQueue(params);
        session.setAttribute("k_data",knowledges);
        return "redirect:lobby.html";
    }

    @RequestMapping("/details")
    public
    String details(HttpServletRequest request, HttpServletResponse response, Integer k_id){
        Result details = knowledgeDetailsBiz.getKDetails(k_id);
        System.out.println(details);
        HttpSession session = request.getSession();
        session.setAttribute("knowledge",details.getData());
        return "redirect:kdetails.html";
    }

    @RequestMapping("/k_details")
    public @ResponseBody
    Knowledge Kdetails(HttpServletRequest request, HttpServletResponse response){
        Knowledge result=(Knowledge) request.getSession().getAttribute("knowledge");
        //System.out.println(result);
        return result;
    }

    @RequestMapping("/k_countAdd")
    public @ResponseBody

    Result kCountAdd(HttpServletRequest request,HttpServletResponse response){
        Knowledge result=(Knowledge) request.getSession().getAttribute("knowledge");
        int kid=result.getK_id();
        System.out.println("kid："+kid);
        knowledgeService.k_countAdd(kid);
        return Result.success("操作成功！",null);
    }

    //搜索栏
    @RequestMapping("/search")
    public  @ResponseBody
    Account search(HttpServletRequest request, HttpServletResponse response,@RequestBody Account account){
        System.out.println("search");
        HttpSession session = request.getSession();
        Map<String, Object> params = (Map<String, Object>) session.getAttribute("params");
        params.replace("page", 0);
        System.out.println(account.getName());
        params.replace("search",account.getName());
        List<Knowledge> knowledges = knowledgeService.limitQueue(params);
        session.setAttribute("k_data",knowledges);
        return account;
    }
    //点击大厅时，应该将数据还原
    @RequestMapping("/lobby")
    public
    String lobby(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("page",0);
        Map<String,Object>params= (Map<String, Object>) session.getAttribute("params");
        params.replace("page",0);
        params.replace("search",null);
        session.setAttribute("params",params);
        List<Knowledge> knowledges = knowledgeService.limitQueue(params);
        session.setAttribute("k_data",knowledges);
        return "redirect:lobby.html";
    }
}
