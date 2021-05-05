package com.qianhao.notesonline.service.impl;

import com.qianhao.notesonline.beans.Tag;
import com.qianhao.notesonline.dao.TagDao;
import com.qianhao.notesonline.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Tag findById(int id) {
        return tagDao.findById(id);
    }
}
