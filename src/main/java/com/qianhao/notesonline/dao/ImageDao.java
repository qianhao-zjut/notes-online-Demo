package com.qianhao.notesonline.dao;

import com.qianhao.notesonline.beans.Document;
import com.qianhao.notesonline.beans.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
@Mapper
public interface ImageDao {
    Image findById(int id);

    List<Image>findByParams(Map<String,Object>params);

    int insert(Image image);
}
