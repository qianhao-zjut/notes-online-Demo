package com.qianhao.notesonline.service.impl;

import com.qianhao.notesonline.beans.Image;
import com.qianhao.notesonline.dao.ImageDao;
import com.qianhao.notesonline.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public Image findById(int id) {
        return imageDao.findById(id);
    }

    @Override
    public List<Image> findByParams(Map<String, Object> params) {
        return imageDao.findByParams(params);
    }

    @Override
    public boolean insert(Image image) {
        return imageDao.insert(image)==1?true:false;
    }
}
