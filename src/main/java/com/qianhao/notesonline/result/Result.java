package com.qianhao.notesonline.result;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用来封装结果
 */
@Component
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static Result success(String msg, Object data){
        Result result=new Result();
        result.code="0";
        result.msg=msg;
        result.data=data;
        return result;
    }

    public static Result success(Object data){
        Result result=new Result();
        result.code="0";
        result.msg="操作成功";
        result.data=data;
        return result;
    }

    public static Result fail(String msg,Object data){
        Result result=new Result();
        result.code="-1";
        result.msg=msg;
        result.data=data;
        return result;
    }

    public static Result fail(String msg){
        Result result=new Result();
        result.code="-1";
        result.msg=msg;
        result.data=null;
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
