package com.qianhao.notesonline.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Image implements Serializable {
    private Integer i_id;
    private Integer uid;
    private String i_name;

    public Integer getI_id() {
        return i_id;
    }

    public void setI_id(Integer i_id) {
        this.i_id = i_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    @Override
    public String toString() {
        return "Image{" +
                "i_id=" + i_id +
                ", uid=" + uid +
                ", i_name='" + i_name + '\'' +
                '}';
    }
}
