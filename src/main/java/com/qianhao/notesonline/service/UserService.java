package com.qianhao.notesonline.service;

import com.qianhao.notesonline.beans.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User findById(int id);
    List<User> findByParams(Map<String,Object>params);
    boolean insertUser(User user);
}
