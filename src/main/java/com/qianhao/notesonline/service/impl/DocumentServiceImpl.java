package com.qianhao.notesonline.service.impl;

import com.qianhao.notesonline.beans.Document;
import com.qianhao.notesonline.dao.DocumentDao;
import com.qianhao.notesonline.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentDao documentDao;

    @Override
    public Document findById(int id) {
        return documentDao.findById(id);
    }

    @Override
    public List<Document> findByParams(Map<String, Object> params) {
        return documentDao.findByParams(params);
    }

    @Override
    public boolean insert(Document document) {
        if(documentDao.insert(document)==1)
            return true;
        return false;
    }
}
