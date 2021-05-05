package com.qianhao.notesonline.dao;

import com.qianhao.notesonline.beans.Knowledge;
import com.qianhao.notesonline.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface KnowledgeDao {
    /**
     * 通过主键查询
     * @param id    主键：k_id
     * @return      唯一记录
     */
    Knowledge findById(int id);
    List<Knowledge> findByParams(Map<String,Object> params);

    /**
     * 批量插入
     * @param knowledges
     * @return
     */
    int insertBatch(@Param("knowledges")List<Knowledge>knowledges);

    /**
     *
     * @param page  当前的页码
     * @return      分页查询的结果
     */
    List<Knowledge>limitQueue(Map<String,Object> params);

    void k_countAdd(int kid);
}
