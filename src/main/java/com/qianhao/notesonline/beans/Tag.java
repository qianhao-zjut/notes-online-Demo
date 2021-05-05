package com.qianhao.notesonline.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Tag implements Serializable {
    private Integer t_id;
    private String t_name;
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "t_id=" + t_id +
                ", t_name='" + t_name + '\'' +
                ", uid=" + uid +
                '}';
    }
}
