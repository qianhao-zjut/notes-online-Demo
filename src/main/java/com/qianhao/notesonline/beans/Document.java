package com.qianhao.notesonline.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class Document implements Serializable {
    private Integer d_id;
    private Integer uid;
    private String d_name;
    private String title;
    private Date upload_time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    @Override
    public String toString() {
        return "Document{" +
                "d_id=" + d_id +
                ", uid=" + uid +
                ", d_name='" + d_name + '\'' +
                ", title='" + title + '\'' +
                ", upload_time=" + upload_time +
                '}';
    }
}
