package com.qianhao.notesonline.dao;

import com.qianhao.notesonline.beans.Tag;
import com.qianhao.notesonline.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface TagDao {
    Tag findById(int id);
    List<Tag> findByParams(Map<String,Object> params);
}
