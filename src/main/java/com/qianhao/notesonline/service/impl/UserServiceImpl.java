package com.qianhao.notesonline.service.impl;

import com.qianhao.notesonline.beans.User;
import com.qianhao.notesonline.dao.UserDao;
import com.qianhao.notesonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByParams(Map<String, Object> params) {
        return userDao.findByParams(params);
    }

    @Override
    public boolean insertUser(User user) {
        int n = userDao.insertUser(user);
        if(n>=1)
            return true;
        else
            return false;
    }
}
