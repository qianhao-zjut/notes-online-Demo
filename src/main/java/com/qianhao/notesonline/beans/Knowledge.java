package com.qianhao.notesonline.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class Knowledge implements Serializable {
    private Integer k_id;
    private Integer uid;
    private String k_name;
    private String belong_title;
    private String k_content;
    private Integer count;
    private Date time;
    private String tags;
    private Integer shared;
    private String u_name;
    private String k_describe;

    public Knowledge() {
        count=0;
        shared=0;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getK_describe() {
        return k_describe;
    }

    public void setK_describe(String k_describe) {
        this.k_describe = k_describe;
    }

    public String getBelong_title() {
        return belong_title;
    }

    public void setBelong_title(String belong_title) {
        this.belong_title = belong_title;
    }

    public String getK_name() {
        return k_name;
    }

    public void setK_name(String k_name) {
        this.k_name = k_name;
    }

    public Integer getK_id() {
        return k_id;
    }

    public void setK_id(Integer k_id) {
        this.k_id = k_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getK_content() {
        return k_content;
    }

    public void setK_content(String k_content) {
        this.k_content = k_content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer sheared) {
        this.shared = sheared;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "k_id=" + k_id +
                ", uid=" + uid +
                ", k_name='" + k_name + '\'' +
                ", belong_title='" + belong_title + '\'' +
                ", k_content='" + k_content + '\'' +
                ", count=" + count +
                ", time=" + time +
                ", tags='" + tags + '\'' +
                ", shared=" + shared +
                ", u_name='" + u_name + '\'' +
                ", k_describe='" + k_describe + '\'' +
                '}';
    }
}
