package com.qianhao.notesonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
//关闭GsonAutoConfiguration自动装载
@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
public class    NotesOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesOnlineApplication.class, args);
    }

}
