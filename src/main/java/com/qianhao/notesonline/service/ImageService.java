package com.qianhao.notesonline.service;

import com.qianhao.notesonline.beans.Image;

import java.util.List;
import java.util.Map;

public interface ImageService {
    Image findById(int id);

    List<Image> findByParams(Map<String,Object> params);

    boolean insert(Image image);
}
