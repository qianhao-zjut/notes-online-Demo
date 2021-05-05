package com.qianhao.notesonline.dao;

import com.qianhao.notesonline.beans.Document;
import com.qianhao.notesonline.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface DocumentDao {
    /**
     * 通过主键查询
     * @param id       d_id
     * @return          查询的唯一记录
     */
    Document findById(int id);

    /**
     *
     * @param params
     * @return
     */
    List<Document> findByParams(Map<String,Object> params);

    /**
     * 插入一条记录
     * @param document  document对象
     * @return          影响的行数
     */
    int insert(Document document);
}
