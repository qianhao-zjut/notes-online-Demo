package com.qianhao.notesonline.service;

import com.qianhao.notesonline.beans.Knowledge;

import java.util.List;
import java.util.Map;

public interface KnowledgeService {
    Knowledge findById(int id);
    int insertBatch(List<Knowledge>knowledges);
    List<Knowledge>limitQueue(Map<String,Object> params);
    void k_countAdd(int kid);
}
