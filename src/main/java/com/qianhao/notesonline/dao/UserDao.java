package com.qianhao.notesonline.dao;

import com.qianhao.notesonline.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserDao {
    User findById(int id);
    List<User> findByParams(Map<String,Object>params);
    int insertUser(User user);
}
