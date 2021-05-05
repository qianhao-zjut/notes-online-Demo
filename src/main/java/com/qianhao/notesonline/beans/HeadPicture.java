package com.qianhao.notesonline.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class HeadPicture implements Serializable {
    private Integer h_id;
    private Integer uid;
    private String h_name;

    public Integer getH_id() {
        return h_id;
    }

    public void setH_id(Integer h_id) {
        this.h_id = h_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    @Override
    public String toString() {
        return "HeadPicture{" +
                "h_id=" + h_id +
                ", uid=" + uid +
                ", h_name='" + h_name + '\'' +
                '}';
    }
}
