package com.qianhao.notesonline.service.impl;

import com.qianhao.notesonline.beans.Knowledge;
import com.qianhao.notesonline.dao.KnowledgeDao;
import com.qianhao.notesonline.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDao knowledgeDao;

    @Override
    public Knowledge findById(int id) {
        return knowledgeDao.findById(id);
    }

    @Override
    public int insertBatch(List<Knowledge> knowledges) {
        return knowledgeDao.insertBatch(knowledges);
    }

    @Override
    public List<Knowledge> limitQueue(Map<String,Object> params) {
        return knowledgeDao.limitQueue(params);
    }

    @Override
    public void k_countAdd(int kid) {
        knowledgeDao.k_countAdd(kid);
    }
}
